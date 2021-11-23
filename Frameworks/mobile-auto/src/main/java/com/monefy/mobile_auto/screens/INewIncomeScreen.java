package com.monefy.mobile_auto.screens;

public interface INewIncomeScreen {

    /**
     *
     * Validates New Income page
     *
     * @return {@link INewIncomeScreen}
     */
    public INewIncomeScreen verifyPage();

    /**
     * Adds amount as new income
     * @param amount : Amount to be added
     * @param incomeType : Category of income
     */
    public void addAmount(String amount, IncomeType incomeType);
}
