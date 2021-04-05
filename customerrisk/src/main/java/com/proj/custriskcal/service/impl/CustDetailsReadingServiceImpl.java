package com.proj.custriskcal.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.custriskcal.dto.AccountDetailsDto;
import com.proj.custriskcal.dto.CountriesDetailsDto;
import com.proj.custriskcal.dto.CustomerDetailsDto;
import com.proj.custriskcal.dto.CustomerRiskRespose;
import com.proj.custriskcal.dto.DayWiseCountObj;
import com.proj.custriskcal.dto.DayWiseCountObjByAccount;
import com.proj.custriskcal.dto.FinalResponse;
import com.proj.custriskcal.dto.MonthResponse;
import com.proj.custriskcal.dto.Response;
import com.proj.custriskcal.dto.ResponseDto;
import com.proj.custriskcal.dto.TransactionsDetailsDto;
import com.proj.custriskcal.entity.CountryDetails;
import com.proj.custriskcal.entity.CustomerAccountDetails;
import com.proj.custriskcal.entity.CustomerDetails;
import com.proj.custriskcal.entity.TransactionDetails;
import com.proj.custriskcal.repository.CountryDetailsRepository;
import com.proj.custriskcal.repository.CustomerAccountsRepository;
import com.proj.custriskcal.repository.CustomerDetailsRepository;
import com.proj.custriskcal.repository.TransactionRepository;
import com.proj.custriskcal.service.CustDetailsReadingService;

@Service
public class CustDetailsReadingServiceImpl implements CustDetailsReadingService {

	private static final Logger logger = LoggerFactory.getLogger(CustDetailsReadingServiceImpl.class);

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	CountryDetailsRepository countryDetailsRepository;

	@Autowired
	CustomerAccountsRepository customerAccountsRepository;

	@Autowired
	CustomerDetailsRepository customerDetailsRepository;

	@Override
	public String readData() {
		logger.info("::::data reading service::");

		// accountdetails List
		// List<AccountDetailsDto> accountlist = getAccountDetails();

		// countries details list
		// List<CountriesDetailsDto> countriesList = getCountriesList();

		// customerDetails details list
		List<CustomerDetailsDto> customerList = getCustomerDetails();

		// transaction details list
		// List<TransactionsDetailsDto> transactionList = getTransactionDetails();

		return "Success";
	}

	private List<CustomerDetailsDto> getCustomerDetails() {
		File file1 = new File("C:\\Users\\DELL\\Desktop\\Customers.xlsx");
		List<CustomerDetailsDto> list = new ArrayList<CustomerDetailsDto>();
		try {

			FileInputStream fis = new FileInputStream(file1); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file

			while (itr.hasNext()) {
				Row row = itr.next();
				if (row.getRowNum() == 0) {
					continue; // just skip the rows if row number is 0 or 1
				}
				CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
				CustomerDetails customerDetails = new CustomerDetails();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// System.out.println(cell.getStringCellValue());

					switch (cell.getColumnIndex()) {
					case 0: // field that represents part_key
						customerDetailsDto.setPartyKey(cell.getStringCellValue());
						customerDetails.setPartyKey(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case 1: //

						customerDetailsDto.setCountryCode(cell.getStringCellValue());
						customerDetails.setResidentialCountryCd(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case 2:
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
						Date date = cell.getDateCellValue();
						Instant instant = date.toInstant();
						LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
						customerDetailsDto.setPartyOpenDate(localDate);
						customerDetails.setPartyOpenDate(localDate);
						System.out.print(localDate + "\t\t\t");
					default:

					}

				}
				//BeanUtils.copyProperties(customerDetailsDto, customerDetails);
				customerDetailsRepository.save(customerDetails);
				list.add(customerDetailsDto);

				System.out.println("");
			}
			System.out.println("list size is :::  " + list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private List<CountriesDetailsDto> getCountriesList() {
		File file1 = new File("C:\\Users\\DELL\\Desktop\\countries.xlsx");
		List<CountriesDetailsDto> list = new ArrayList<CountriesDetailsDto>();
		try {

			FileInputStream fis = new FileInputStream(file1); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file

			while (itr.hasNext()) {
				Row row = itr.next();
				if (row.getRowNum() == 0) {
					continue; // just skip the rows if row number is 0 or 1
				}
				CountriesDetailsDto countriesDetailsDto = new CountriesDetailsDto();
				CountryDetails countryDetails = new CountryDetails();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// System.out.println(cell.getStringCellValue());

					switch (cell.getColumnIndex()) {
					case 0: // field that represents part_key

						countriesDetailsDto.setListId(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case 1: //
						countryDetails.setCountryName(cell.getStringCellValue());
						countriesDetailsDto.setEntryKey(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;

					default:

					}

				}

				countryDetailsRepository.save(countryDetails);
				list.add(countriesDetailsDto);

				System.out.println("");
			}
			System.out.println("list size is :::  " + list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private List<AccountDetailsDto> getAccountDetails() {
		File file1 = new File("C:\\Users\\DELL\\Desktop\\Customer_Accounts.xlsx");
		List<AccountDetailsDto> list = new ArrayList<AccountDetailsDto>();
		try {

			FileInputStream fis = new FileInputStream(file1); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file

			while (itr.hasNext()) {
				Row row = itr.next();
				if (row.getRowNum() == 0) {
					continue; // just skip the rows if row number is 0 or 1
				}
				CustomerAccountDetails CustomerAccountDetails = new CustomerAccountDetails();
				AccountDetailsDto accountDetails = new AccountDetailsDto();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// System.out.println(cell.getStringCellValue());

					switch (cell.getColumnIndex()) {
					case 0: // field that represents part_key
						accountDetails.setAccountKey(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case 1: //

						accountDetails.setPrimaryPartyKey(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case 2:
						Date date = cell.getDateCellValue();

						Instant instant = date.toInstant();
						LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
						accountDetails.setActOpenDate(localDate);
						System.out.print(localDate + "\t\t\t");

					default:

					}

					BeanUtils.copyProperties(accountDetails, CustomerAccountDetails);
					customerAccountsRepository.save(CustomerAccountDetails);
					list.add(accountDetails);

				}

				System.out.println("");
			}
			System.out.println("list size is :::  " + list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private List<TransactionsDetailsDto> getTransactionDetails() {
		File file1 = new File("C:\\Users\\DELL\\Desktop\\Customer_Transactions.xlsx");
		List<TransactionsDetailsDto> list = new ArrayList<TransactionsDetailsDto>();
		try {

			FileInputStream fis = new FileInputStream(file1); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file

			while (itr.hasNext()) {
				Row row = itr.next();
				if (row.getRowNum() == 0) {
					continue; // just skip the rows if row number is 0 or 1
				}
				TransactionsDetailsDto transactionsDetails = new TransactionsDetailsDto();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						transactionsDetails.setTransferKey(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case 1:
						transactionsDetails.setAccountKey(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case 2:
						transactionsDetails.setTransactionAmount(cell.getNumericCellValue());
						System.out.print(cell.getNumericCellValue() + "\t\t\t");
						break;
					case 3:
						transactionsDetails.setTransactionType(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case 4:
						transactionsDetails.setTransactionOriginDest(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case 5:
						Date date = cell.getDateCellValue();

						Instant instant = date.toInstant();
						LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
						transactionsDetails.setTransactionDate(localDate);
						System.out.print(localDate + "\t\t\t");
					default:

					}
					TransactionDetails transDetails = new TransactionDetails();
					BeanUtils.copyProperties(transactionsDetails, transDetails);
					transactionRepository.save(transDetails);
					list.add(transactionsDetails);
				}

				System.out.println("");
			}
			System.out.println("list size is :::  " + list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public ResponseDto riskCal(String monthYear) {
		logger.info("monthYear:" + monthYear);

		ResponseDto resList = new ResponseDto();
		List<Response> liRes = new ArrayList<>();
		List<CountryDetails> countryList = countryDetailsRepository.findAll();
		logger.info("countryList:" + countryList);
		for (CountryDetails list : countryList) {
			Response res = new Response();
			Integer objINN = transactionRepository.getTransactionDetails(list.getCountryName(), "INN",
					monthYear.concat("%"));
			Integer objOUT = transactionRepository.getTransactionDetails(list.getCountryName(), "OUT",
					monthYear.concat("%"));
			Double objINNamount = transactionRepository.getTransactionAmount(list.getCountryName(), "INN",
					monthYear.concat("%"));
			Double objOUTamount = transactionRepository.getTransactionAmount(list.getCountryName(), "OUT",
					monthYear.concat("%"));
			if (objINNamount == null)
				objINNamount = 0.0;
			if (objOUTamount == null)
				objOUTamount = 0.0;
			logger.info("amount:" + objINNamount + " " + objOUTamount);
			logger.info("objInn:" + objINN + " objOUT:" + objOUT);

			List<Object[]> dayWiseCountObj = transactionRepository.getDayWiseCount(monthYear);
			List<DayWiseCountObj> dayWisecountList = new ArrayList<>();
			for (Object[] obj : dayWiseCountObj) {
				DayWiseCountObj d = new DayWiseCountObj();
				d.setCount(Integer.valueOf(obj[0].toString()));
				d.setTransactionDate(obj[1].toString());
				d.setTransactionOriginDest(obj[2].toString());
				dayWisecountList.add(d);
			}

			List<DayWiseCountObj> listobj = dayWisecountList.stream()
					.filter(p -> p.getTransactionOriginDest().equals(list.getCountryName()))
					.filter(p -> p.getTransactionDate().contains(monthYear)).collect(Collectors.toList());
			int highCount = 0;
			int mediumCount = 0;
			int lowCount = 0;

			for (DayWiseCountObj obj : listobj) {
				if (obj.getCount() > 10)
					lowCount++;
				if (obj.getCount() > 20)
					highCount++;
				if (obj.getCount() > 10 || obj.getCount() < 20)
					mediumCount++;
			}

			if ((objINN > 10 || objOUT > 10) && (objINNamount > 1000 && objOUTamount > 800) && highCount > 0) {
				logger.info(list.getCountryName() + " HighRisk");

				res.setCountry(list.getCountryName());
				res.setRiskStatus("HighRisk");

			} else if ((objINN > 6 || objOUT > 6)
					&& ((objINNamount > 600 && objINNamount < 1000) && (objOUTamount > 500 && objOUTamount < 800))
					&& mediumCount > 0) {
				logger.info(list.getCountryName() + " MediumRisk");
				res.setCountry(list.getCountryName());
				res.setRiskStatus("MediumRisk");

			} else if ((objINN < 10 || objOUT < 10) && (objINNamount == null || objINNamount < 600)
					&& (objOUTamount == null || objOUTamount < 500) && lowCount == 0) {
				logger.info(list.getCountryName() + " LowRisk");

				res.setCountry(list.getCountryName());
				res.setRiskStatus("LowRisk");

			} else {
				res.setCountry(list.getCountryName());
				res.setRiskStatus("NoRisk");
			}

			liRes.add(res);
		}

		resList.setRes(liRes);
		return resList;
	}

	@Override
	public FinalResponse custriskcal() {

		FinalResponse finalResponse = new FinalResponse();
		Set<CustomerRiskRespose> listCustomerRiskRespose = new HashSet<>();
		List<CustomerAccountDetails> customerList = customerAccountsRepository.findAll();
		logger.info("CustomerList:" + customerList.size());

		for (CustomerAccountDetails list : customerList) {
			CustomerRiskRespose customerRiskRespose = new CustomerRiskRespose();
			CustomerAccountDetails customerAccountDetails = customerAccountsRepository.findById(list.getAccountKey())
					.get();
			//logger.info("customer id:"+customerAccountDetails.getPrimaryPartyKey());
			//CustomerDetails customerDetails=customerDetailsRepository.findById(customerAccountDetails.getPrimaryPartyKey()).get();
			customerRiskRespose.setCustomer(customerAccountDetails.getPrimaryPartyKey());
			customerRiskRespose.setAccountNumber(list.getAccountKey());
			//customerRiskRespose.setCountry(customerDetails.getResidentialCountryCd());
			List<MonthResponse> monthResList = new ArrayList<MonthResponse>();
			List<TransactionDetails> objListTransactionDetailsList1 = transactionRepository
					.findByAccountKey(list.getAccountKey());

			Set<String> dateSet = objListTransactionDetailsList1.stream()
					.map(p -> p.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy-MM")))
					.collect(Collectors.toSet());

			for (String obj : dateSet) {
				logger.info("dateSet date: " + obj);
				MonthResponse monthResponse = new MonthResponse();

				Integer objINN = transactionRepository.getTransactionDetailsByAccount(list.getAccountKey(), "INN",
						obj.concat("%"));
				Integer objOUT = transactionRepository.getTransactionDetailsByAccount(list.getAccountKey(), "OUT",
						obj.concat("%"));

				Double objINNamount = transactionRepository.getTransactionAmountByaccount(list.getAccountKey(), "INN",
						obj.concat("%"));
				Double objOUTamount = transactionRepository.getTransactionAmountByaccount(list.getAccountKey(), "OUT",
						obj.concat("%"));

				if (objINNamount == null)
					objINNamount = 0.0;
				if (objOUTamount == null)
					objOUTamount = 0.0;

				logger.info("objINN:" + objINN + " objOUT:" + objOUT + " objINNamount:" + objINNamount
						+ " objOUTamount:" + objOUTamount);

				List<Object[]> dayWiseCountObj = transactionRepository.getDayWiseCountByaccount(obj);
				List<DayWiseCountObjByAccount> dayWisecountList = new ArrayList<>();
				for (Object[] dayobj : dayWiseCountObj) {
					DayWiseCountObjByAccount d = new DayWiseCountObjByAccount();
					d.setCount(Integer.valueOf(dayobj[0].toString()));
					d.setTransactionDate(dayobj[1].toString());
					d.setAccountKey(dayobj[2].toString());
					dayWisecountList.add(d);
				}

				List<DayWiseCountObjByAccount> listobj = dayWisecountList.stream()
						.filter(p -> p.getAccountKey().equals(list.getAccountKey()))
						.filter(p -> p.getTransactionDate().contains(obj)).collect(Collectors.toList());
				int highCount = 0;
				int mediumCount = 0;
				int lowCount = 0;

				for (DayWiseCountObjByAccount countobj : listobj) {
					if (countobj.getCount() > 10)
						lowCount++;
					if (countobj.getCount() > 20)
						highCount++;
					if (countobj.getCount() > 10 || countobj.getCount() < 20)
						mediumCount++;
				}

				if ((objINN > 10 || objOUT > 10) && (objINNamount > 1000 && objOUTamount > 800) && highCount > 0) {
					logger.info(list.getAccountKey() + " HighRisk");

					monthResponse.setMonthYear(obj);
					monthResponse.setRiskStatus("HighRisk");

				} else if ((objINN > 6 || objOUT > 6)
						&& ((objINNamount > 600 && objINNamount < 1000) && (objOUTamount > 500 && objOUTamount < 800))
						&& mediumCount > 0) {
					logger.info(list.getAccountKey() + " MediumRisk");
					monthResponse.setMonthYear(obj);
					monthResponse.setRiskStatus("MediumRisk");

				} else if ((objINN < 10 || objOUT < 10) && (objINNamount < 600 && objOUTamount < 500)
						&& lowCount == 0) {
					logger.info(list.getAccountKey() + " LowRisk");

					monthResponse.setMonthYear(obj);
					monthResponse.setRiskStatus("LowRisk");

				} else {
					monthResponse.setMonthYear(obj);
					monthResponse.setRiskStatus("NoRisk");
				}
				logger.info("monthResponse:" + monthResponse.getMonthYear());
				monthResList.add(monthResponse);

				logger.info("monthResList:" + monthResList.size());
				customerRiskRespose.setMonthRes(monthResList);
				listCustomerRiskRespose.add(customerRiskRespose);
			}

		}

		finalResponse.setCustomerRiskResposeList(listCustomerRiskRespose);

		return finalResponse;
	}

}
