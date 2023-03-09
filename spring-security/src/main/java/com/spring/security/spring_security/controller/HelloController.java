package com.spring.security.spring_security.controller;

import com.spring.security.spring_security.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/personInfo")
    public String getPersonInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        Logger.getAnonymousLogger().info(personDetails.getPerson().toString());

        return "hello";
    }
}
