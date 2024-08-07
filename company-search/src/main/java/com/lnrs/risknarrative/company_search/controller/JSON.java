package com.lnrs.risknarrative.company_search.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 
 * JSON Object Controller that serves as a "String Setter"
 * 
 */


public class JSON {
    public static String setJsonString(Object content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(content);
            return jsonInString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}