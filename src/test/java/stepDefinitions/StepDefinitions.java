package stepDefinitions;

import io.cucumber.java.en.*;
import pages.SearchJobActions;

public class StepDefinitions {
    SearchJobActions jobActions = new SearchJobActions();

    @Given("I am on the NHS job search site")
    public void iAmOnTheNHSJobSearchSite() {
        jobActions.setUp();
    }

    @When("I search for a {string} role")
    public void iSearchForARole(String role) {
        jobActions.searchForAJob(role);
    }

    @And("I click the search Button")
    public void iClickTheSearchButton() {
        jobActions.submitSearchCriteria();
    }

    @And("I insert {string} as a preferred city")
    public void iInsertAsAPreferredCity(String city) {
        jobActions.specifyCity(city);
    }

    @When("I search for jobs using {string} as a preferred city")
    public void iSearchForJobsUsingAsAPreferredCity(String city) {
        jobActions.specifyCity(city);
    }

    @Then("I see jobs available for {string}")
    public void iSeeJobsAvailableFor(String role) {
        jobActions.verifyJobOnlySearchResult(role);
    }

    @When("I sort the result by date")
    public void iSortTheResultByDate() {
        jobActions.sortByNewestDate();
    }

    @Then("I should see the most recent date first")
    public void iShouldSeeTheMostRecentDateFirst() {
        jobActions.verifyTheMostRecentDay();
    }

    @Then("I see jobs available in {string}")
    public void iSeeJobsAvailableIn(String city) { jobActions.specifyCity(city);
    }

    @Then("I should see all available jobs")
    public void iShouldSeeAllAvailableJobs() {jobActions.allJobSearch();
    }

    @Then("I see jobs available for {string} within {int} miles of {string}")
    public void iSeeJobsAvailableForWithinMilesOf(String role, int arg1, String city) { jobActions.verifyJobMileSearchResult(role, city);
    }

    @Then("I should see a message that no jobs were found")
    public void iShouldSeeAMessageThatNoJobsWereFound() { jobActions.noResults();

    }
}
