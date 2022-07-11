package com.ramakrishna.automation.PageInteractions;

import com.ramakrishna.automation.Builder.ActionParams;
import com.ramakrishna.automation.browser.AbstractPage;
import com.ramakrishna.automation.helpers.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SetupExpectedCondition {
    private WebDriver webDriver = AbstractPage.driver;
    Util util = new Util();
    SetupByLocator setupLoc = new SetupByLocator();
    JavascriptExecutor runJS = ((JavascriptExecutor) webDriver);

    public SetupExpectedCondition() {

    }

    public void expectedCondition(String expectedType, ActionParams.Params methodParams, int numberOfElements) {
        int waitTimeInSec = methodParams.getWaitTimeInSec();
        WebDriverWait wait = new WebDriverWait(webDriver, waitTimeInSec);
        By byLocator = setupLoc.getByLocator(methodParams);
        String text = methodParams.getText();
        int position = methodParams.getPosition();
        switch (expectedType) {
        case "visibility":
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(byLocator)));
            break;
        case "presence":
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(byLocator)));
            break;
        case "presenceBooleanReturned":
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(byLocator)));
            break;
        case "presenceOfTextToBe":
            wait.until(
                    ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElementLocated(byLocator, text)));
            break;
        case "clickable":
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(byLocator)));
            break;
        case "invisibility":
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(byLocator)));
            break;
        case "frameAvailableSwitchToIt":
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.frameToBeAvailableAndSwitchToIt(byLocator)));
            break;
        case "stringInUrl":
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(text)));
            break;
        case "elementToBeSelected":
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeSelected(byLocator)));
            break;
        case "numberOfElementsToBeMoreThan":
                wait.until(ExpectedConditions.refreshed(ExpectedConditions.numberOfElementsToBeMoreThan(byLocator,numberOfElements)));
                break;
        case "multipleWindows":
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.numberOfWindowsToBe(position)));
        }
    }

    public By expectedConditionRoot(String expectedType, ActionParams.Params methodParams) {
        By byLocator = setupLoc.getByLocator(methodParams);
        util.waitForJSandJQueryToLoad();
        expectedCondition(expectedType, methodParams);
        return byLocator;
    }

    public WebElement setExpectedCondition(String expectedType, ActionParams.Params methodParams) {
        By byLocator = expectedConditionRoot(expectedType, methodParams);
        if (!expectedType.equals("invisibility") && !expectedType.equals("presenceBooleanReturned")) {
            if (methodParams.getScrollTo()) {
                String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                        + "var elementTop = arguments[0].getBoundingClientRect().top;"
                        + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
                runJS.executeScript(scrollElementIntoMiddle, webDriver.findElement(byLocator));
            }
        }
        return webDriver.findElement(byLocator);
    }

    public List<WebElement> setupExpectedConditionList(String expectedType, ActionParams.Params methodParams) {
        By byLocator = expectedConditionRoot(expectedType, methodParams);
        return webDriver.findElements(byLocator);
    }
    public void expectedCondition(String expectedType, ActionParams.Params methodParams) {
        expectedCondition(expectedType, methodParams, 0);
    }
}
