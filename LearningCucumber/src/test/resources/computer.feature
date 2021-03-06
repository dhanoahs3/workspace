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

@BuyComputer
Feature: Buy computer
  I want to buy a computer

   Background: Buying Dell PC
   Given I want to buy  a PC
   And PC should be of Dell
  
  @BuyDesktop
  Scenario: Buying a desktop
  And It should be a desktop
  And It should be a monitor of 15 inches
  And a 'carbon' finish
  And I select a 2 TB hard drive
  And Ram should be 8 Gb
  And Price should be less than 30000
  And It should have atmost 2 drives
  
  
  
   @BuyLaptop
  Scenario: Buying a laptop
  And It should be a laptop
  And It should be a screen of 14 inches
  And a 'sleak silver' finish
  And I select a 1 TB hard drive
  And Ram should be 6 Gb
  And Price should be less than 40000
  And It should come with a bag
  
