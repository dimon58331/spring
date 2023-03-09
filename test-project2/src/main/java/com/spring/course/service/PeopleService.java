package com.spring.course.service;

import com.spring.course.entity.Book;
import com.spring.course.entity.Person;
import com.spring.course.repository.BooksRepository;
import com.spring.course.repository.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findById(int id) throws IOException {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isEmpty()){
            throw new IOException("Invalid id!");
        }
        Hibernate.initialize(person.get().getBooks());

        person.get().getBooks().forEach((book -> {
            long timeOfOut = Math.abs(book.getDateOfTaken().getTime() - new Date().getTime());
            book.setTimeOut(timeOfOut > 864000000);
        }));

        return person.get();
    }

    public Person findByBook(Book book){
        List<Person> people = peopleRepository.findByBooks(book);
        return people.isEmpty() ? new Person() : people.get(0);
    }

    @Transactional
    public void deleteById(int id){
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void save(Person person){
        person.setDateOfCreation(new Date());
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person person) throws IOException {
        person.setDateOfCreation(peopleRepository.findById(person.getId()).orElseThrow(IOException::new)
                .getDateOfCreation());
        peopleRepository.save(person);
    }

    @Transactional
    public void removeBook(Book book) throws IOException {
        Book filledBook = booksRepository.findById(book.getId()).orElseThrow(IOException::new);
        filledBook.setPerson(null);
        filledBook.setDateOfTaken(null);
    }
}
