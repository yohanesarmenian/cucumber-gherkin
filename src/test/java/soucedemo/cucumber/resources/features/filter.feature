Feature: Users Use Product Filters

@Regresion @Positive
  Scenario: User using filters based on High to Low prizes
    Given User on Dashboard
    When User clicks the Filter menu
    And User uses the Prize Low to High filter
    Then Products are sorted from the highest to lowest price

@Regresion @Negative
  Scenario: Problem User using filters based on High to Low prizes
    Given Problem User on Dashboard
    When User clicks the Filter menu
    And User uses the Prize Low to High filter
    Then Products are not sorted