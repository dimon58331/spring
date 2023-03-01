package com.crud.hibernate_and_spring_data_jpa.service;

import com.crud.hibernate_and_spring_data_jpa.entity.Person;
import com.crud.hibernate_and_spring_data_jpa.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findById(int id){
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person){
        person.setDateOfCreate(new Date());
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person person){
        person.setDateOfCreate(Objects.requireNonNull(peopleRepository
                .findById(person.getId()).orElse(null)).getDateOfCreate());
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(Person person){
        peopleRepository.delete(person);
    }
}
