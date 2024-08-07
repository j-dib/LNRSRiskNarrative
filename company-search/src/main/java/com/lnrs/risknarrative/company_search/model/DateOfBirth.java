package com.lnrs.risknarrative.company_search.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


/*
 *
 * Date of Birth Object model representing the variables of the DoB information.
 *
 */


@Data
public class DateOfBirth {

	@JsonProperty("month")
	private int month;
	@JsonProperty("year")
	private int year;

}