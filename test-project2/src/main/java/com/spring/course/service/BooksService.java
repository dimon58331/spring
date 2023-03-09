package com.spring.course.service;

import com.spring.course.entity.Book;
import com.spring.course.entity.Person;
import com.spring.course.repository.BooksRepository;
import com.spring.course.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

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

    public List<Book> findAll(Integer page, String sortBy){
        return Objects.nonNull(page) && Objects.nonNull(sortBy)
                ? booksRepository.findAll(PageRequest.of(page, 3, Sort.by(sortBy))).getContent()
                : Objects.nonNull(page) ? booksRepository.findAll(PageRequest.of(page, 3)).getContent()
                : Objects.nonNull(sortBy) ? booksRepository.findAll(Sort.by(sortBy))
                : booksRepository.findAll();
    }

    public Book findById(int id) throws IOException {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isEmpty()){
            throw new IOException("Invalid id");
        }

        return book.get();
    }

    public List<Book> findAllByBookNameContainingIgnoreCase(String bookName){
        List<Book> books = booksRepository.findAllByBookNameContainingIgnoreCase(bookName);
        books.forEach(book -> {
            long timeOfOut = Math.abs(book.getDateOfTaken().getTime() - new Date().getTime());
            book.setTimeOut(timeOfOut > 864000000);
        });
        return books;
    }

    @Transactional
    public void save(Book book){
        book.setDateOfCreation(new Date());
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book book) throws IOException {
        book.setDateOfCreation(booksRepository.findById(book.getId()).orElseThrow(IOException::new)
                .getDateOfCreation());
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id){
        booksRepository.deleteById(id);
        // maybe add code for Hibernate cache
    }

    @Transactional
    public void addPersonByBookId(Person person, int id){
        Book book = booksRepository.findById(id).orElseThrow(RuntimeException::new);
        Person filledPerson = peopleRepository.findById(person.getId()).orElseThrow(RuntimeException::new);

        book.setPerson(filledPerson);
        book.setDateOfTaken(new Date());
        //For Hibernate cache
        if (filledPerson.getBooks().isEmpty()){
            filledPerson.setBooks(new ArrayList<>(Collections.singletonList(book)));
        }else{
            filledPerson.getBooks().add(book);
        }
    }
}
