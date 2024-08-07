package com.lnrs.risknarrative.company_search.helpers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


/*
 * 
 * JSONResponse object helper returns the correspondant error when a bad request is returned
 * 
 */

public class JSONResponse {
      @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> onBadRequest(BadRequestException badRequestException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();
    }
}
