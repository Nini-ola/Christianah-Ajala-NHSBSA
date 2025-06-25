package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchJobObject;

import java.time.Duration;

public class SearchJobActions {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    SearchJobObject searchJob = new SearchJobObject(driver);

    public void setUp(){
        WebDriverManager.chromedriver().setup();
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

    public void sortByNewestDate(){
        searchJob.filterByDate();
    }

    public void specifyCity(String city){
        searchJob.locationSearch(city);
    }

    public void submitSearchCriteria(){
        searchJob.searchForJob();
    }

}
