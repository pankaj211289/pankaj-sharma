package com.monefy.mobile_auto.config;

import com.google.common.base.Function;
import com.monefy.mobile_auto.config.Driver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class BasicAction {

    private static MobileDriver mobileDriver = Driver.getInstance();
    private static int fluentWaitMaxTimeOut = Integer.parseInt("30");

    public static void clickMobileElement(By by) {
        waitForElementToBeClickable(by).click();
    }

    public static void clickMobileElementByID(String id) {
        getMobileElementByID(id).click();
    }

    public static void enterText(By by, String text) {
        waitForElementToBeClickable(by).sendKeys(text);
    }

    public static String getMobileElementText(By by) {
        return waitForElementToBeDisplayed(by).getText();
    }

    public static List<WebElement> getMultiple(By by) {
        waitForElementToBeDisplayed(by);
        return mobileDriver.findElements(by);
    }

    public static boolean isMobileElementDisplayed(By by) {
        try {
            return waitForElement(by).isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }
    }

    public static void pressBack() {
        if(mobileDriver instanceof AndroidDriver) {
            ((AndroidDriver)mobileDriver).pressKey(new KeyEvent(AndroidKey.BACK));
        } else {
            // Not implemented yet!
            throw new IllegalStateException("Not implemented yet!");
        }
    }

    public static void pressEnter() {
        if(mobileDriver instanceof AndroidDriver) {
            ((AndroidDriver)mobileDriver).pressKey(new KeyEvent(AndroidKey.ENTER));
        } else {
            // Not implemented yet!
            throw new IllegalStateException("Not implemented yet!");
        }
    }

    private static WebElement getMobileElementByID(String id) {
        return waitForElementToBeClickableByID(id);
    }

    private static WebElement waitForElementToBeClickable(By by) {
        return waitForElement(mobileDriver -> {
            WebElement element = mobileDriver.findElement(by);
            if(element != null && element.isEnabled()) {
                return element;
            } else {
                return null;
            }
        });
    }

    private static WebElement waitForElementToBeClickableByID(String id) {
        return waitForElement(mobileDriver -> {
            WebElement element = ((MobileDriver)mobileDriver).findElementById(id);
            if(element != null && element.isEnabled()) {
                return element;
            } else {
                return null;
            }
        });
    }

    private static WebElement waitForElementToBeDisplayed(By by) {
        return waitForElement(mobileDriver -> {
            WebElement element = mobileDriver.findElement(by);
            if(element != null && element.isDisplayed()) {
                return element;
            } else {
                return null;
            }
        });
    }

    private static WebElement waitForElement(By by) {
        return waitForElement(mobileDriver -> {
            WebElement element = mobileDriver.findElement(by);
            if(element != null) {
                return element;
            } else {
                return null;
            }
        });
    }

    private static WebElement waitForElement(Function<WebDriver, WebElement> function) {
        return new FluentWait<WebDriver>(mobileDriver)
            .withTimeout(Duration.ofSeconds(fluentWaitMaxTimeOut))
            .pollingEvery(Duration.ofMillis(250))
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class)
            .until(function);
    }
}
