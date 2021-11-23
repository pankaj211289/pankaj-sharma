package com.bestbuy.api_auto.lib;

import java.util.List;

/**
 * Defines functions to return as response
 *
 */
public interface IResponse {

    /**
     * Outputs response
     */
    public void print();
    
    /**
     * Status code of response
     * @return : Response code
     */
    public int getStatusCode();
    
    /**
     * Returns List at specified path
     * @param path : location in response body
     */
    public <T> List<T> getResponseBodyAsList(String path);

    /**
     * Returns value at specified path
     * @param path : location in response body
     */
    public <T> T getResponseBody(String path);

    /**
     * Returns Object
     */
    public <T> T getResponseBody(Class clazz);
    
    /**
     * Converts response JSON body as java object
     * @param t : Type of class
     * @return : response body as object
     */
    public <T> Object getResponseBody(T[] t);
}
