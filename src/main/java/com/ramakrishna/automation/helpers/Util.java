package com.ramakrishna.automation.helpers;

import com.ramakrishna.automation.browser.AbstractPage;
import com.ramakrishna.automation.constants.Constants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import com.ramakrishna.automation.constants.Constants.WaitingTime;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import static com.ramakrishna.automation.browser.AbstractPage.driver;

public class Util {

    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());
    private final WebDriver webDriver = AbstractPage.driver;

    public byte[] takeScreenScreenShotAsByte(WebDriver webDriver) throws IOException {
        Screenshot fpScreenshot;
        fpScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.scaling(2))
                .takeScreenshot(webDriver);

        BufferedImage originalImage = fpScreenshot.getImage();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        }
    }

    public byte[] takeFullPageScreenShotAsByte(WebDriver webDriver, boolean local) throws IOException {
        Screenshot fpScreenshot;
        if (local) {
            fpScreenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(2f), 1000))
                    .takeScreenshot(webDriver);
        } else {
            fpScreenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(webDriver);
        }

        BufferedImage originalImage = fpScreenshot.getImage();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        }
    }

    public boolean waitForJSandJQueryToLoad() {
        if (AlertsHandling.checkForUnexpectedAlert()) {
            driver.switchTo().alert().accept();
        }
        WebDriverWait wait = new WebDriverWait(driver, 5);
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                JavascriptExecutor runJS = ((JavascriptExecutor) driver);
                return ((Long) runJS.executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> {
            JavascriptExecutor runJS = ((JavascriptExecutor) driver);
            return runJS.executeScript("return document.readyState").toString().equals("complete");
        };
        try { // if jquery doesn't load within 5 seconds then continue tests
            // bypasses a bug introduced from rc-25-1 forwards where jquery gets returning 1
            // when transitioning from the admin page to the newly created long form story
            return wait.until(jQueryLoad) && wait.until(jsLoad);
        } catch (Exception e) {
            return true;
        }
    }

    private static void waitFor(final int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pause(final WaitingTime waitTime) {

        switch (waitTime) {
            case QUARTER_SECOND:
                waitFor(Constants.WAIT_FOR_250MS);
                break;
            case HALF_SECOND:
                waitFor(Constants.WAIT_FOR_500MS);
                break;
            case SECOND:
                waitFor(Constants.WAIT_FOR_1000MS);
                break;
            case SHORT:
                waitFor(Constants.WAIT_FOR_2000MS);
                break;
            case MEDIUM:
                waitFor(Constants.WAIT_FOR_4000MS);
                break;
            case LONG:
                waitFor(Constants.WAIT_FOR_7000MS);
                break;
            default:
                LOGGER.info("ERROR: invalid waiting time(" + waitTime + "). Please enter one of SHORT, MEDIUM, or LONG");
                break;
        }
    }

    public void switchToAlertAndAccept() {
        webDriver.switchTo().alert().accept();
    }

    public static int generateRandomNumber(int min, int max) {

        if (min == max) {
            return max;
        } else if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     *
     * Close all browser tabs except for first main tab
     *
     */
    public void closeAllTabs() {
        ArrayList<String> multipleTabs = new ArrayList<String>(driver.getWindowHandles());
        for (int i = 1; i < multipleTabs.size(); i++) {
            driver.switchTo().window(multipleTabs.get(i));
            driver.close();
        }
        driver.switchTo().window(multipleTabs.get(0));
    }

    public WebDriver getDriver() {
        return this.webDriver;
    }


    public void navigateToUrl(final String url) {
        getDriver().navigate().to(url);
        waitForJSandJQueryToLoad();
        pause(WaitingTime.LONG);
    }
}
