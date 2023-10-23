Feature: Login to the website

@Regresion @Positive
  Scenario: Login valid credential
    Given Login Page Swag Labs
    When Input Username
    And Input Password
    And Click Login Button
    Then User Landing on Dashboard

@Regresion @Negative
  Scenario: Login invalid credential
    Given Login Page Swag Labs
    When Input invalid username
    And Input Password
    And Click Login Button
    Then User get error message