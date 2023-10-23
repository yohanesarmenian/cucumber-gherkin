Feature: Users add item to the cart

@Regresion @Positive
  Scenario: User Add Items to the Cart
    Given User Choose Items
    When User clicks Add Item to Card for Sauce Labs Backpack
    And User clicks Add Item to Card for Sauce Labs Bike Light
    And User clicks cart logo
    Then Products are showed on cart page

@Regresion @Negative
  Scenario: Problem User Add Item to the Cart
    Given Problem User Choose Item
    When User click Add Item to Card for Sauce Labs Backpack
    And User clicks Remove Item to Card for Sauce Labs Backpack
    Then Problem User cant Remove the item
