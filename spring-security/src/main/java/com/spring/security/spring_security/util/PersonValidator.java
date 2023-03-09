package com.spring.security.spring_security.util;

import com.spring.security.spring_security.entity.Person;
import com.spring.security.spring_security.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            personService.findByUsername(((Person) target).getUsername());
            errors.rejectValue("username", "", "User with this username exists");
        }catch (UsernameNotFoundException ignored){
        }
    }
}
