package com.lnrs.risknarrative.company_search.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lnrs.risknarrative.company_search.helpers.CompanySearchHelper;
import com.lnrs.risknarrative.company_search.model.CompanyData;
import com.lnrs.risknarrative.company_search.model.CompanySearchResponse;
import com.lnrs.risknarrative.company_search.model.OfficerSearch;
import com.lnrs.risknarrative.company_search.model.Request;

/*
 * 
 * Search Controller. This controller aces as a top-level controller that receives the "/company" requests and maps the correct response.
 * Header checking is also fulfilled at this stage
 * 
 */


@RestController
@RequestMapping(value = "/company")
public class Search {
	@Autowired
	CompanySearchHelper companySearch;



	private static final Logger masterLog = LogManager.getLogger(Search.class);

	@GetMapping
	public ResponseEntity<?> getCompany(@RequestBody Request body, @RequestHeader("x-api-key") String key)  {
		try {
			String result = null;
			if (body == null) {
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}

			if (body.getCompanyName() == null || body.getCompanyNumber() == null) {
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}

			if (body.getCompanyName().isEmpty() || body.getCompanyNumber().isEmpty()) {
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}

			CompanyData  comapanies = companySearch.getComapanies(body.getCompanyNumber());
			List<OfficerSearch> officers = companySearch.getOfficers(body.getCompanyNumber());
		
			CompanySearchResponse response = new CompanySearchResponse();
			response.setTotalResults(1);
			List<CompanyData> Search = new ArrayList<CompanyData>();
			comapanies.setOfficers(officers);
			Search.add(comapanies);
			response.setItems(Search);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}