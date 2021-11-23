package com.bestbuy.api_auto.api;

import com.bestbuy.api_auto.AssertUtil;
import com.bestbuy.api_auto.lib.IAPI;
import com.bestbuy.api_auto.lib.IResponse;
import com.bestbuy.api_auto.lib.restassuredimpl.APIImpl;

/**
 * API User class is used to perform all the API operation either using RestAssured or HTTPClient
 *
 */
public class APIUser {

    private final APIUserChecker apiUserChecker;
    private AssertUtil assertUtil;
    private final IAPI apiService;
    private IResponse response;
    private String relativeUrl;
    
    /**
     * Initialize Base URL of AUT
     * @param baseUrl : Base URL of application
     */
    public APIUser(String baseUrl) {
        // Initialize RestAssured library to perform API operations
        this.apiService = new APIImpl(baseUrl);
        this.apiUserChecker = new APIUserChecker(this);
    }
    
    /**
     * Verifies the API result
     * @return {@link APIUserChecker} to verify API operations
     */
    public APIUserChecker verify() {
        return apiUserChecker;
    }
    
    /**
     * Response from end-point
     * @return {@link IResponse}
     */
    public IResponse getResponse() {
        return this.response;
    }
    
    /**
     * Sets the defined URL relative to Base URL
     * @param relativeUrl : Path to resource after Base URL 
     * @return {@link APIUser}
     */
    public APIUser setRelativeUrl(String relativeUrl) {
    	this.relativeUrl = relativeUrl;
    	return this;
    }
    
    /**
     * @return : Relative Path to resource in Application, throws exception if it's not set
     */
    public String getRelativeUrl() {
    	if(this.relativeUrl != null) {
    		return this.relativeUrl;
    	}
    	
    	throw new IllegalStateException("Relative URL is not set");
    }
    
    /**
     * @return : Prints the response body of end-point
     */
    public APIUser printResponse() {
    	this.response.print();
    	return this;
    }
    
    /**
     * GET response of end-point
     * @return : {@link APIUser}
     */
    public APIUser requestGet() {
        this.response = apiService.requestGet(getRelativeUrl());
        return this;
    }

    /**
     * POST response of end-point
     * @param body : body of request
     * @return : {@link APIUser}
     */
    public APIUser requestPost(String body) {
        this.response = apiService.requestPost(getRelativeUrl(), body);
        return this;
    }

    /**
     * PATCH response of end-point
     * @param body : body of request
     * @return : {@link APIUser}
     */
    public APIUser requestPatch(String body) {
        this.response = apiService.requestPatch(getRelativeUrl(), body);
        return this;
    }

    /**
     * PATCH response of end-point
     * @return : {@link APIUser}
     */
    public APIUser requestDelete() {
        this.response = apiService.requestDelete(getRelativeUrl());
        return this;
    }
    
    /**
     * Initializes assertion utility
     * @return : {@link APIUser}
     */
    public APIUser initAssertUtil() {
    	if(assertUtil == null) {
    		assertUtil = new AssertUtil();
    	}
    	
    	return this;
    }
    
    /**
     * Instance of {@link AssertUtil}
     * @return {@link AssertUtil}
     */
    public AssertUtil getAssertUtil() {
    	if(assertUtil != null) {
    		return this.assertUtil;
    	}
        this.initAssertUtil();

        return this.assertUtil;
    }
}