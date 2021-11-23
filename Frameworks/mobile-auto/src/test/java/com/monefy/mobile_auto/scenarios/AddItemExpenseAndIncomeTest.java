package com.monefy.mobile_auto.scenarios;

import com.monefy.mobile_auto.BasicInit;
import com.monefy.mobile_auto.screens.IHomeScreen;
import com.monefy.mobile_auto.screens.IncomeType;
import com.monefy.mobile_auto.screens.screensImpl.homescreen.AndroidHomeScreenImpl;
import com.monefy.mobile_auto.screens.screensImpl.homescreen.IOSHomeScreenImpl;
import org.testng.annotations.Test;

public class AddItemExpenseAndIncomeTest extends BasicInit {

    private IHomeScreen homeScreen = getOS().contains("ANDROID") ? new AndroidHomeScreenImpl() : new IOSHomeScreenImpl();

    /**
     * Adds Income and expense
     * Validates Balance sheet for same
     * Validates total Balance
     */
    @Test(priority = 1)
    public void addIncomeAndItem() {
        String salary = "70000";
        String carAmount = "10000";

        // Adds Income
        homeScreen.verifyPage().clickAddNewIncome().verifyPage().addAmount(salary, IncomeType.SALARY);
        // Validates same income in balance sheet
        homeScreen.clickBalance().verifyPage().validateEntry("Salary", salary).minimizeBalanceScreen();

        // Adds Expense
        homeScreen.clickAddCar().addAmount(carAmount);
        // Verifies same expense in balance sheet
        homeScreen.hideSettings().clickBalance().verifyPage().validateEntry("Car", carAmount).minimizeBalanceScreen();

        // Validate Balance sheet
        homeScreen.validateBalanceAmount("60000");
    }

    /**
     * Adds Note to Expense and Search it via same note & validates
     */
    @Test(priority = 2)
    public void addNoteAndSearch() {
        String foodAmount = "1000";
        String noteText = "TestNote";

        // Adds note to expense
        homeScreen.clickAddFood().addNote(noteText).addAmount(foodAmount);

        // Search via same note and validates it
        homeScreen.search(noteText).validateSearchItem(noteText);
    }

    /**
     * Filter Expenses and Income via Month and validates same (by checking title only)
     */
    @Test(priority = 2)
    public void filterByMonth() {
        // Clicks All Account and filter by month and validates
        homeScreen.clickAllAccounts().selectMonthFilter().validateMonthOption();
    }
}
