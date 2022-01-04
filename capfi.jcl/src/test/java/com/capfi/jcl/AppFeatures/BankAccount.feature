
Feature: Bank Account Kata feature

  Scenario: In order to save money of new account scenario
    Given As a bank new client
    When I want to make a deposit in my new account of amount 100.0
    Then I verify the account balance is 100.0


  Scenario: In order to retrieve all of my savings
    Given As a bank new client
    When I want to make a deposit in my new account of amount 100.0
    Then I verify the account balance is 100.0
    When I withdrawal for the 100.0 on my account
    Then I verify the account balance is 0.0
    
  Scenario: In order to retrieve some of my savings
    Given As a bank new client
    When I want to make a deposit in my new account of amount 100.0
    Then I verify the account balance is 100.0
    When I withdrawal for the 5.0 on my account
    Then I verify the account balance is 95.0

  Scenario: In order to check my operations
    Given As a bank new client
    When I want to make a deposit in my new account of amount 100.0
    Then I verify the account balance is 100.0
    When I withdrawal for the 5.0 on my account
    Then I verify the account balance is 95.0  
		When I want to retrieve the history
    Then I verify that history has 2 lines
    Then I verify that history last line amount is 5.0
    Then I verify that history last line balance is 95.0
    Then I verify that history last line operation is WITHDRAWAL
    Then I verify that history last line date is before system 'now' date
      


  Scenario Outline: Check of authorized/unauthorized operation
    Given As a bank new client
    When I want to make a deposit in my new account of amount 100.0
    Then I verify the account balance is 100.0
    When I <action> for the <value> on my account
    Then I verify the account balance is <balance> and <laststatus>
    Examples: 
      | action     | value   | balance    | laststatus |
      | deposit    |   100.0 |     200.0  |         OK |
      | deposit    |   200.0 |     300.0  |         OK |
      | withdrawal |   350.0 |     100.0  |         KO |
      | withdrawal |  2000.0 |     100.0  |         KO |
      
      
