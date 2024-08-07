package com.lnrs.risknarrative.company_search.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/*
 * 
 * Company Data Object model representing the variables of the Company Data.
 * The variables used are limited to the task requirements
 * 
 */


@Data
@JsonInclude(Include.NON_NULL)
public class CompanyData {
	
	@JsonProperty("company_number")
    private String companyNumber;
	@JsonProperty("company_type")
    private String companyType;
	@JsonProperty("title")
    private String title;
	@JsonProperty("company_status")
    private String companyStatus;
	@JsonProperty("date_of_creation")
    private String dateOfCreation;
	@JsonProperty("address")
    private Address address;
	@JsonProperty("officers")
    private List<OfficerSearch> officers;
}