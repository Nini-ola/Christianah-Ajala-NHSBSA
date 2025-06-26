Feature: Job Search Test Functionality

  Background: User should be able to access the job search site
    Given I am on the NHS job search site

  Scenario: User Should be able to search for a job using only the job field
    When I search for a "Software Tester" role
    And I click the search Button
    Then I see jobs available for "Software Tester"

  Scenario: User Should be able to search for a job using only the location field
    When I search for jobs using "Leeds" as a preferred city
    And I click the search Button
    Then I see jobs available in "Leeds"

  Scenario: User Should be able to search for jobs without job title or location
    When I click the search Button
    Then I should see all available jobs

  Scenario: User Should be able to search for a job using only the job field
    When I search for a "Automation Test Analyst" role
    And I click the search Button
    Then I see jobs available for "Automation Test Analyst"
    When I sort the result by date
    Then I should see the most recent date first

  Scenario: User Should be able to search for a job using only the location field and sort based on newest posting
    When I search for jobs using "Birmingham" as a preferred city
    And I click the search Button
    Then I see jobs available in "Birmingham"
    When I sort the result by date
    Then I should see the most recent date first

  Scenario: User Should be able to search for jobs without job title or location and sort based on newest posting
    When I click the search Button
    Then I should see all available jobs
    When I sort the result by date
    Then I should see the most recent date first

  Scenario: User Should be able to search for a job using the job field and the location field and sort based on newest posting
    When I search for a "Tester" role
    And I insert "Manchester" as a preferred city
    And I click the search Button
    Then I see jobs available for "Tester" within 5 miles of "Manchester"
    When I sort the result by date
    Then I should see the most recent date first

  Scenario: User Should see a message when no jobs match the search criteria
    Given I am on the NHS job search site
    When I search for a "Rocket Scientist" role
    And I insert "Atlantis" as a preferred city
    And I click the search Button
    Then I should see a message that no jobs were found

  Scenario: User Should see a message when no jobs match the search criteria
    Given I am on the NHS job search site
    When I search for jobs using "Atlantis" as a preferred city
    And I click the search Button
    Then I should see a message that no jobs were found

  Scenario: User Should see a message when no jobs match the search criteria
    Given I am on the NHS job search site
    When I search for a "faia322c" role
    And I click the search Button
    Then I should see a message that no jobs were found
