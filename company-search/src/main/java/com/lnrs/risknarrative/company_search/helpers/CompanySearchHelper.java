package com.lnrs.risknarrative.company_search.helpers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnrs.risknarrative.company_search.helpers.HttpRequest.HttpRequestException;
import com.lnrs.risknarrative.company_search.model.Company;
import com.lnrs.risknarrative.company_search.model.CompanyData;
import com.lnrs.risknarrative.company_search.model.Officer;
import com.lnrs.risknarrative.company_search.model.OfficerSearch;
import com.lnrs.risknarrative.company_search.model.truproxy.TruProxyAPICompanySearchResponse;
import com.lnrs.risknarrative.company_search.model.truproxy.TruProxyAPIOfficerSearchResponse;

import lombok.Data;


/*
 * 
 * The CompanySearchHelper is one of the most important helpers of this application.
 * This helper fulfills the search API logic and information exchange. 
 * Its functions return the companyes in a CompanyData container, and ArrayList of OfficerSearch objects containing
 * information about the company officers
 * 
 */


@Data
@Service
public class CompanySearchHelper {

	private static final Logger logManager = LogManager.getLogger(CompanySearchHelper.class);
	private static final String COMPANIES_SEARCH_API_URL = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Search?Query=company";
	private static final String OFFICERS_SEARCH_API_URL = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Officers?CompanyNumber=";
	private static final String API_KEY = "PwewCEztSW7XlaAKqkg4IaOsPelGynw6SN9WsbNf";

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public CompanyData getComapanies(String companyNumber)
			throws JsonMappingException, JsonProcessingException, HttpRequestException {
		HttpRequest request = HttpRequest.get(COMPANIES_SEARCH_API_URL).header("x-api-key", API_KEY).connectTimeout(12000);
		TruProxyAPICompanySearchResponse companySearchAPIResponse = MAPPER.readValue(request.body(), TruProxyAPICompanySearchResponse.class);
		List<Company> items = companySearchAPIResponse.getItems();
		CompanyData response = new CompanyData();
		for (Company company : items) {
			if (company.getCompanyNumber().equals(companyNumber) && company.getCompanyStatus().equals("active")) {
				response.setAddress(company.getAddress());
				response.setCompanyNumber(company.getCompanyNumber());
				response.setCompanyStatus(company.getCompanyStatus());
				response.setCompanyType(company.getCompanyType());
				response.setDateOfCreation(company.getDateOfCreation());
				response.setTitle(company.getTitle());
				response.setAddress(company.getAddress());
			}
		}
		return response;
	}

	public List<OfficerSearch> getOfficers(String companyNumber)
			throws JsonMappingException, JsonProcessingException, HttpRequestException {
		HttpRequest request = HttpRequest.get(OFFICERS_SEARCH_API_URL + companyNumber).header("x-api-key", API_KEY)
				.connectTimeout(12000);
		
		TruProxyAPIOfficerSearchResponse response = MAPPER.readValue(request.body(), TruProxyAPIOfficerSearchResponse.class);
		List<Officer> items = response.getItems();
		List<OfficerSearch> officers = new ArrayList<OfficerSearch>();
		for (Officer officer : items) {
			if (officer.getResignedOn() != null) {
				OfficerSearch companySearchOfficer = new OfficerSearch();
				companySearchOfficer.setAddress(officer.getAddress());
				companySearchOfficer.setAppointedOn(officer.getAppointedOn());
				companySearchOfficer.setName(officer.getName());
				companySearchOfficer.setOfficerRole(officer.getOfficerRole());
				officers.add(companySearchOfficer);
			}
		}
		return officers;
	}
}