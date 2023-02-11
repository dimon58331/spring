package com.spring.course.controller;

import com.spring.course.entity.Person;
import com.spring.course.repository.BookDAO;
import com.spring.course.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String showAllPeople(Model model){
        model.addAttribute("people", personDAO.findAll());
        return "people/show-all-people";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/add-new-person";
    }

    @PostMapping()
    public String addNewPerson(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) throws IOException {
        Optional<Person> person = personDAO.findById(id);
        if (person.isEmpty()){
            throw new IOException("Invalid id!");
        }

        model.addAttribute("person", person.get());
        return "people/edit-person";
    }

    @PatchMapping()
    public String patchEditPerson(@ModelAttribute("person") Person person){
        personDAO.update(person);

        return "redirect:/people/" + person.getPersonId();
    }

    @GetMapping("/{id}")
    public String showPersonById(@PathVariable("id") int id, Model model) throws IOException {
        Optional<Person> person = personDAO.findById(id);
        if (person.isEmpty()){
            throw new IOException("Invalid id!");
        }

        model.addAttribute("person", person.get());
        model.addAttribute("books", bookDAO.findAllByPersonId(id));

        return "people/show-person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id){
        personDAO.delete(id);

        return "redirect:/people";
    }
}
