# Company Search RESTful API implementation SpringBoot endpoint

This application leverages the benefits of the SpringBoot JAVA framework in order to utilise the TruProxy API
for returning company and officer information via a JSON-based API Search.

* The Testing class can be found in src/test (LNRSCompanySearchAPITest.java) 
* Only active officers (`resigned_on` is not present) are returned
* The API key is passed in via header `x-api-key`
* The API is accessible via the `/company` API uri
* A detailed unit testing class is made available
* The h2 Database instance is stored in memory at this stage (volatile), and can be accessed via /h2-console
* Database credentials are stored in the application.properties file (src/main/resources)


## Issues

The API is fully functional, however, due to time and availability constraints, the database functionality has been disabled.

In order to enable the DB functionality, the errors are to be resolved:
* H2 Database Storage: This has been stopped as a Bean error is returned. This is returned because the interface implementation has not been coupled with a Bean to fulfill the requirements (This error is returned within the OfficerController). The solution is to instantiate a Bean and configure it accordingly.
* H2 Database Logic: The Insert logic has been implemented, but the read, is yet to be implemented within the impl. Class (this is simply achieved by implementing the interface's read method and reusing it.
* Amending the Search class to store and read from the h2 database

## Next Steps

Future implementation would include the below:

* MSSQL Database to replace the h2 demo database used. This can be achieved by setting up a MYSQL instance on a virtual/cloud server, and configuring the tables (as they cannot be dynamically configured the same way h2 tables are).
* API Auth header to be amended to include user-specific API keys and restriction parameters (This would be implemented by storing the API keys in a database (properly handling the encryption), and enabling the code to check whether the Key is valid or not by accessing the DB table).
* More parameters such as `active` to be included to filter out inactive companies (This could be achieved by amending the API's Search class to also check for other parameters such is active, or other custom parameters).
* Using a more advanced and automated Unit testing tool such as the Postman in an automated enviorment where the resting results are stored within a database and returned to the system administrator.




## Goal
Create a company search application using Spring Boot 3.1.3 or higher.

Expose an endpoint that uses the `TruProxyAPI` to do a company and officer lookup 
via name or registration number.

## Criteria
* The result of the search is returned as JSON
* A request parameter has to be added to decide whether only active companies should be returned
* The officers of each company have to be included in the company details (new field `officers`) 
* Only include officers that are active (`resigned_on` is not present in that case)
* Paging can be ignored
* Please add unit tests and integrations tests, e.g. using WireMock to mock `TruProxyAPI` calls

**Expected Request**

* The name and registration/company number are passed in via body
* The API key is passed in via header `x-api-key`
* If both fields are provided `companyNumber` is used

<pre>
{
    "companyName" : "BBC LIMITED",
    "companyNumber" : "06500244"
}
</pre>

**Expected Response**

* Not all fields that are returned from `TruProxyAPI` are required.
The final JSON should look like this :

<pre>

{
    "total_results": 1,
    "items": [
        {
            "company_number": "06500244",
            "company_type": "ltd",
            "title": "BBC LIMITED",
            "company_status": "active",
            "date_of_creation": "2008-02-11",
            "address": {
                "locality": "Retford",
                "postal_code": "DN22 0AD",
                "premises": "Boswell Cottage Main Street",
                "address_line_1": "North Leverton",
                "country": "England"
            },
            "officers": [
                {
                    "name": "BOXALL, Sarah Victoria",
                    "officer_role": "secretary",
                    "appointed_on": "2008-02-11",
                    "address": {
                        "premises": "5",
                        "locality": "London",
                        "address_line_1": "Cranford Close",
                        "country": "England",
                        "postal_code": "SW20 0DP"
                    }
                }
            ]
        }
    ]
}
</pre>

## Bonus
* Save the companies (by `company_number`) and its officers and addresses in a database 
and return the result from there if the endpoint is called with `companyNumber`.

 
## Example API Requests

**Search for Company:**  
`https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Search?Query={search_term}`

<details>
  <summary>Response Example</summary>

  <pre>
  {
    "page_number": 1,
    "kind": "search#companies",
    "total_results": 20,
    "items": [
        {
            "company_status": "active",
            "address_snippet": "Boswell Cottage Main Street, North Leverton, Retford, England, DN22 0AD",
            "date_of_creation": "2008-02-11",
            "matches": {
                "title": [
                    1,
                    3
                ]
            },
            "description": "06500244 - Incorporated on 11 February 2008",
            "links": {
                "self": "/company/06500244"
            },
            "company_number": "06500244",
            "title": "BBC LIMITED",
            "company_type": "ltd",
            "address": {
                "premises": "Boswell Cottage Main Street",
                "postal_code": "DN22 0AD",
                "country": "England",
                "locality": "Retford",
                "address_line_1": "North Leverton"
            },
            "kind": "searchresults#company",
            "description_identifier": [
                "incorporated-on"
            ]
        }]
  }
  </pre>
</details>

**Get Company Officers:**  
`https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Officers?CompanyNumber={number}`
<details>
  <summary>Response Example</summary>

  <pre>
  {
    "etag": "6dd2261e61776d79c2c50685145fac364e75e24e",
    "links": {
        "self": "/company/10241297/officers"
    },
    "kind": "officer-list",
    "items_per_page": 35,
    "items": [
        {
            "address": {
                "premises": "The Leeming Building",
                "postal_code": "LS2 7JF",
                "country": "England",
                "locality": "Leeds",
                "address_line_1": "Vicar Lane"
            },
            "name": "ANTLES, Kerri",
            "appointed_on": "2017-04-01",
            "resigned_on": "2018-02-12",
            "officer_role": "director",
            "links": {
                "officer": {
                    "appointments": "/officers/4R8_9bZ44w0_cRlrxoC-wRwaMiE/appointments"
                }
            },
            "date_of_birth": {
                "month": 6,
                "year": 1969
            },
            "occupation": "Finance And Accounting",
            "country_of_residence": "United States",
            "nationality": "American"
        }]
  }
  </pre>
</details>

## API documentation

**Authentication:**\
Use the API key provided in your request header when calling the endpoints. <br>
Example: curl -s -H 'x-api-key: xxxxxxxxxxxxx' "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Officers?CompanyNumber=10241297"<br>

*API credentials will be provided seperately*

## Do not check the API Key into the repository!

## Flow

![Wireframe](https://raw.githubusercontent.com/TruNarrative/spring-exercise/main/spring_exercise.png)
