package com.ramakrishna.automation.browser;

import com.ramakrishna.automation.helpers.Util;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class AbstractPage extends EventFiringWebDriver {
    Util util = new Util();
    public static String headless = System.getProperty("headlessBrowser");
    static String browserName = System.getProperty("browserName").toLowerCase();
    public static boolean screenShotFlag = false;
    public static boolean fullPageScreenShot = false;
    public static boolean accessibilityReport = false;
    public static boolean currentUrl = false;
    public static String exceptionMsg = "";


    public static WebDriver driver = setBrowser();
    String presentWorkingDirectory = System.getProperty("user.dir");
    public static final Logger LOGGER = Logger.getLogger(AbstractPage.class.getName());
    public static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.quit();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public AbstractPage() {
        super(driver);
    }

        public static WebDriver setBrowser() {

        switch (browserName) {
            case "chrome":
                System.out.println("Tests are running on Chrome in PC mode.");
                driver = new ChromeDriver(chromeOptions());
                break;
             case "firefox":
                 System.out.println("Tests are running on Firefox in PC mode.");
                 driver = new FirefoxDriver(firefoxOptions());
                 break;
             case "ie":
                 System.out.println("Tests are running on Internet Explorer in PC mode.");
                 driver = new InternetExplorerDriver();
                 break;

             case "edge":
                 System.out.println("Tests are running on Windows in PC mode.");
                 driver = new EdgeDriver();
                 break;

              default:
                  System.out.println("Tests are running on Chrome in PC mode.");
                  driver = new ChromeDriver(chromeOptions());
                  break;
        }

        driver.manage().timeouts().pageLoadTimeout(420, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(420, TimeUnit.SECONDS);
        return driver;
    }


    @After()
    public void screenCapture(Scenario scenario) throws Exception {

        if (scenario.isFailed()) {
            LOGGER.info("TEST FAILED");

            if (AbstractPage.exceptionMsg.contains("waiting for condition")) {
                if (System.getenv("BUILD_URL") != null) {
                    scenario.embed(util.takeFullPageScreenShotAsByte(driver, false), "image/jpeg");
                } else {
                    scenario.embed(util.takeFullPageScreenShotAsByte(driver, true), "image/jpeg");
                }
                scenario.embed(driver.getCurrentUrl().getBytes(), "text/plain");
                AbstractPage.exceptionMsg = "";
            } else {
                scenario.embed(driver.getCurrentUrl().getBytes(), "text/plain");
                scenario.embed(util.takeScreenScreenShotAsByte(driver), "image/jpeg");
            }

        } else {
            LOGGER.info("TEST PASSED");
        }
    }


    @AfterStep()
    public void afterEveryStep(Scenario scenario) throws Exception {
        String fileName = scenario.getId();
        String userDir = System.getProperty("user.dir");
        String dir = userDir + "/reuseDataFiles/ss_image.jpg";
        if (currentUrl == true) {
            scenario.embed(driver.getCurrentUrl().getBytes(), "text/plain");
        }
        currentUrl = false;
        if (screenShotFlag == true) {
            scenario.embed(util.takeScreenScreenShotAsByte(driver), "image/jpeg");
        }
        screenShotFlag = false;
        if (fullPageScreenShot == true){
            if (System.getenv("BUILD_URL") != null) {
                scenario.embed(util.takeFullPageScreenShotAsByte(driver, false), "image/jpeg");
            } else {
                scenario.embed(util.takeFullPageScreenShotAsByte(driver, true), "image/jpeg");
            }
        }
        fullPageScreenShot = false;

        if (accessibilityReport) {
            StringBuilder paragraphBuilder = new StringBuilder();
            try (Stream<String> stream = Files.lines(Paths.get(presentWorkingDirectory+"/target/axe_results.txt"),
                    StandardCharsets.UTF_8)) {
                stream.forEach(s -> paragraphBuilder.append(s).append("\n"));
            } catch (IOException e) {

                e.printStackTrace();
            }

            String text = paragraphBuilder.toString();
            scenario.embed(text.getBytes(StandardCharsets.UTF_8), "text/plain");
        }
        accessibilityReport = false;
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. " + "It would close when the JVM exits");
        }
        super.quit();
    }


    public static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (headless.contentEquals("True")) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--no-sandbox");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            options.setCapability("acceptSslCerts", "true");
            options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
            options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        } else { // if not headless
            options.addArguments("--start-fullscreen");
            System.out.println("Chrome is Not Headless");
            }
            return options;
    }

    public static FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (headless.contentEquals("True")) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--no-sandbox");
            options.setCapability("acceptSslCerts", "true");
            options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
            options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        } else {
            System.out.println("Firefox is not headless.");
        }
        return options;
    }


}