package com.monefy.mobile_auto.screens;

public interface IBalanceScreen {

    /**
     * Validates the page
     * @return : {@link IBalanceScreen}
     */
    public IBalanceScreen verifyPage();

    /**
     * Validates the entry in sheet
     * @param category : Nature of expense/income
     * @param amount : Amount
     * @return {@link IBalanceScreen}
     */
    public IBalanceScreen validateEntry(String category, String amount);

    /**
     * Minimize the balance sheet
     * @return {@link IHomeScreen}
     */
    public IHomeScreen minimizeBalanceScreen();

}
