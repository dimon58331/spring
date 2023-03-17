package com.spring.course.controller;

import com.spring.course.config.JWTFilter;
import com.spring.course.dto.PersonDTO;
import com.spring.course.model.Person;
import com.spring.course.response.JWTResponse;
import com.spring.course.service.PersonService;
import com.spring.course.util.JWTUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class RegistrationController {
    private final ModelMapper modelMapper;
    private final PersonService personService;
    private final JWTUtil jwtUtil;

    @Autowired
    public RegistrationController(ModelMapper modelMapper, PersonService personService, JWTUtil jwtUtil) {
        this.modelMapper = modelMapper;
        this.personService = personService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registration")
    public JWTResponse generateToken(@RequestBody PersonDTO personDTO){
        Person person = convertToPerson(personDTO);
        personService.register(person);

        Logger.getLogger(RegistrationController.class.toString()).info("WE'RE HERE");

        return new JWTResponse(jwtUtil.generateToken(person.getUsername()));
    }

    public Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }
}
