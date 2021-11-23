package com.monefy.mobile_auto.screens.screensImpl.homescreen;

import com.monefy.mobile_auto.screens.IBalanceScreen;
import com.monefy.mobile_auto.screens.IHomeScreen;
import com.monefy.mobile_auto.screens.INewExpenseScreen;
import com.monefy.mobile_auto.screens.screensImpl.balancescreen.AndroidBalanceScreenImpl;
import com.monefy.mobile_auto.screens.screensImpl.expensescreen.AndroidExpenseScreenImpl;
import com.monefy.mobile_auto.screens.screensImpl.incomescreen.AndroidIncomeScreenImpl;
import com.monefy.mobile_auto.config.BasicAction;
import com.monefy.mobile_auto.util.AssertUtil;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AndroidHomeScreenImpl implements IHomeScreen {

    private By foodIconBy = By.xpath("//*[@resource-id='com.monefy.app.lite:id/piegraph']/android.widget.ImageView[1]");
    private By carIconBy = By.xpath("//*[@resource-id='com.monefy.app.lite:id/piegraph']/android.widget.ImageView[2]");
    private By allAccountsIconBy = By.xpath("//*[@content-desc='Open navigation']");
    private By settingsIconBy = By.id("com.monefy.app.lite:id/overflow");
    private By expenseButtonBy = By.id("com.monefy.app.lite:id/expense_button");
    private By incomeButtonBy = By.id("com.monefy.app.lite:id/income_button");
    private By balanceIconBy = By.id("com.monefy.app.lite:id/contentLayout");
    private By balanceTextBy = By.id("com.monefy.app.lite:id/balance_amount");
    private By leftSettingsContainerBy = By.id("com.monefy.app.lite:id/left_drawer");
    private By rightSettingsContainerBy = By.id("com.monefy.app.lite:id/right_drawer");
    private By titleBy = By.xpath("//*[@resource-id='com.monefy.app.lite:id/pts_main']/android.widget.TextView");
    private By searchIconBy = By.id("com.monefy.app.lite:id/menu_search");
    private By searchFieldBy = By.id("com.monefy.app.lite:id/et_search");
    private By searchNoteBy = By.id("com.monefy.app.lite:id/note_text_view");
    private By logoBy = By.xpath("//*[@resource-id='com.monefy.app.lite:id/toolbar']/android.widget.TextView");
    private By settingsPanelBy = By.id("com.monefy.app.lite:id/settings_panel");
    private By monthFilterBy = By.id("com.monefy.app.lite:id/month_period_button");
    private By backButtonBy = By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation\"]");

    private AndroidExpenseScreenImpl androidExpenseScreenImpl = new AndroidExpenseScreenImpl();
    private AndroidIncomeScreenImpl androidIncomeScreenImpl = new AndroidIncomeScreenImpl();

    @Override
    public IHomeScreen verifyPage() {
        BasicAction.isMobileElementDisplayed(expenseButtonBy);
        BasicAction.isMobileElementDisplayed(incomeButtonBy);
        BasicAction.isMobileElementDisplayed(balanceIconBy);
        return this;
    }

    @Override
    public INewExpenseScreen clickAddFood() {
        BasicAction.clickMobileElement(foodIconBy);
        return androidExpenseScreenImpl;
    }

    @Override
    public INewExpenseScreen clickAddCar() {
        BasicAction.clickMobileElement(carIconBy);
        return androidExpenseScreenImpl;
    }

    @Override
    public AndroidIncomeScreenImpl clickAddNewIncome() {
        BasicAction.clickMobileElement(incomeButtonBy);
        return androidIncomeScreenImpl;
    }

    @Override
    public INewExpenseScreen clickAddNewExpense() {
        BasicAction.clickMobileElement(expenseButtonBy);
        return androidExpenseScreenImpl;
    }

    @Override
    public IHomeScreen clickAllAccounts() {
        BasicAction.clickMobileElement(allAccountsIconBy);
        return this;
    }

    @Override
    public IHomeScreen selectMonthFilter() {
        BasicAction.clickMobileElement(monthFilterBy);
        return this;
    }

    @Override
    public IHomeScreen clickSettings() {
        BasicAction.clickMobileElement(settingsIconBy);
        return this;
    }

    @Override
    public IHomeScreen hideSettings() {
        if(BasicAction.isMobileElementDisplayed(settingsPanelBy)) {
            BasicAction.clickMobileElement(settingsIconBy);
        }
        return this;
    }

    @Override
    public IBalanceScreen clickBalance() {
        BasicAction.clickMobileElement(balanceIconBy);
        return new AndroidBalanceScreenImpl();
    }

    @Override
    public IHomeScreen validateMonthOption() {
        if(BasicAction.isMobileElementDisplayed(leftSettingsContainerBy)) {
            BasicAction.pressBack();
        }
        Calendar cal = Calendar.getInstance();
        String currentMonth = new SimpleDateFormat("MMM").format(cal.getTime());
        AssertUtil.verifyTrue(BasicAction.getMobileElementText(titleBy).toLowerCase().contains(currentMonth.toLowerCase()), "Month is displaying correctly as title");
        return this;
    }

    @Override
    public IHomeScreen validateRightOptions() {
        String rightPanelText = BasicAction.getMobileElementText(rightSettingsContainerBy);
        AssertUtil.verifyTrue(rightPanelText.contains("Categories"), "Categories option is displayed");
        AssertUtil.verifyTrue(rightPanelText.contains("Accounts"), "Accounts option is displayed");
        AssertUtil.verifyTrue(rightPanelText.contains("Currencies"), "Currencies option is displayed");
        AssertUtil.verifyTrue(rightPanelText.contains("Settings"), "Settings option is displayed");
        return this;
    }

    @Override
    public IHomeScreen search(String searchText) {
        BasicAction.clickMobileElement(searchIconBy);
        BasicAction.enterText(searchFieldBy, searchText);
        BasicAction.pressEnter();
        return this;
    }

    @Override
    public IHomeScreen validateSearchItem(String searchItemNote) {
        AssertUtil.verifyTrue(BasicAction.getMobileElementText(searchNoteBy).contains(searchItemNote), "Item note matched");
        BasicAction.clickMobileElement(backButtonBy);
        return this;
    }

    @Override
    public IHomeScreen clickLogo() {
        BasicAction.clickMobileElement(logoBy);
        return this;
    }

    @Override
    public IHomeScreen validateBalanceAmount(String balanceAmount) {
        AssertUtil.verifyTrue(BasicAction.getMobileElementText(balanceTextBy).replace(",", "").contains(balanceAmount), "Correct balance amount is displayed");
        return this;
    }
}
