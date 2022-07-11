package com.ramakrishna.automation.step_definitions.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/main/resources/features/Instacart.feature" }, glue = {
        "com.ramakrishna.automation" }, plugin = { "pretty", "html:target/cucumber-reports",
        "json:target/cucumber-reports/Instacart.json",
        "junit:target/cucumber-reports/Instacart.xml",
        "rerun:target/rerun/Instacart.txt" }, monochrome = true)

public class Test_Instacart {
}
