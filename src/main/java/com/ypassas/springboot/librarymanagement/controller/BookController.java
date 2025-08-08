package com.ypassas.springboot.librarymanagement.controller;

import com.ypassas.springboot.librarymanagement.model.Book;
import com.ypassas.springboot.librarymanagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {


    private final BookService bookService;

    @Autowired
    public BookController(BookService theBookService) {
        bookService = theBookService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listBooks(Model theModel) {

        // get the books from db
        List<Book> theBooks = bookService.findAll();

        // add to the spring model
        theModel.addAttribute("books", theBooks);

        return "books/list-of-books";
    }

    // add mapping for "/showFormForAdd"
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Book theBook = new Book();

        theModel.addAttribute("book", theBook);

        return "books/book-form";
    }

    // add mapping for "/showFormForUpdate"
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId, Model theModel) {
        // get the book from the service
        Book theBook = bookService.findById(theId);

        // set book in the model to prepopulate the form
        theModel.addAttribute("book", theBook);

        // send over our form
        return "books/book-form";
    }

    // add mapping for "/delete"
    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId){
        // delete book
        bookService.deleteById(theId);

        // redirect to /books/list
        return "redirect:/books/list";
    }

    // add mapping for "/save"
    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") Book theBook,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()) {
            return "books/book-form";
        }

        try {
            // save book
            bookService.save(theBook);
        }
        catch (IllegalArgumentException exception){
            model.addAttribute("errorMessage", exception.getMessage());
            return "books/book-form";
        }

        // use a redirect to prevent duplicate submissions
        return "redirect:/books/list";
    }
    // add mapping for available books
    @GetMapping("/available")
    public String showAvailableBooks(Model theModel) {
        List<Book> availableBooks = bookService.findByIsAvailableTrue();
        theModel.addAttribute("books", availableBooks);
        theModel.addAttribute("showingAvailable", true);
        return "books/list-of-books";
    }

}