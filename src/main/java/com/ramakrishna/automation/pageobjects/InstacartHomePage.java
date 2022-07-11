package com.ramakrishna.automation.pageobjects;

import com.ramakrishna.automation.Builder.ActionParams;
import com.ramakrishna.automation.PageInteractions.PageInteraction;
import com.ramakrishna.automation.constants.Constants;
import com.ramakrishna.automation.helpers.Util;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;

import static com.ramakrishna.automation.browser.AbstractPage.driver;

public class InstacartHomePage {

    PageInteraction pageActions = new PageInteraction();

    int totalStores;

    ArrayList<String> storeNames = new ArrayList<String>();
    ArrayList<String> stores = new ArrayList<>(Arrays.asList("Fortinos", "Real Canadian Superstore", "Shoppers Drug Mart", "Metro", "Walmart", "Bulk Barn", "Dollarama", "Pusateri's", "M&M Food Market", "Eataly", "Organic Garage", "Indigo"));

    // LOCATORS

    private ActionParams.Params stores() {
        return new ActionParams.Params().setByType("xpath").setLocator("//p[@class='css-3w8d2s']");
    }

    private ActionParams.Params hamburgerMenu() {
        return new ActionParams.Params().setByType("xpath").setLocator("//div[@data-testid='hamburger-button']");
    }

    // METHODS

    public int getTotalStoresCount() {
        return pageActions.getCount(stores());
    }

    public void getCountOfStores() {
        totalStores = getTotalStoresCount();
    }

    public void getStoreNames() {
        for (int i = 1; i <= totalStores ; i++) {
            storeNames.add(driver.findElement(By.xpath("(//p[@class='css-3w8d2s'])[position()="+i+"]")).getText());
        }
    }

    public void compareStoreNames() {
        Assert.assertEquals(stores, storeNames);
    }

    public void printStoreNames() {
        System.out.println("Validated the following store names on instacart home page");
        for (int i = 0; i < storeNames.size(); i++) {
            System.out.println(i+1 + "-" + storeNames.get(i));
        }
    }

    public void clickOnHamburgerMenu() {
        pageActions.actionClick(hamburgerMenu());
    }

    public void clickOnSubMenu(String subMenuName) {
        driver.findElement(By.xpath("//div[@class='css-l4tmde']/div[@data-testid='nav-section-div']/span[text()='"+subMenuName+"']")).click();
    }

    public void clickOnMenuUnderMoreWaysToShop(String category) {
        Util.pause(Constants.WaitingTime.MEDIUM);
        driver.findElement(By.xpath("//div[@id='more-ways-to-shop']/a/div[text()='"+category+"']")).click();
    }
}
