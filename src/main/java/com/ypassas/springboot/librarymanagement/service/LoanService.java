package com.ypassas.springboot.librarymanagement.service;

import com.ypassas.springboot.librarymanagement.model.Loan;

import java.util.List;

public interface LoanService {

    // new load or update
    Loan save(Loan loan);

    // find by id
    Loan findById(int id);

    // show every loan
    List<Loan> findAllByOrderByReturnedAscReturnDateAsc();

    void deleteById(int id);

    // return book
    Loan returnBook(int loadId);


}
