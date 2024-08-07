package com.lnrs.risknarrative.company_search.model.truproxy;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lnrs.risknarrative.company_search.model.Links;
import com.lnrs.risknarrative.company_search.model.Officer;

import lombok.Data;



/*
 * 
 * TruProxyAPI Officer Search Response. This model is the object container for the officer search response of the TruProxi API
 * 
 */

@Data
public class TruProxyAPIOfficerSearchResponse {

	@JsonProperty("etag")
    private String etag;

    @JsonProperty("links")
    private Links links;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("items_per_page")
    private int itemsPerPage;

    @JsonProperty("items")
    private List<Officer> items;

    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("active_count")
    private int activeCount;
    
    @JsonProperty("resigned_count")
    private int resignedCount;

	@JsonProperty("inactive_count")
    private int inactiveCount;

    
}
