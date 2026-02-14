@login @smoke @regression
Feature: Login Functionality
  As a user of My Demo App
  I want to be able to login with valid credentials
  So that I can access the application features

  Background:
    Given user is on products page

  @positive
  Scenario Outline: Successful login with valid users
    When user opens menu
    And user clicks on login menu item
    And user enters username "<username>"
    And user enters password "<password>"
    And user clicks login button
    Then user should be redirected to the products page

    Examples:
      | username            | password |
      | bob@example.com     | 10203040 |



  @negative
  Scenario Outline: Login with invalid credentials
    When user opens menu
    And user clicks on login menu item
    And user enters username "<username>"
    And user enters password "<password>"
    And user clicks login button
    Then user should see error message "<expectedError>"

    Examples:
      | username           | password  | expectedError                       |
      | alice@example.com  | 10203040  | Sorry this user has been locked out.|
      |                    | 10203040  | Username is required                |
      | bob@example.com    |           | Enter Password                      |
      |                    |           | Username is required                |
