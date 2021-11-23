package com.monefy.mobile_auto.screens;

public interface IHomeScreen {

	/**
	 * Validates Page
	 * @return : {@link IHomeScreen}
	 */
	public IHomeScreen verifyPage();

	/**
	 * Clicks Add food icon
	 * @return : {@link INewExpenseScreen}
	 */
	public INewExpenseScreen clickAddFood();

	/**
	 * Clicks Add car icon
	 * @return : {@link INewExpenseScreen}
	 */
	public INewExpenseScreen clickAddCar();

	/**
	 * Clicks New income button
	 * @return : {@link INewIncomeScreen}
	 */
	public INewIncomeScreen clickAddNewIncome();

	/**
	 * Clicks New expense button
	 * @return : {@link INewExpenseScreen}
	 */
	public INewExpenseScreen clickAddNewExpense();

	/**
	 * Clicks All accounts icon
	 * @return : {@link IHomeScreen}
	 */
	public IHomeScreen clickAllAccounts();

	/**
	 * Select Month filter in left panel
	 * @return  : {@link IHomeScreen}
	 */
	public IHomeScreen selectMonthFilter();

	/**
	 * Clicks Settings icon
	 * @return : {@link IHomeScreen}
	 */
	public IHomeScreen clickSettings();

	/**
	 * Hides setting panel if open
	 * @return: {@link IHomeScreen}
	 */
	public IHomeScreen hideSettings();

	/**
	 * Clicks Balance to open balance sheet
	 * @return :  {@link IHomeScreen}
	 */
	public IBalanceScreen clickBalance();

	/**
	 * Validates Month Option
	 * @return : {@link IHomeScreen}
	 */
	public IHomeScreen validateMonthOption();

	/**
	 * Validates Right Options
	 * @return : {@link IHomeScreen}
	 */
	public IHomeScreen validateRightOptions();

	/**
	 * Searches with specified text
	 * @param searchText : text
	 * @return : {@link IHomeScreen}
	 */
	public IHomeScreen search(String searchText);

	/**
	 * Validates the search item via its note
	 * @param noteText : Note added to expense/Income
	 * @return : {@link IHomeScreen}
	 */
	public IHomeScreen validateSearchItem(String noteText);

	/**
	 * Clicks logo of application
	 * @return : {@link IHomeScreen}
	 */
	public IHomeScreen clickLogo();

	/**
	 * Validates Balance amount
	 * @param balanceAmount : Amount
	 * @return : {@link IHomeScreen}
	 */
	public IHomeScreen validateBalanceAmount(String balanceAmount);
}
