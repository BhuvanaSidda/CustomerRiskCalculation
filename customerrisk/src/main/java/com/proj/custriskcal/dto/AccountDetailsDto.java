package com.proj.custriskcal.dto;

import java.time.LocalDate;

public class AccountDetailsDto {

	private String accountKey;
	private String primaryPartyKey;
	private LocalDate actOpenDate;
	public String getAccountKey() {
		return accountKey;
	}
	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
	public String getPrimaryPartyKey() {
		return primaryPartyKey;
	}
	public void setPrimaryPartyKey(String primaryPartyKey) {
		this.primaryPartyKey = primaryPartyKey;
	}
	public LocalDate getActOpenDate() {
		return actOpenDate;
	}
	public void setActOpenDate(LocalDate actOpenDate) {
		this.actOpenDate = actOpenDate;
	}
	

	
}
