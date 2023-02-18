package com.spring.course.repository;

import com.spring.course.entity.Person;
import com.spring.course.entity.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    // database
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll(){
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Optional<Person> findById(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?"
                , new Object[]{id}, new PersonMapper()).stream().findAny();
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(name, surname, patronymic, year) VALUES(?, ?, ?, ?)"
                , person.getName(), person.getSurname()
                , person.getPatronymic(), person.getYear());
    }

    public void update(Person person){
        jdbcTemplate.update("UPDATE Person SET name=?, surname=?, patronymic=?, year=? WHERE person_id=?"
                , person.getName(), person.getSurname(), person.getPatronymic()
                , person.getYear(), person.getPersonId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    public Optional<Person> findByBookId(int bookId){
        return jdbcTemplate.query("SELECT Person.* FROM Person " +
                        "JOIN Book ON Book.person_id=Person.person_id WHERE Book.book_id=?"
                        , new PersonMapper(), bookId)
                .stream().findAny();
    }
}
