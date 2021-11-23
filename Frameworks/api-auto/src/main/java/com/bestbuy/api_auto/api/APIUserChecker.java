package com.bestbuy.api_auto.api;

import com.bestbuy.api_auto.AssertUtil;
import com.bestbuy.api_auto.Util;
import org.hamcrest.Matcher;

/**
 * This class contains all the verifications functions related to API Response 
 *
 */
public class APIUserChecker {

    private APIUser apiUser;
    private AssertUtil assertUtil;
    
    /**
     * Initializes {@link APIUser} class
     * @param apiUser : {@link APIUser}
     */
    public APIUserChecker(APIUser apiUser) {
        this.apiUser = apiUser;
        this.assertUtil = apiUser.getAssertUtil();
    }
    
    /**
     * Validates the response code from end-point with the expected response
     * @param responseCode : Expected Response code
     * @return : {@link APIUser}
     */
    public APIUser responseCode(int responseCode, String message) {
        this.assertUtil.verify(apiUser.getResponse().getStatusCode(), responseCode, "FAILED: " + message);
        Util.getLogger().info("PASSED: " + message + ". Response code is " + responseCode);
        
        return this.apiUser;
    }
    
    /**
     * Validates response body as per defined path and throws exception immediately if occurs
     * @param path : location to response body
     * @param matcher : {@link Matcher} to compare
     * @param message : Message to be displayed
     * @return
     */
    public <T> APIUser responseBody(String path, Matcher<T> matcher, String message) {
    	apiUser.getAssertUtil().verify(matcher.matches(apiUser.getResponse().getResponseBody(path)), true, message);
    	Util.getLogger().info("PASSED: " + message);
    	
        return this.apiUser;
    }
}
