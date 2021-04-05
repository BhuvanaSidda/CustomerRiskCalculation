package com.proj.custriskcal.dto;

import java.time.LocalDate;

public class CustomerDetailsDto {

	private String partyKey;
	private String countryCode;
	private LocalDate partyOpenDate;
	public String getPartyKey() {
		return partyKey;
	}
	public void setPartyKey(String partyKey) {
		this.partyKey = partyKey;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public LocalDate getPartyOpenDate() {
		return partyOpenDate;
	}
	public void setPartyOpenDate(LocalDate partyOpenDate) {
		this.partyOpenDate = partyOpenDate;
	}
	
	
	
	

}
