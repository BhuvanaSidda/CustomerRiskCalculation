package com.proj.custriskcal.dto;

import java.util.List;

public class CustomerRiskRespose {

	private String customer;
	private String accountNumber;
	//private String country;
	private List<MonthResponse> monthRes;

	

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	

	/*
	 * public String getCountry() { return country; }
	 * 
	 * public void setCountry(String country) { this.country = country; }
	 */
	public List<MonthResponse> getMonthRes() {
		return monthRes;
	}

	public void setMonthRes(List<MonthResponse> monthRes) {
		this.monthRes = monthRes;
	}

}
