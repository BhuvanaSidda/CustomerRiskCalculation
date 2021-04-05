package com.proj.custriskcal.dto;

import java.time.LocalDate;

public class TransactionsDetailsDto {

	private String transferKey;
	private String accountKey;
	private Double transactionAmount;
	private String transactionType;
	private String transactionOriginDest;
	private LocalDate transactionDate;
	public String getTransferKey() {
		return transferKey;
	}
	public void setTransferKey(String transferKey) {
		this.transferKey = transferKey;
	}
	public String getAccountKey() {
		return accountKey;
	}
	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionOriginDest() {
		return transactionOriginDest;
	}
	public void setTransactionOriginDest(String transactionOriginDest) {
		this.transactionOriginDest = transactionOriginDest;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	
}
