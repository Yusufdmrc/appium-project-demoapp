@cart @regression
Feature: Shopping Cart Functionality
  As a user
  I want to manage items in my shopping cart
  So that I can purchase products

  Background:
    Given user launches the application
    When user logs in with valid credentials
    And user is on the products page
    And user clicks on the first product
    And user adds the product to cart

  @positive
  Scenario: View cart with added items
    When user opens the cart
    Then user should see cart page
    And cart should contain 1 item(s)
    And total price should be displayed

  @positive
  Scenario: Add multiple products to cart
    When user is on the products page
    And user clicks on the first product
    And user adds the product to cart
    And user opens the cart
    Then cart should display the added item

  @positive
  Scenario: Remove item from cart
    When user opens the cart
    And user removes the first item from cart
    Then cart should be empty

  @positive
  Scenario: Proceed to checkout with items in cart
    When user opens the cart
    Then cart should display the added item
    And total price should be displayed
