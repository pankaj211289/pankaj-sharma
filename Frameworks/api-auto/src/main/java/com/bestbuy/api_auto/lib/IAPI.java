package com.bestbuy.api_auto.lib;

/**
 * Defines API's method to implement
 *
 */
public interface IAPI {

	/**
	 * GET request to specified end-point
	 * @param relativeURL : location of end-point
	 * @return : Response from end-point
	 */
    public IResponse requestGet(String relativeURL);

	public IResponse requestPost(String relativeURL, String body);

	public IResponse requestPatch(String relativeURL, String body);

	public IResponse requestDelete(String relativeURL);
}
