package com.bestbuy.api_auto.stepdefs;

import com.bestbuy.api_auto.app.HealthCheckManager;

import io.cucumber.java.en.When;

public class HealthCheckStepDefs extends AbstractBaseTest {
	
	private HealthCheckManager healthManager = new HealthCheckManager(api());

	@When("I check the health information of system")
    public void checkHealth() {
		healthManager.checkHealth();
    }
}
