package com.spring.course.repository;

import com.spring.course.entity.Book;
import com.spring.course.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    public List<Person> findByBooks(List<Book> books);
}
