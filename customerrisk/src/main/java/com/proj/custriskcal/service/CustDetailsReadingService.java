package com.proj.custriskcal.service;

import com.proj.custriskcal.dto.FinalResponse;
import com.proj.custriskcal.dto.ResponseDto;

public interface CustDetailsReadingService {

	String readData();

	ResponseDto riskCal(String monthYear);

	FinalResponse custriskcal();

}
