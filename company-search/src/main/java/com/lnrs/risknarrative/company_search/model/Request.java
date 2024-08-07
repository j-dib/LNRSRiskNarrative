package com.lnrs.risknarrative.company_search.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Data

/*
 * 
 * Search Request model representing the variables of the Search Request information.
 * The variables used are limited to the task requirements
 * 
 */

public class Request {
   
    private String companyName;
    private String companyNumber;

}