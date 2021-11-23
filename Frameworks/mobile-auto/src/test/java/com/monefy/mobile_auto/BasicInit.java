package com.monefy.mobile_auto;

import com.monefy.mobile_auto.config.Driver;

public class BasicInit {

    // Gets the OS and provide it to all the tests
    protected String getOS() {
        return Driver.getOS().name();
    }
}
