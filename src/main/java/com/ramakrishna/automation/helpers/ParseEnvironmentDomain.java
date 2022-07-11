package com.ramakrishna.automation.helpers;


public class ParseEnvironmentDomain {

    private final String mvnTestingUrl = System.getProperty("testingUrl").replaceAll("/$", "");

    public String feUrl = getFeUrl();

    public ParseEnvironmentDomain() {

    }

    private  String getFeUrl() {
        return  mvnTestingUrl;
    }
}
