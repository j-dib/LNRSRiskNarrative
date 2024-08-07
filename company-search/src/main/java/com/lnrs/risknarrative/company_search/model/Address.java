package com.lnrs.risknarrative.company_search.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/*
 * 
 * Address Object model representing the variables of the Address returned.
 * The variables used are limited to the task requirements
 * 
 */

@Data
@JsonInclude(Include.NON_NULL)
public class Address {

    private Integer Id;

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("premises")
    private String premises;

    @JsonProperty("address_line_1")
    private String addressLine1;
   
    @JsonProperty("country")
    private String country;

    private LocalDateTime createdDateTime;



    
}
