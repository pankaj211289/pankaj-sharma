package com.monefy.mobile_auto.screens.screensImpl.expensescreen;

import com.monefy.mobile_auto.screens.INewExpenseScreen;
import com.monefy.mobile_auto.config.BasicAction;
import com.monefy.mobile_auto.util.AssertUtil;
import org.openqa.selenium.By;

public class AndroidExpenseScreenImpl implements INewExpenseScreen {

    private By toolBarId = By.id("com.monefy.app.lite:id/toolbar");
    private String keyBoardTextID = "com.monefy.app.lite:id/buttonKeyboard{KeyName}";
    private By addExpenseBy = By.id("com.monefy.app.lite:id/keyboard_action_button");
    private By noteBy = By.id("com.monefy.app.lite:id/textViewNote");

    @Override
    public INewExpenseScreen verifyPage() {
        AssertUtil.verifyTrue(BasicAction.getMobileElementText(toolBarId).equals("New Expense"), "Title should be displayed");
        return this;
    }

    @Override
    public void addAmount(String amount) {
        for(String character : amount.split("")) {
            if(!character.matches("\\s*")) {
                BasicAction.clickMobileElementByID(keyBoardTextID.replace("{KeyName}", character));
            }
        }
        BasicAction.clickMobileElement(addExpenseBy);
    }

    @Override
    public INewExpenseScreen addNote(String note) {
        BasicAction.enterText(noteBy, note);
        return this;
    }
}
