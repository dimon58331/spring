package com.spring.course.service;

import com.spring.course.entity.Book;
import com.spring.course.entity.Person;
import com.spring.course.repository.BooksRepository;
import com.spring.course.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }
    public Optional<Book> findById(int id){
        return booksRepository.findById(id);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void addPersonByBookId(Person person, int id){
        Book book = booksRepository.findById(id).orElseThrow(RuntimeException::new);
        Person filledPerson = peopleRepository.findById(person.getId()).orElseThrow(RuntimeException::new);

        book.setPerson(filledPerson);

        //For Hibernate cache
        if (filledPerson.getBooks().isEmpty()){
            filledPerson.setBooks(new ArrayList<>(Collections.singletonList(book)));
        }else{
            filledPerson.getBooks().add(book);
        }
    }
}
