package com.lnrs.risknarrative.company_search.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/*
 * 
 * Officer Search model representing the variables of the Officer Search information.
 * The variables used are limited to the task requirements
 * 
 */
@Data
@JsonInclude(Include.NON_NULL)
public class OfficerSearch {

    long id;
	
	@JsonProperty("name")
    private String name;
	@JsonProperty("officer_role")
    private String officerRole;
	@JsonProperty("appointed_on")
    private String appointedOn;
	@JsonProperty("address")
    private Address address;
}