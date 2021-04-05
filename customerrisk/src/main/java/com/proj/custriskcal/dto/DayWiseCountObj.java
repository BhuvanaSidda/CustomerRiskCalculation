package com.proj.custriskcal.dto;

import java.time.LocalDate;

public class DayWiseCountObj {
	
	private Integer count;
	
	private String transactionDate;

	private String transactionOriginDest;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionOriginDest() {
		return transactionOriginDest;
	}

	public void setTransactionOriginDest(String transactionOriginDest) {
		this.transactionOriginDest = transactionOriginDest;
	}
	
	
}
