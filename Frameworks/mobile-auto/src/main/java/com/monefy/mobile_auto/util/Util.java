package com.monefy.mobile_auto.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {

    private static Properties properties = new Properties();
    private static Logger logger;

    public static String readConfigProperty(String key) {
        try {
            InputStream input = new FileInputStream("config.properties");
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns logger to print messages
     * @return : {@link Logger}
     */
    public static Logger getLogger() {
        if(logger == null) {
            logger = Logger.getLogger(Util.class);
            PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");
        }

        return logger;
    }

}
