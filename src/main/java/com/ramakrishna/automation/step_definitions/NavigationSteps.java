package com.ramakrishna.automation.step_definitions;

import com.ramakrishna.automation.helpers.ParseEnvironmentDomain;
import com.ramakrishna.automation.helpers.Util;
import io.cucumber.java.en.Given;

import static com.ramakrishna.automation.browser.AbstractPage.driver;

public class NavigationSteps {

    ParseEnvironmentDomain parseEnv = new ParseEnvironmentDomain();
    Util util = new Util();
    private String feUrl = parseEnv.feUrl;

    @Given("the user navigate to environment")
    public void theUserNavigateToEnvironment() {
        util.closeAllTabs();
        driver.manage().deleteAllCookies();
        util.navigateToUrl(feUrl);
    }
}
