@products @smoke @regression
Feature: Products Page Functionality
  As a user
  I want to view and interact with products
  So that I can browse and select items to purchase

  Background:
    Given user is on products page

  @positive
  Scenario: View products list
    Then products page should be displayed
    And products list should contain items

  @positive
  Scenario: Open product detail page
    When user clicks on first product
    Then product detail page should be displayed
    And product name should be displayed
    And product price should be displayed
    And product rating should be displayed

  @positive
  Scenario: Verify quantity controls on product detail page
    When user clicks on first product
    Then product detail page should be displayed
    And quantity should be "1"
    When user clicks increase quantity button
    Then quantity should be "2"
    When user clicks increase quantity button
    And user clicks increase quantity button
    Then quantity should be "4"
    When user clicks decrease quantity button
    Then quantity should be "3"

  @positive
  Scenario: Add product to cart with default quantity
    When user clicks on first product
    And user clicks add to cart button
    And user opens cart
    Then cart should contain "1" items

  @positive
  Scenario: Add product to cart with increased quantity
    When user clicks on first product
    And user increases quantity to "3"
    And user clicks add to cart button
    And user opens cart
    Then cart should contain "1" items
    And first cart item quantity should be "3"

  @positive
  Scenario: Verify cart badge updates after adding product
    When user clicks on first product
    And user clicks add to cart button
    Then cart badge should show "1"

  @positive
  Scenario: Select different product color
    When user clicks on first product
    Then color options should be displayed
    When user selects color "blue"
    Then selected color should be "blue"
