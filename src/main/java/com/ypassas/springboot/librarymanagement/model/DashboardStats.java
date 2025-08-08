package com.ypassas.springboot.librarymanagement.model;

public class DashboardStats {

	private long totalBooks;
	private long availableBooks;
	private long totalMembers;
	private long activeLoans;
	private long overdueLoans;

	public DashboardStats() {
	}

	public DashboardStats(long totalBooks, long availableBooks, long totalMembers, long activeLoans, long overdueLoans) {
		this.totalBooks = totalBooks;
		this.availableBooks = availableBooks;
		this.totalMembers = totalMembers;
		this.activeLoans = activeLoans;
		this.overdueLoans = overdueLoans;
	}

	public long getTotalBooks() {
		return totalBooks;
	}

	public void setTotalBooks(long totalBooks) {
		this.totalBooks = totalBooks;
	}

	public long getAvailableBooks() {
		return availableBooks;
	}

	public void setAvailableBooks(long availableBooks) {
		this.availableBooks = availableBooks;
	}

	public long getTotalMembers() {
		return totalMembers;
	}

	public void setTotalMembers(long totalMembers) {
		this.totalMembers = totalMembers;
	}

	public long getActiveLoans() {
		return activeLoans;
	}

	public void setActiveLoans(long activeLoans) {
		this.activeLoans = activeLoans;
	}

	public long getOverdueLoans() {
		return overdueLoans;
	}

	public void setOverdueLoans(long overdueLoans) {
		this.overdueLoans = overdueLoans;
	}
}
