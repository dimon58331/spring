package com.spring.course.service;

import com.spring.course.model.Person;
import com.spring.course.repository.PersonRepository;
import com.spring.course.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void register(Person person){
        person.setRole("ROLE_" + Role.USER);
        personRepository.save(person);
    }
}
