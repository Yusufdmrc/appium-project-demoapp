@products @smoke @regression
Feature: Products Page Functionality
  As a logged-in user
  I want to view and interact with products
  So that I can browse and select items

  Background:
    Given user launches the application
    When user logs in with valid credentials
    Then user should be redirected to the products page

  @positive
  Scenario: View products page after login
    Then user should see the products page
    And products should be displayed

  @positive
  Scenario: View product details
    Given user is on the products page
    When user clicks on the first product
    Then product details should be displayed

  @positive
  Scenario: Add product to cart from details page
    Given user is on the products page
    When user clicks on the first product
    And user adds the product to cart
    And user opens the cart
    Then cart should display the added item

  @positive
  Scenario: Increase product quantity
    Given user is on the products page
    When user clicks on the first product
    And user increases quantity by 2
    And user adds the product to cart
    And user opens the cart
    Then cart should display the added item

  @logout
  Scenario: Logout from products page
    Given user is on the products page
    When user logs out
    Then user should see login button
