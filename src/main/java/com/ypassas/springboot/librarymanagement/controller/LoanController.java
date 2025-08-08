package com.ypassas.springboot.librarymanagement.controller;

import com.ypassas.springboot.librarymanagement.model.Book;
import com.ypassas.springboot.librarymanagement.model.Loan;
import com.ypassas.springboot.librarymanagement.model.Member;
import com.ypassas.springboot.librarymanagement.service.BookService;
import com.ypassas.springboot.librarymanagement.service.LoanService;
import com.ypassas.springboot.librarymanagement.service.MemberService;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final MemberService memberService;
    private final BookService bookService;

    @Autowired
    public LoanController(LoanService loanService, MemberService memberService, BookService bookService) {
        this.loanService = loanService;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listLoans(Model theModel) {

        // get the loans from db
        List<Loan> theLoans = loanService.findAllByOrderByReturnedAscReturnDateAsc();

        // add to the spring model
        theModel.addAttribute("loans", theLoans);

        return "loans/list-of-loans";

    }

    // add mapping for "/showFormForAdd"
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Loan theLoan = new Loan();
        theLoan.setBorrowDate(LocalDate.now());
        theLoan.setReturnDate(LocalDate.now().plusDays(7));
        theModel.addAttribute("loan", theLoan);

        // list of members
        List<Member> members = memberService.findAll();
        theModel.addAttribute("members", members);

        // list of available books
        List<Book> books = bookService.findAll().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
        theModel.addAttribute("books", books);

        return "loans/loan-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("loanId") int theId, Model theModel) {
        Loan theLoan = loanService.findById(theId);
        theModel.addAttribute("loan", theLoan);

        // list of members
        List<Member> members = memberService.findAll();
        theModel.addAttribute("members", members);

        // list of available books
        List<Book> books = bookService.findAll();
        theModel.addAttribute("books", books);

        return "loans/loan-form";
    }

    @PostMapping("/return")
    public String markAsReturned(@RequestParam("loanId") int loanId) {
        Loan loan = loanService.findById(loanId);
        loan.setReturned(true);
        loan.setActualReturnDate(LocalDate.now());
        loanService.save(loan);
        // Αν θέλεις, άλλαξε και την κατάσταση του βιβλίου σε διαθέσιμο
        Book book = loan.getBook();
        book.setAvailable(true);
        bookService.save(book);

        return "redirect:/loans/list";
    }

    // add mapping for "/save"
    @PostMapping("/save")
    public String saveLoan(@Valid @ModelAttribute("loan") Loan theLoan,
                           BindingResult bindingResult,
                           Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("members", memberService.findAll());
            model.addAttribute("books", bookService.findAll().stream()
                    .filter(Book::isAvailable)
                    .collect(Collectors.toList()));
            return "loans/loan-form";
        }

        Member selectedMember = memberService.findById(theLoan.getMember().getId());
        Book selectedBook = bookService.findById(theLoan.getBook().getId());

        theLoan.setMember(selectedMember);
        theLoan.setBook(selectedBook);

        if (theLoan.getBorrowDate().isAfter(theLoan.getReturnDate())) {
            model.addAttribute("members", memberService.findAll());
            model.addAttribute("books", bookService.findAll()
                    .stream().filter(Book::isAvailable)
                    .collect(Collectors.toList()));
            model.addAttribute("errorMessage",
                    "Η ημερομηνία επιστροφής πρέπει να είναι μετά την ημερομηνία δανεισμού");
            return "loans/loan-form";
        }

        selectedBook.setAvailable(false);
        bookService.save(selectedBook);

        loanService.save(theLoan);
        redirectAttributes.addFlashAttribute("successMessage", "Το βιβλίο δανείστηκε επιτυχώς!");
        return "redirect:/loans/list";
    }



    @GetMapping("/delete")
    public String delete(@RequestParam("loanId") int theId){
        // delete loan
        loanService.deleteById(theId);

        // redirect to /loans/list
        return "redirect:/loans/list";
    }

}
