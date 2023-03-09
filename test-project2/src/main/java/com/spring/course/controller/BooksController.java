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
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

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
    public String getShowAllBooks(@RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "sortBy", required = false) String sortBy, Model model){
        model.addAttribute("books", booksService.findAll(page, sortBy));

        return "books/show-all-books";
    }

    @GetMapping("/new")
    public String getAddNewBook(Model model){
        model.addAttribute("book", new Book());

        return "books/add-new-book";
    }

    @GetMapping("/search")
    public String getAllBooksWithSearch(@RequestParam(value = "bookName", required = false) String bookName
            , Model model){
        if (Objects.isNull(bookName) || bookName.isEmpty()){
            model.addAttribute("booksContainingName",null);
            return "books/search-books";
        }

        List<Book> booksContainingName = booksService.findAllByBookNameContainingIgnoreCase(bookName);
        model.addAttribute("booksContainingName", booksContainingName);

        return "books/search-books";
    }

    @PostMapping()
    public String postAddNewBook(@ModelAttribute("book") Book book){
        booksService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getUpdateBook(@PathVariable int id, Model model) throws IOException {
        Book book = booksService.findById(id);

        model.addAttribute("book", book);

        return "books/edit-book";
    }

    // add /{id} in the controller and remove hidden input from view
    @PatchMapping("/{id}")
    public String patchUpdateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult)
            throws IOException {
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
        Book book = booksService.findById(id);

        Person person = peopleService.findByBook(book);

        model.addAttribute("book", book);
        model.addAttribute("person", person);
        model.addAttribute("people", peopleService.findAll());

        return "books/show-book";
    }

    // add /{id} in the controller and remove hidden input from view, update view action,
    // return in model only id book and person
    @PostMapping("/{id}/add-to-person")
    public String addBookToPerson(@ModelAttribute("book") Book book, @ModelAttribute("person") Person person
            , @PathVariable("id") int id){
        book.setId(id);
        Logger.getAnonymousLogger().info("ID from url: " + id);
        Logger.getAnonymousLogger().info("Book: " + book);
        Logger.getAnonymousLogger().info("Person: " + person);
        booksService.addPersonByBookId(person, book.getId());

        return "redirect:/books/" + book.getId();
    }

    // add /{id} in the controller and remove hidden input from view, update view action
    @DeleteMapping ("/{id}/remove-from-person")
    public String removeBookFromPerson(@ModelAttribute("book") Book book) throws IOException {
        peopleService.removeBook(book);

        return "redirect:/books/" + book.getId();
    }
}
