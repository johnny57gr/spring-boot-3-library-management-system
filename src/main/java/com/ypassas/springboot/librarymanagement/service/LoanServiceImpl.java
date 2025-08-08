package com.ypassas.springboot.librarymanagement.service;

import com.ypassas.springboot.librarymanagement.model.Loan;
import com.ypassas.springboot.librarymanagement.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService{

    private LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan save(Loan loan) {

        return loanRepository.save(loan);

    }

    @Override
    public Loan findById(int id) {
        Optional<Loan> result = loanRepository.findById(id);

        Loan theLoan = null;

        if(result.isPresent()) {
            theLoan = result.get();
        }
        else {
            throw new RuntimeException("Did not find loan id - " + id);
        }

        return theLoan;
    }

    @Override
    public List<Loan> findAllByOrderByReturnedAscReturnDateAsc() {
        return loanRepository.findAllByOrderByReturnedAscReturnDateAsc();
    }

    @Override
    public void deleteById(int id) {
        loanRepository.deleteById(id);
    }

    @Override
    public Loan returnBook(int loadId) {
        Loan loan = findById(loadId);
        loan.setReturned(true);
        loan.setActualReturnDate(LocalDate.now());
        loan.getBook().setAvailable(true);
        return loanRepository.save(loan);
    }
}
