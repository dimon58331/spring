package com.spring.security.spring_security.service;

import com.spring.security.spring_security.entity.Person;
import com.spring.security.spring_security.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findByUsername(String username){
        Optional<Person> person = personRepository.findByUsername(username);
        if (person.isEmpty()){
            throw new UsernameNotFoundException("User with this username not found");
        }

        return person.get();
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

}
