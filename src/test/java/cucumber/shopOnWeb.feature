
@tag
Feature: Purchase the item on E-commerce website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce Page
  

  @tag2
  Scenario Outline: Positive Test Of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add the product <productName> to cart
    And Checkout <productName> and submit the order
    Then The confirmation message "THANKYOU FOR THE ORDER." is displayed 

    Examples: 
      | username                | password  | productName     |
      | sanrthd@gmail.com       | Aaditi@30 | ADIDAS ORIGINAL |
    
