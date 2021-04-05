package com.proj.custriskcal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.proj.custriskcal.dto.FinalResponse;
import com.proj.custriskcal.dto.ResponseDto;
import com.proj.custriskcal.service.CustDetailsReadingService;

@RestController
@RequestMapping("/custdetails")
public class CustDetailsReadingController {

	private static final Logger logger = LoggerFactory.getLogger(CustDetailsReadingController.class);

	@Autowired
	CustDetailsReadingService custDetailsReadingService;

	@GetMapping
	public String readDatafromXls() {
		logger.info(":::::Reading data controller:::");
		return custDetailsReadingService.readData();
	}

	@GetMapping("/riskcal")
	public ResponseDto riskCal(@RequestParam String monthYear) {
		logger.info(":::::risk cal data controller:::");
		return custDetailsReadingService.riskCal(monthYear);
	}


	@GetMapping("/custriskcal")
	public FinalResponse custriskcal() {
		logger.info(":::::risk cal data controller:::");
		return custDetailsReadingService.custriskcal();
		//ModelAndView map=new ModelAndView("index");
	}

}
