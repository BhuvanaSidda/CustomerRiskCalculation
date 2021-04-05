package com.proj.custriskcal.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerAccountDetails {

	@Id
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
