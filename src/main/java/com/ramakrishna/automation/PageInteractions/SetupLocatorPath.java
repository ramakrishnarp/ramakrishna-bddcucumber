package com.ramakrishna.automation.PageInteractions;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class SetupLocatorPath {
    private static final Logger LOGGER = Logger.getLogger(SetupLocatorPath.class.getName());
    Properties prop = new Properties();
    InputStream input = null;

    public SetupLocatorPath() {
    }

    // load the properties file
    public void loadPropertyFile(String propertyFileName) {
        try {
            input = new FileInputStream(propertyFileName);
            prop.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // method overload in case "local" locator
    public String getPropertyLocator(String elementName) {
        return getPropertyLocator("", elementName);
    }

    // method to return locator
    public String getPropertyLocator(String fileName, String elementName) {
        String property = null;
        try {
            if (fileName.equals("")) {
                return elementName;
            } else {
                loadPropertyFile(fileName);
                property = prop.getProperty(elementName);
                return property;
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return property;
    }
}
