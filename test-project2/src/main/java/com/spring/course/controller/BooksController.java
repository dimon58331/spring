package com.spring.course.controller;

import com.spring.course.entity.Book;
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
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String getShowAllBooks(Model model){
        model.addAttribute("books", booksService.findAll());

        return "books/show-all-books";
    }

    @GetMapping("/new")
    public String getAddNewBook(Model model){
        model.addAttribute("book", new Book());

        return "books/add-new-book";
    }

    @PostMapping()
    public String postAddNewBook(@ModelAttribute("book") Book book){
        booksService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getUpdateBook(@PathVariable int id, Model model) throws IOException {
        Optional<Book> book = booksService.findById(id);
        if (book.isEmpty()){
            throw new IOException("Invalid id");
        }

        model.addAttribute("book", book.get());

        return "books/edit-book";
    }

    // add /{id} in the controller and remove hidden input from view
    @PatchMapping()
    public String patchUpdateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/edit-book";
        }

        booksService.update(book);
        return "redirect:/books/" + book.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id){
        booksService.deleteById(id);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getShowBook(@PathVariable int id, Model model) throws IOException {
        Optional<Book> book = booksService.findById(id);

        if (book.isEmpty()){
            throw new IOException("Invalid id");
        }

        Person person = peopleService.findByBook(book.get());

        model.addAttribute("book", book.get());
        model.addAttribute("person", person);
        model.addAttribute("people", peopleService.findAll());

        return "books/show-book";
    }

    // add /{id} in the controller and remove hidden input from view, update view action,
    // return in model only id book and person
    @PostMapping("/{id}/add-to-person")
    public String addBookToPerson(@ModelAttribute("book") Book book, @ModelAttribute("person") Person person){
        booksService.addPersonByBookId(person, book.getId());

        return "redirect:/books/" + book.getId();
    }

    // add /{id} in the controller and remove hidden input from view, update view action
    @DeleteMapping ("/{id}/remove-from-person")
    public String removeBookFromPerson(@ModelAttribute("book") Book book){
        peopleService.removeBook(book);

        return "redirect:/books/" + book.getId();
    }
}
