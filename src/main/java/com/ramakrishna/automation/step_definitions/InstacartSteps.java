package com.ramakrishna.automation.step_definitions;

import com.ramakrishna.automation.browser.AbstractPage;
import com.ramakrishna.automation.constants.Constants;
import com.ramakrishna.automation.helpers.Util;
import com.ramakrishna.automation.pageobjects.InstacartHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InstacartSteps {

    InstacartHomePage instacartHomePage = new InstacartHomePage();



    @Then("get the count of stores on home page")
    public void getTheCountOfStoresOnHomePage() {
        instacartHomePage.getCountOfStores();
    }

    @And("get the store names on homepage")
    public void getTheStoreNamesOnHomePage() {
        instacartHomePage.getStoreNames();
    }

    @And("validate the store names")
    public void validateTheStoreNames() {
        instacartHomePage.compareStoreNames();
    }

    @Then("print the validated store names")
    public void printTheValidatedStoreNames() {
        instacartHomePage.printStoreNames();
    }

    @When("user click on hamburger menu on home page")
    public void userClickOnMenuBarOnHomePage() {
        instacartHomePage.clickOnHamburgerMenu();
    }

    @Then("click on submenu {string}")
    public void clickOnSubMenu(String submenu) {
        instacartHomePage.clickOnSubMenu(submenu);
    }

    @Then("click category {string} under more ways to shop menu")
    public void clickCategoryUnderMoreWaysToShopMenu(String category) {
        instacartHomePage.clickOnMenuUnderMoreWaysToShop(category);
        Util.pause(Constants.WaitingTime.LONG);
    }

    @And("take the screenshot")
    public void takeTheScreenshot() {
        AbstractPage.screenShotFlag = true;
        AbstractPage.currentUrl = true;
    }
}
