@instacart @regression @smoke

Feature: Testing instacart application

  @instacart1
  Scenario: 1-Validate the stores names on home page
    Given the user navigate to environment
    Then get the count of stores on home page
    And get the store names on homepage
    And validate the store names
    Then print the validated store names

  @instacart2
  Scenario: 2-Validate the store names on menu page
    Given the user navigate to environment
    When user click on hamburger menu on home page
    Then click on submenu "More ways to shop"
    Then click category "Alcohol" under more ways to shop menu
    And take the screenshot