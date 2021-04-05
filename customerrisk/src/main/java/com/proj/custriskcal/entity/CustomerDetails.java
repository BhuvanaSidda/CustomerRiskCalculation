package com.proj.custriskcal.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerDetails {

	@Id
	private String partyKey;
	private String residentialCountryCd;
	private LocalDate partyOpenDate;

	public String getPartyKey() {
		return partyKey;
	}

	public void setPartyKey(String partyKey) {
		this.partyKey = partyKey;
	}

	public String getResidentialCountryCd() {
		return residentialCountryCd;
	}

	public void setResidentialCountryCd(String residentialCountryCd) {
		this.residentialCountryCd = residentialCountryCd;
	}

	public LocalDate getPartyOpenDate() {
		return partyOpenDate;
	}

	public void setPartyOpenDate(LocalDate partyOpenDate) {
		this.partyOpenDate = partyOpenDate;
	}

}
