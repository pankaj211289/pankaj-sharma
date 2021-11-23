package com.bestbuy.api_auto.stepdefs;

import io.cucumber.java.Before;

public class SetUp {
    private static String uniqueNumber;

    @Before
    public void setUp() {
        uniqueNumber = String.valueOf(System.nanoTime());
    }

    public static String getUniqueNumber() {
        return uniqueNumber;
    }
}
