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

@CNN
Feature: Checking top links of CNN

   @CNN
   Scenario Outline: Check top links for cnn
   Given I open '<Browser>'
   And go to '<URL>'
  # Then title should contain keywords world,politics,sports --->The relevant function for this feature is giving errors and needs to be checked
   Then top menu should have links  
  | Links     | Title   | 
  | Homepage  | XYZ     |
  | World     | ABC     | 
  
   Examples: Running the tests on different environments and browsers
  | Browser     |           URL                        | 
  | Chrome      |    https://www.reuters.com           |
  | Mozilla     |    https://www.reuters.com           | 
  | Chrome      |    https://www.staging.reuters.com   |
  | Mozilla     |    https://www.staging.reuters.com   |  