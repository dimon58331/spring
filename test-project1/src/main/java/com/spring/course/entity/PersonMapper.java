package com.spring.course.entity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setPersonId(rs.getInt("person_id"));
        person.setName(rs.getString("name"));
        person.setSurname(rs.getString("surname"));
        person.setPatronymic(rs.getString("patronymic"));
        person.setYear(rs.getInt("year"));

        return person;
    }
}
