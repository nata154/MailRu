Feature: Write and Send email

  Background:
    Given I opened Mail.Ru
    And I enter login and password
      | nata154154 | 1z2x3c4v |
    And I click submit button

  Scenario Outline: Writing and sending email
    When I click button create new letter
    And I enter write email to writeEmailTo <writeEmailTo>
    And I enter subject of email subject <subject>
    And I enter body of email bodyOfEmail <bodyOfEmail>
#    And I write letter with parameters <writeEmailTo>, <subject>, <bodyOfEmail>
    And Click Send Email
    Then Letter appears in folder Sent <subject>
    And Log out

    Examples:
      | writeEmailTo     | subject     | bodyOfEmail             |
      | nata154154@bk.ru | first_email | first_email_bodyOfEmail |

  Scenario Outline: Writing letter and saving it to drafts
    When I click button create new letter
    And I enter write email to writeEmailTo <writeEmailTo>
    And I enter subject of email subject <subject>
    And I enter body of email bodyOfEmail <bodyOfEmail>
    And Click button save email to drafts
    And Open drafts folder
    Then Letter appears in folder Drafts <writeEmailTo>
    And Log out

    Examples:
      | writeEmailTo     | subject     | bodyOfEmail             |
      | nata154154@bk.ru | first_email | first_email_bodyOfEmail |
