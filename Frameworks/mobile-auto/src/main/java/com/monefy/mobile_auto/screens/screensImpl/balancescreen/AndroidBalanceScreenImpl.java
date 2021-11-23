package com.monefy.mobile_auto.screens.screensImpl.balancescreen;

import com.monefy.mobile_auto.config.BasicAction;
import com.monefy.mobile_auto.screens.IBalanceScreen;
import com.monefy.mobile_auto.screens.IHomeScreen;
import com.monefy.mobile_auto.screens.screensImpl.homescreen.AndroidHomeScreenImpl;
import com.monefy.mobile_auto.util.AssertUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AndroidBalanceScreenImpl implements IBalanceScreen {

    private By balanceTitleBy = By.id("com.monefy.app.lite:id/balance_amount");
    private By transactionsBy = By.xpath("//*[@resource-id='com.monefy.app.lite:id/expandableListViewTransactions']/android.widget.RelativeLayout");
    private By totalAmountBy = By.id("com.monefy.app.lite:id/textViewWholeAmount");
    private By categoryNameBy = By.id("com.monefy.app.lite:id/textViewCategoryName");
    private By minimizeBy = By.id("com.monefy.app.lite:id/leftLinesImageView");

    @Override
    public IBalanceScreen verifyPage() {
        AssertUtil.verifyTrue(BasicAction.getMobileElementText(balanceTitleBy).contains("Balance"), "Title is displayed");
        return this;
    }

    @Override
    public IBalanceScreen validateEntry(String category, String amount) {
        for(WebElement webElement : BasicAction.getMultiple(transactionsBy)) {
            if(webElement.findElement(categoryNameBy).getText().contains(category)) {
                AssertUtil.verifyTrue(webElement.findElement(totalAmountBy).getText().replace(",", "").contains(amount), "Entry Present");
                return this;
            }
        }
        return this;
    }

    @Override
    public IHomeScreen minimizeBalanceScreen() {
        BasicAction.clickMobileElement(minimizeBy);
        return new AndroidHomeScreenImpl();
    }
}
