package com.monefy.mobile_auto.util;

import org.testng.Assert;

public class AssertUtil {

    public static void verify(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
        Util.getLogger().info("SUCCESS : " + message);
    }

    public static void verifyTrue(boolean actual, String message) {
        Assert.assertTrue(actual, message);
        Util.getLogger().info("SUCCESS : " + message);
    }
}
