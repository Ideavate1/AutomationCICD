
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background: 
  Given I landed on Ecommerce Page


  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and Password <password>
    When I add the product <productname> to cart
    And Checkout <productname> and submit the order.
    Then "Thankyou for the order." displayed on the confirmationPage 

    Examples: 
      | username                | password        | productname        |
      | Ideavate@gmail.com      | Ideavate@123    | ZARA COAT 3        |

