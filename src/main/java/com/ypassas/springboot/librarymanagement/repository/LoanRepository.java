package com.ypassas.springboot.librarymanagement.repository;

import com.ypassas.springboot.librarymanagement.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

	List<Loan> findAllByOrderByReturnedAscReturnDateAsc();

}
