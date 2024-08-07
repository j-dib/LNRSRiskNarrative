package com.lnrs.risknarrative.company_search.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/*
 * 
 * Officer Object model representing the variables of the Officer information.
 * The variables used are limited to the task requirements
 * 
 */


@Data
public class Officer {

    private Integer Id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("officer_role")
    private String officerRole;

    @JsonProperty("appointed_on")
    private String appointedOn;

    @JsonProperty("resigned_on")
    private String resignedOn;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("date_of_birth")
    private DateOfBirth dateOfBirth;
	
	private LocalDateTime createdDateTime;



    
}
