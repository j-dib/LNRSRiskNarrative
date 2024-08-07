package com.lnrs.risknarrative.company_search.helpers;


/*
 * 
 * Bad Request Object Helper that holds information about the bad requests returned
 * 
 */

public class BadRequest extends Exception {
    public BadRequest() {
    }

    public BadRequest(String message) {
        super(message);
    }
}

