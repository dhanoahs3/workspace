#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template


Feature: Car Trading
  I want to buy and sell
  
  @BuyCar
  Scenario: Buying a second hand car
  Given I go to buy car
  And Car must of 'General motors'
  And Car can be blue,black,grey in color
  When I search for car
  And I select city as 'Toronto'
  Then I should get 'blue' cars in results
  And Cars must be atleast 3 years old
  But Cars should not be damaged
  And deliver this car to 123,NYC,New york,USA
  
  



