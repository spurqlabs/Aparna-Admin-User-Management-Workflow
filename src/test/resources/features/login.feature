@smoke @regression
Feature: OrangeHRM User Management Flow


  @login
  Scenario Outline: Login with multiple valid credentials
    Given user is on login page
    When user enters username "<username>" and password "<password>"
    And clicks on login button
    Then dashboard page should be displayed

    Examples:
      | username | password |
      | Admin    | admin123 |


  @admin
  Scenario: Complete end to end admin flow
    Given user is on login page
    When user enters username "Admin" and password "admin123"
    And clicks on login button
    Then dashboard page should be displayed

    When user clicks on admin menu
    Then user management page should be displayed

    When user clicks on add button
    Then add user page should be displayed

    When user enters new user details and saves
    Then user should be added successfully

    When user searches created user
    Then created user record should be displayed

    When user deletes created user
    Then delete success message should be displayed

    When user searches invalid username
    Then no record found message should be displayed