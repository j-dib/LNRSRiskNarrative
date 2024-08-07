package com.lnrs.risknarrative.company_search.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/*
 * 
 * Officer Response Object model representing the variables of the Officer Response information.
 * The variables used are limited to the task requirements
 * 
 */

@Data
public class OfficerResponse {

    @JsonProperty("appointments")
    private String appointments;


}
