package com.bestbuy.api_auto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Defines all the Utility functions
 * 
 */
public class Util {

	private static Logger logger;
	private static Properties applicationProperties;
	
	/**
	 * Reads the values as per defined key-value pairs in application data properties file
	 * @param propKey : Keys as per defined in properties file
	 * @return : Respective Value of Property Key
	 */
	public static String readApplicationData(String propKey) {
		if(applicationProperties == null) {
			applicationProperties = new Properties();
			try(InputStream input = new FileInputStream("application-data.properties")) {
				applicationProperties.load(input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return applicationProperties.getProperty(propKey);
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
