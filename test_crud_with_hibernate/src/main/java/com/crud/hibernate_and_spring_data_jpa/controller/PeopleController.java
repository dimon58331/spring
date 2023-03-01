package com.crud.hibernate_and_spring_data_jpa.controller;

import com.crud.hibernate_and_spring_data_jpa.entity.Person;
import com.crud.hibernate_and_spring_data_jpa.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService){
        this.peopleService = peopleService;
    }

    @GetMapping
    public String showPeople(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "show-people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findById(id));
        return "show-person";
    }

    @GetMapping("/new")
    public String savePeople(Model model){
        model.addAttribute("person", new Person());
        return "add-person";
    }

    @GetMapping("/{id}/update")
    public String updatePerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findById(id));
        return "edit-person";
    }

    @PostMapping
    public String postPerson(@ModelAttribute("person") @Valid Person person
            , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add-person";
        }
        peopleService.save(person);

        return "redirect:/people/" + person.getId();
    }

    @PatchMapping("/{id}")
    public String patchPerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person
            , BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "edit-person";
        }
        peopleService.update(person);

        return "redirect:/people/" + id;
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@ModelAttribute("person") Person person){
        peopleService.delete(person);
        return "redirect:/people";
    }
}
