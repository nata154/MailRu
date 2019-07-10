Feature: LogIn

  Background:
    Given I opened Mail.Ru

  Scenario Outline: LogIn with credentials
    When I enter login <mailboxLogin>
    And I enter password <password>
    And I click submit button
    Then Check i entered successfully
    And log out

    Examples:
      | mailboxLogin | password |
      | nata154154   | 1z2x3c4v |