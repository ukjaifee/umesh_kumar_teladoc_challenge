@adduser
Feature: Validate the user


  Scenario: Validate user being added and deleted
    Given Click the add user button
    When Add user details
    |firstName|lastName|email|userName|passWord|cellPhone|firstNameValue|lastNameValue|emailValue|userNameValue|passWordValue|cellPhoneValue|
    |FirstName|LastName|Email |UserName|Password|Mobilephone|uk         |novak        |abc123@gmail.com|novak  |admin        |1234567890    |
    And Select Customer and Role "Admin"
    And Click the Save button
    Then Validate the user "novak"
    When Delete the user "novak"
    Then Validate the delete confirmation message "Do you really want to delete the user?"
    And Click the "OK"

