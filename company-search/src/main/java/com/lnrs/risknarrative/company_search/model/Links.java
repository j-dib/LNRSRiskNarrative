package com.lnrs.risknarrative.company_search.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/*
 * 
 * Links Object model representing the variables of the Links.
 * 
 */


@Data
public class Links {

    @JsonProperty("self")
    private String self;
    
    @JsonProperty("officer")
    private OfficerResponse officer;

}