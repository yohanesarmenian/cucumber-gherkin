Feature: User Check out after Add Items

@Regresion  @Positive
  Scenario: User Check Out After fill the Information Page
    Given User Add items
      When User on Cart Page
      And Click Check Out button
      And Fill the Information page
      And Click finish button
      Then User success order

@Regresion @Negative
    Scenario: User Check Out But Didn't Fill the information Page
    Given User Add items
    When User on Cart Page
    And Click Check Out button
    But Didn't Fill the first Name Information page
    Then User get error notification
