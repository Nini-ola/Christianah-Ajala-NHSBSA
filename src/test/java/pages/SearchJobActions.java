package pages;

import org.openqa.selenium.WebDriver;
import utils.WebDriverConfig;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchJobActions {
    WebDriver driver = WebDriverConfig.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    SearchJobObject searchJob = new SearchJobObject(driver);

    public void setUp(){
        driver.get("https://www.jobs.nhs.uk/candidate/search");
        driver.manage().window().maximize();
    }

    public void tearDown(){
        driver.quit();
    }

    public void verifyTheMostRecentDay(){
        searchJob.checkSortedResult();
    }

    public void searchForAJob(String role){
        searchJob.jobSearch(role);
    }

    public void verifyJobOnlySearchResult(String role){
        searchJob.verifyRoleResult(role);
    }

    public void verifyJobMileSearchResult(String role, String city){
        searchJob.verifyRoleDistanceResult(role, city);
    }

    public void sortByNewestDate(){
        searchJob.filterByDate();
    }

    public void specifyCity(String city){
        searchJob.locationSearch(city);
    }

    public void submitSearchCriteria(){
        searchJob.searchForJob();
    }

    public void allJobSearch(){
        searchJob.sendSearch();
    }

    public void noResults(){
        searchJob.noResultsFound();
    }

}
