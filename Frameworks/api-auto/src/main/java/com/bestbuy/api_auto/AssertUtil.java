package com.bestbuy.api_auto;

import org.junit.Assert;

/**
 * Assertion Util for validation of results
 *
 */
public class AssertUtil {

	/**
	 * Verifies actual and expected result, throws exception immediately if there's any failure
	 * @param actual : Actual Result
	 * @param expected : Expected Result
	 * @param message : Message to be displayed
	 */
	public void verify(Object actual, Object expected, String message) {
		Assert.assertEquals(message, expected, actual);
	}
}
