
@tag
Feature: Error Validation
  I want to use this template for my feature file

  

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on ecommerece Page 
    When Logged in with <name> and <password>
   
    Then "Incorrect email or password." message displayed

    Examples: 
      | name                      | password        | 
      | contact1@rahulshetty.com  | Mumbai@123     | 
     
