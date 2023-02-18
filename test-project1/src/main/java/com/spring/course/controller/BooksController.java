package com.spring.course.controller;

import com.spring.course.entity.Book;
import com.spring.course.entity.Person;
import com.spring.course.repository.BookDAO;
import com.spring.course.repository.PersonDAO;

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
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getShowAllBooks(Model model){
        model.addAttribute("books", bookDAO.findAll());

        return "books/show-all-books";
    }

    @GetMapping("/new")
    public String getAddNewBook(Model model){
        model.addAttribute("book", new Book());

        return "books/add-new-book";
    }

    @PostMapping()
    public String postAddNewBook(@ModelAttribute("book") Book book){
        bookDAO.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getUpdateBook(@PathVariable int id, Model model) throws IOException {
        Optional<Book> book = bookDAO.findById(id);
        if (book.isEmpty()){
            throw new IOException("Invalid id");
        }

        model.addAttribute("book", book.get());

        return "books/edit-book";
    }

    @PatchMapping()
    public String patchUpdateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/edit-book";
        }

        bookDAO.update(book);
        return "redirect:/books/" + book.getBookId();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id){
        bookDAO.delete(id);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getShowBook(@PathVariable int id, Model model) throws IOException {
        Optional<Book> book = bookDAO.findById(id);

        if (book.isEmpty()){
            throw new IOException("Invalid id");
        }

        Person person = personDAO.findByBookId(id).isEmpty() ? new Person() : personDAO.findByBookId(id).get();

        model.addAttribute("book", book.get());
        model.addAttribute("person", person);
        model.addAttribute("people", personDAO.findAll());

        return "books/show-book";
    }

    @PostMapping("/add-book-to-person")
    public String addBookToPerson(@ModelAttribute("book") Book book, @ModelAttribute("person") Person person){
        bookDAO.updateByPersonId(book, person.getPersonId());

        return "redirect:/books/" + book.getBookId();
    }

    @DeleteMapping ("/remove-book-from-person")
    public String removeBookFromPerson(@ModelAttribute("book") Book book){
        bookDAO.updateByPersonId(book, 0);

        return "redirect:/books/" + book.getBookId();
    }
}
