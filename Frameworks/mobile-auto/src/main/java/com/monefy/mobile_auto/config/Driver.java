package com.monefy.mobile_auto.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.monefy.mobile_auto.util.Util;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class Driver {
	
	// Let's not to instantiate the class
	private Driver() {}
	
	private static ThreadLocal<MobileDriver> webDriver = new ThreadLocal<MobileDriver>() {
		@Override
		public MobileDriver initialValue() {
			try {
				return initAppiumDriver(getOS());
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
		}
	};

	public static OS getOS() {
		return OS.ANDROID;
	}
	
	private static MobileDriver initAppiumDriver(OS os) throws MalformedURLException {
		AndroidDriver<?> androidDriver;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("fullReset", true);
		
		if(os == OS.ANDROID) {
			capabilities.setCapability("platformName", "Android");
            capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
			capabilities.setCapability("app", System.getProperty("user.dir") +"/apps/monefyApp.apk");
            capabilities.setCapability("deviceName", "Android Emulator");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("disableAndroidWatchers", true);
	        capabilities.setCapability("appPackage", Util.readConfigProperty("appPackage"));
	        capabilities.setCapability("appActivity", Util.readConfigProperty("appActivity"));

			String ip = Util.readConfigProperty("ip");
			String port = Util.readConfigProperty("port");
			androidDriver = new AndroidDriver(new URL("http://" + ip + ":" + port + "/wd/hub"), capabilities);
			androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return androidDriver;
		} else if(os == OS.IOS) {
			// Still need to put stub regarding IOS
			throw new IllegalStateException("Not implemented yet!");
		}

		throw new IllegalStateException("OS not supported!");
	}
	
	public static MobileDriver getInstance() {
		return webDriver.get();
	}
	
	public static void close(MobileDriver driver) {
		webDriver.remove();
	}
	
	public enum OS {
		IOS, ANDROID;
	}
}
