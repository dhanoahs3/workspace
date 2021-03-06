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

@Leads
Feature: Managing leads
  I want to create and delete leads
  
Background: Logged In
  Given I am logged in zoho.com
  And I click on 'Leads' in top menu
  
  @CreateLead
  Scenario Outline: Creation of a lead
    When I go to 'create lead' page
    And enter and submit lead details
    Then I go to 'leads details' page
    And I verify leads details
    When I click on 'Leads' in top menu
    Then Lead should be present inside the grid
     #  Examples: 
     # | FirstName  | LastName | Email   |
     # | name1      |    last1 | success | 
     # | name2      |    last2 | Fail    |

 
  @DeleteLead
    Scenario Outline: Deletion of the lead
    When I select the lead
    And I click the delete button
    Then lead should be deleted
   


  
    
 