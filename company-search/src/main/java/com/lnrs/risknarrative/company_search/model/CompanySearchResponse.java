package com.lnrs.risknarrative.company_search.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/*
 * 
 * Company Search Response Object model representing the variables of the Company Search Response Data.
 * The variables used are limited to the task requirements
 * 
 */



@Setter
@Getter
@Data
public class CompanySearchResponse {


    @JsonProperty("total_results")
    private int totalResults;
    
    @JsonProperty("items")
    private List<CompanyData> items;
}
