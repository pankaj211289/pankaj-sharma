package com.bestbuy.api_auto.lib.restassuredimpl;

import com.bestbuy.api_auto.lib.IAPI;
import com.bestbuy.api_auto.lib.IResponse;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * Implements {@link IAPI} via Rest-Assured
 *
 */
public class APIImpl implements IAPI {
	
	private RequestSpecification httpRequest;
    
    public APIImpl(String baseURL) {
        RestAssured.baseURI = baseURL;
    }
    
    @Override
    public IResponse requestGet(String relativeURL) {
    	httpRequest = RestAssured.given();
        return new ResponseImpl(httpRequest.get(relativeURL));
    }

    @Override
    public IResponse requestPost(String relativeURL, String body) {
        httpRequest = RestAssured.given();
        if(body != null && !body.equals("")) {
            httpRequest.body(body);
        }
        httpRequest.header("Content-Type", "application/json")
                .header("Accept", "application/json");

        return new ResponseImpl(httpRequest.post(relativeURL));
    }

    @Override
    public IResponse requestPatch(String relativeURL, String body) {
        httpRequest = RestAssured.given();
        if(body != null && !body.equals("")) {
            httpRequest.body(body);
        }
        httpRequest.header("Content-Type", "application/json")
                .header("Accept", "application/json");

        return new ResponseImpl(httpRequest.patch(relativeURL));
    }

    @Override
    public IResponse requestDelete(String relativeURL) {
        httpRequest = RestAssured.given();

        return new ResponseImpl(httpRequest.delete(relativeURL));
    }


}
