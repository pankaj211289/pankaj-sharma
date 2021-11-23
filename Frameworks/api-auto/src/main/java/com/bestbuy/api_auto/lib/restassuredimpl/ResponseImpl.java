package com.bestbuy.api_auto.lib.restassuredimpl;

import java.util.List;

import com.bestbuy.api_auto.Util;
import com.bestbuy.api_auto.lib.IResponse;
import io.restassured.response.Response;

/**
 * Implements {@link IResponse} for API implementation using Rest-Assured 
 *
 */
public class ResponseImpl implements IResponse {

    private Response response;
    
    public ResponseImpl(Response response) {
        this.response = response;
    }
    
    @Override
    public void print() {
        Util.getLogger().info("Response Body: \n" + response.asString());
    }

    @Override
    public int getStatusCode() {
        return response.getStatusCode();
    }

    @Override
    public <T> T getResponseBody(Class clazz) {
        return (T) response.body().as(clazz);
    }

    @Override
    public <T> List<T> getResponseBodyAsList(String path) {
        return response.body().jsonPath().get(path);
    }

    @Override
    public <T> T getResponseBody(String path) {
        return response.body().jsonPath().get(path);
    }

	@Override
	public <T> Object getResponseBody(T[] t) {
		return response.body().as(t.getClass());
	}
}
