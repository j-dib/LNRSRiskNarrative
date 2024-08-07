package com.lnrs.risknarrative.company_search.model.truproxy;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lnrs.risknarrative.company_search.model.Company;

import lombok.Data;

/*
 * 
 * TruProxyAPI Company Search Response. This model is the object container for the company search response of the TruProxi API
 * 
 */



@Data
public class TruProxyAPICompanySearchResponse {

    @JsonProperty("page_number")
    private Boolean pageNumber;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("total_results")
    private int totalResults;
    
    @JsonProperty("items")
    private List<Company> items;
    
}
