package com.ramakrishna.automation.PageInteractions;

import com.ramakrishna.automation.Builder.ActionParams;
import com.ramakrishna.automation.browser.AbstractPage;
import org.openqa.selenium.WebElement;

public class SetupAction {
    SetupExpectedCondition ecSetup = new SetupExpectedCondition();

    public SetupAction() {
    }

    public WebElement clickable(ActionParams.Params methodParams) {
        return ecSetup.setExpectedCondition("clickable", methodParams);
    }

    public WebElement visibility(ActionParams.Params methodParams) {
        try {
            return ecSetup.setExpectedCondition("visibility", methodParams);
        } catch (Exception e) {
            AbstractPage.exceptionMsg = e.getMessage();
            throw e;
        }

    }

    public WebElement presence(ActionParams.Params methodParams) {
        try {
            return ecSetup.setExpectedCondition("presence", methodParams);
        } catch (Exception e) {
            AbstractPage.exceptionMsg = e.getMessage();
            throw e;
        }
    }

    public WebElement presenceBooleanReturned(ActionParams.Params methodParams) {
        return ecSetup.setExpectedCondition("presenceBooleanReturned", methodParams);
    }

    public WebElement presenceOfAll(ActionParams.Params methodParams) {
        try {
            return ecSetup.setExpectedCondition("presenceOfAll", methodParams);
        } catch (Exception e) {
            AbstractPage.exceptionMsg = e.getMessage();
            throw e;
        }

    }

    public WebElement presenceOfTextToBe(ActionParams.Params methodParams) {
        return ecSetup.setExpectedCondition("presenceOfTextToBe", methodParams);
    }

    public WebElement stringInUrl(ActionParams.Params methodParams) {
        return ecSetup.setExpectedCondition("stringInUrl", methodParams);
    }

    public void invisibility(ActionParams.Params methodParams) {
        try {
            ecSetup.expectedCondition("invisibility", methodParams);
        } catch (Exception e) {
            AbstractPage.exceptionMsg = e.getMessage();
            throw e;
        }
    }

    public void frameAvailableSwitchToIt(ActionParams.Params methodParams) {
        ecSetup.expectedCondition("frameAvailableSwitchToIt", methodParams);
    }

    public void multipleWindows(ActionParams.Params methodParams) {
        ecSetup.expectedCondition("multipleWindows", methodParams);
    }

    public void elementToBeSelected(ActionParams.Params methodParams) {
        ecSetup.expectedCondition("elementToBeSelected", methodParams);
    }

    public void numberOfElementsToBeMoreThan(ActionParams.Params methodParams, int sizeOfTotalElements){
            ecSetup.expectedCondition("numberOfElementsToBeMoreThan",methodParams,sizeOfTotalElements);
    }
}
