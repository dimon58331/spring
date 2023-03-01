package com.spring.course.service;

import com.spring.course.entity.Book;
import com.spring.course.entity.Person;
import com.spring.course.repository.BooksRepository;
import com.spring.course.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public Optional<Person> findById(int id){
        return peopleRepository.findById(id);
    }

    public Person findByBook(Book book){
        List<Person> people = peopleRepository.findByBooks(Collections.singletonList(book));
        return people.isEmpty() ? new Person() : people.get(0);
    }

    @Transactional
    public void deleteById(int id){
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void removeBook(Book book){
        Person person = peopleRepository.findByBooks(Collections.singletonList(book)).get(0);

        person.getBooks().remove(book);
        //for Hibernate cache ???
        book.setPerson(null);
    }
}
