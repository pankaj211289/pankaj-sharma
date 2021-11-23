package com.bestbuy.api_auto;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/featureFiles",
        glue = "com.bestbuy.api_auto.stepdefs",
        plugin = { "pretty", "html:reports/cucumber-reports.html" })
public class RunCucumberTest {

}