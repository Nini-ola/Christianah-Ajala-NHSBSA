Feature: Job Search Test Functionality

  Background: User should be able to access the job search site
    Given I am on the NHS job search site

  Scenario: User Should be able to search for a job using only the job field
    When I search for a "Software Tester" role
    And I click the search Button
    Then I see jobs available for "Software Tester"
    When I sort the result by date
    Then I should see the most recent date first
