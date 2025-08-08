package com.ypassas.springboot.librarymanagement.service;

import com.ypassas.springboot.librarymanagement.model.DashboardStats;
import com.ypassas.springboot.librarymanagement.model.Loan;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService{

	private final BookService bookService;
	private final MemberService memberService;
	private final LoanService loanService;

	public DashboardServiceImpl(BookService bookService, MemberService memberService, LoanService loanService) {
		this.bookService = bookService;
		this.memberService = memberService;
		this.loanService = loanService;
	}

	@Override
	public DashboardStats getDashboardStats() {
		long totalBooks = bookService.findAll().size();
		long availableBooks = bookService.findAll().stream()
				.filter(book -> book.isAvailable()).count();
		long totalMembers = memberService.findAll().size();

		List<Loan> allLoans = loanService.findAllByOrderByReturnedAscReturnDateAsc();
		long activeLoans = allLoans.stream().filter(loan -> !loan.isReturned()).count();
		long overdueLoans = allLoans.stream()
				.filter(loan -> !loan.isReturned() && loan.getReturnDate().isBefore(LocalDate.now())).count();

		return new DashboardStats(totalBooks, availableBooks, totalMembers, activeLoans, overdueLoans);
	}
}
