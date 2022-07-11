package com.ramakrishna.automation.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import static com.ramakrishna.automation.browser.AbstractPage.driver;

public class AlertsHandling {

    public static boolean checkForUnexpectedAlert() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public static String readAlertText() {
        Alert alert = driver.switchTo().alert();
        String alertText = null;
        if (checkForUnexpectedAlert()) {
            driver.switchTo().alert();
            alertText = alert.getText();
        }
        return alertText;
    }
}
