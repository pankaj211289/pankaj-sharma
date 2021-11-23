package com.monefy.mobile_auto.screens.screensImpl.incomescreen;

import com.monefy.mobile_auto.screens.INewIncomeScreen;
import com.monefy.mobile_auto.config.BasicAction;
import com.monefy.mobile_auto.screens.IncomeType;
import com.monefy.mobile_auto.util.AssertUtil;
import org.openqa.selenium.By;

public class AndroidIncomeScreenImpl implements INewIncomeScreen {

    private By toolBarBy = By.xpath("//*[@resource-id='com.monefy.app.lite:id/toolbar']/android.widget.TextView");
    private String keyBoardTextID = "com.monefy.app.lite:id/buttonKeyboard{KeyName}";
    private By addExpenseBy = By.id("com.monefy.app.lite:id/keyboard_action_button");
    private By salaryBy = By.xpath("//*[@text='Salary']");
    private By depositsBy = By.xpath("//*[@text='Deposits']");
    private By savingBy = By.xpath("//*[@text='Savings']");

    @Override
    public INewIncomeScreen verifyPage() {
        AssertUtil.verifyTrue(BasicAction.getMobileElementText(toolBarBy).equals("New income"), "Title should be displayed");
        return this;
    }

    @Override
    public void addAmount(String amount, IncomeType incomeType) {
        for(String character : amount.split("")) {
            if(!character.matches("\\s*")) {
                BasicAction.clickMobileElementByID(keyBoardTextID.replace("{KeyName}", character));
            }
        }
        BasicAction.clickMobileElement(addExpenseBy);
        switch (incomeType) {
            case SALARY:
                BasicAction.clickMobileElement(salaryBy);
                break;
            case DEPOSITS:
                BasicAction.clickMobileElement(depositsBy);
                break;
            case SAVINGS:
                BasicAction.clickMobileElement(savingBy);
                break;
        }
    }
}
