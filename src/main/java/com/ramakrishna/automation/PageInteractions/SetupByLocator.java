package com.ramakrishna.automation.PageInteractions;

import com.ramakrishna.automation.Builder.ActionParams;
import org.openqa.selenium.By;

public class SetupByLocator {

    public SetupByLocator() {

    }

    SetupLocatorPath setupLocatorPath = new SetupLocatorPath();

    // sets up the By
    // returns eg
    //  By.xpath("//div...");
    //  or By.cssSelector("button.btn-search");
    //  etc.
    public By locatorParser(String byType, String locator) {
        By loc = By.id(locator);
        switch (byType) {
        case "name":
            loc = By.name(locator);
            break;
        case "linkText":
            loc = By.linkText(locator);
            break;
        case "xpath":
            loc = By.xpath(locator);
            break;
        case "className":
            loc = By.className(locator);
            break;
        case "cssSelector":
            loc = By.cssSelector(locator);
            break;
        case "partialLinkText":
            loc = By.partialLinkText(locator);
            break;
        case "tagName":
            loc = By.tagName(locator);
            break;
        }
        return loc;
    }

    // setup By locator
    public By getByLocator(ActionParams.Params locatorParams) { // set up the By statement eg By.cssSelector(rawLocator)
        // set up params
        String byType = locatorParams.getByType(); // defaults to xpath eg By.xpath("");
        String locator = setupLocatorPath.getPropertyLocator(locatorParams.getFileName(), locatorParams.getLocator());
//        String locator = locatorParams.getLocator(); // ** REQUIRED **
//        String fileName = locatorParams.getFileName(); // defaults to empty string ""
        int position = locatorParams.getPosition(); // for drag and drop element position. Defaults to 0
//        String rawLocator = getLocator(fileName, locator); // gets the locator property from the .properties file
        if (position > 0) {
            locator = "(" + locator + ")[" + position + "]";
        }
        // return By statement eg By.cssSelector(rawLocator)
        return locatorParser(byType, locator); // sets up the By eg By.xpath("//div..."
    }

}
