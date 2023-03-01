package com.spring.course.controller;

import com.spring.course.entity.Person;
import com.spring.course.service.BooksService;
import com.spring.course.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String showAllPeople(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "people/show-all-people";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/add-new-person";
    }

    @PostMapping()
    public String addNewPerson(@ModelAttribute("person") Person person){
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) throws IOException {
        Optional<Person> person = peopleService.findById(id);
        if (person.isEmpty()){
            throw new IOException("Invalid id!");
        }

        model.addAttribute("person", person.get());
        return "people/edit-person";
    }

    @PatchMapping()
    public String patchEditPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/edit-person";
        }

        peopleService.update(person);

        return "redirect:/people/" + person.getId();
    }

    @GetMapping("/{id}")
    public String showPersonById(@PathVariable("id") int id, Model model) throws IOException {
        Optional<Person> person = peopleService.findById(id);
        if (person.isEmpty()){
            throw new IOException("Invalid id!");
        }

        model.addAttribute("person", person.get());
        model.addAttribute("books", person.get().getBooks());

        return "people/show-person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id){
        peopleService.deleteById(id);

        return "redirect:/people";
    }
}
