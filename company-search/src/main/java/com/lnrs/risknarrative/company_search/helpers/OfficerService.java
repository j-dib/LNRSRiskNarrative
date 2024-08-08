package com.lnrs.risknarrative.company_search.helpers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lnrs.risknarrative.company_search.model.OfficerSearch;


/*
 * 
 * OfficerService interface helper that implements the database interaction functions
 * 
 */


@Service
public interface OfficerService {

    OfficerSearch saveOfficer (OfficerSearch officer);

    List<OfficerSearch> fetchOfficerList();

    void deleteOfficerSearch (long id);
    
}
