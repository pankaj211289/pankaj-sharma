package com.monefy.mobile_auto.screens;

public interface INewExpenseScreen {

    /**
     * Validates the page
     * @return {@link INewExpenseScreen}
     */
    public INewExpenseScreen verifyPage();

    /**
     * Adds amount as expense
     * @param amount : amount to be added
     */
    public void addAmount(String amount);

    /**
     * Adds a new note to expense
     * @param note : note's text
     * @return {@link INewExpenseScreen}
     */
    public INewExpenseScreen addNote(String note);
}
