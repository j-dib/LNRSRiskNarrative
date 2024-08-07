package com.lnrs.risknarrative.company_search;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnrs.risknarrative.company_search.controller.Search;
import com.lnrs.risknarrative.company_search.helpers.CompanySearchHelper;
import com.lnrs.risknarrative.company_search.model.CompanyData;
import com.lnrs.risknarrative.company_search.model.OfficerSearch;
import com.lnrs.risknarrative.company_search.model.Request;


/*
 * 
 * Application Unit Testing Class.
 * This class handles the individual test scenarios used to test and assess the applicaiton's performance and behaviour
 * 
 */



@WebMvcTest(Search.class)
class LNRSCompanySearchAPITest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CompanySearchHelper companySearch;

	@Mock
	private CompanyData companies;

	@Mock
	private List<OfficerSearch> officers;
	
	
// Test Scenario: Testing a bad request whereby the Company Name passed is NULL

	@Test
	public void testBadRequestNULL() throws Exception {
		Request request = new Request();
		request.setCompanyName(null);
		request.setCompanyNumber("");
		String reqBody = new ObjectMapper().writeValueAsString(request);
		mockMvc.perform(MockMvcRequestBuilders.get("/company").header("x-api-key", "abc").content(reqBody))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		Mockito.verify(this.companySearch, Mockito.times(0)).getComapanies(Mockito.any(String.class));
		Mockito.verify(this.companySearch, Mockito.times(0)).getOfficers(Mockito.any(String.class));
	}

	// Test Scenario: Testing a bad request whereby the request does not contain a body
	
	@Test
	public void testBadResultNoBody() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/company").header("x-api-key", "abc"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		Mockito.verify(this.companySearch, Mockito.times(0)).getComapanies(Mockito.any(String.class));
		Mockito.verify(this.companySearch, Mockito.times(0)).getOfficers(Mockito.any(String.class));
	}


	// Test Scenario: Testing a bad request that returns Server Error whereby the server receives and ASCII-encoded request

	@Test
	public void testServerError() throws Exception {
		Request request = new Request();
		request.setCompanyName("BBC LIMITED");
		request.setCompanyNumber("03054282");
		String reqBody = new ObjectMapper().writeValueAsString(request);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/company").header("x-api-key", "abc")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding(StandardCharsets.US_ASCII).content(reqBody))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}

	// Test Scenario: Testing a bad request whereby the header does not contain an authentication parameter

	@Test
	public void testBadRequestNoAuth() throws Exception {
		Request request = new Request();
		request.setCompanyName("BBC LIMITED");
		request.setCompanyNumber("03054282");
		String reqBody = new ObjectMapper().writeValueAsString(request);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/company").content(reqBody))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		Mockito.verify(this.companySearch, Mockito.times(0)).getComapanies(Mockito.any(String.class));
		Mockito.verify(this.companySearch, Mockito.times(0)).getOfficers(Mockito.any(String.class));
	}


	// Test Scenario: Testing a bad request whereby the header contains the wrong header paramneter x-api-key

	@Test
	public void testBadRequestWrongAuth() throws Exception {
		Request request = new Request();
		request.setCompanyName("BBC LIMITED");
		request.setCompanyNumber("03054282");
		String reqBody = new ObjectMapper().writeValueAsString(request);
		
		Mockito.when(this.companySearch.getComapanies(Mockito.any(String.class))).thenReturn(companies);
		Mockito.when(this.companySearch.getOfficers(Mockito.any(String.class))).thenReturn(officers);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/company").header("x-iap-key", "abc")
		.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
		.characterEncoding(StandardCharsets.UTF_8).content(reqBody)).andExpect(MockMvcResultMatchers.status().is4xxClientError());

		Mockito.verify(this.companySearch, Mockito.times(0)).getComapanies(Mockito.any(String.class));
		Mockito.verify(this.companySearch, Mockito.times(0)).getOfficers(Mockito.any(String.class));
	}

	// Test Scenario: Testing a successful scenario

	@Test
	public void testSuccess() throws Exception {
		Request request = new Request();
		request.setCompanyName("BBC LIMITED");
		request.setCompanyNumber("06500244");
		String reqBody = new ObjectMapper().writeValueAsString(request);
		
		Mockito.when(this.companySearch.getComapanies(Mockito.any(String.class))).thenReturn(companies);
		Mockito.when(this.companySearch.getOfficers(Mockito.any(String.class))).thenReturn(officers);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/company").header("x-api-key", "abc")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding(StandardCharsets.UTF_8).content(reqBody))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(this.companySearch, Mockito.times(1)).getComapanies(Mockito.any(String.class));
		Mockito.verify(this.companySearch, Mockito.times(1)).getOfficers(Mockito.any(String.class));
	}

		// Test Scenario: Testing a successful scenario with custom parameters

		@Test
		public void testSuccessCustomParams() throws Exception {
			Request request = new Request();
			request.setCompanyName("LEXISNEXIS RISK SOLUTIONS UK LIMITED");
			request.setCompanyNumber("07416642");
			String reqBody = new ObjectMapper().writeValueAsString(request);
			
			Mockito.when(this.companySearch.getComapanies(Mockito.any(String.class))).thenReturn(companies);
			Mockito.when(this.companySearch.getOfficers(Mockito.any(String.class))).thenReturn(officers);
			
			mockMvc.perform(MockMvcRequestBuilders.get("/company").header("x-api-key", "abc")
					.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
					.characterEncoding(StandardCharsets.UTF_8).content(reqBody))
					.andExpect(MockMvcResultMatchers.status().isOk());
	
			Mockito.verify(this.companySearch, Mockito.times(1)).getComapanies(Mockito.any(String.class));
			Mockito.verify(this.companySearch, Mockito.times(1)).getOfficers(Mockito.any(String.class));
		}
	

}
