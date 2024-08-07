package com.lnrs.risknarrative.company_search.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 
 * Company Object model representing the variables of the Company.
 * The variables used are limited to the task requirements
 * 
 */

@ToString
@Setter
@Getter
@Data
public class Company {

    private Integer Id;

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

	private LocalDateTime createdDateTime;

    
}
