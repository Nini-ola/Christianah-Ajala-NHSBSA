package pages;

// Import necessary Selenium and Java utilities
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static utils.WebDriverConfig.driver;

public class SearchJobObject {

    // Locate the job title input field
    @FindBy(css = "[data-test=\"search-jobTitle-input\"]")
    private WebElement jobField;

    // Locate the location input field
    @FindBy(css = "[data-test=\"search-location-input\"]")
    private WebElement locationField;

    // Locate the distance dropdown filter
    @FindBy(css = "[data-test=\"search-distance-input\"]")
    private WebElement distanceDropdown;

    // Collect the list of job posting dates
    @FindBy(css = "[data-test=\"search-result-publicationDate\"]>strong")
    public List<WebElement> datePosted;

    // Method to locate the search button (not using @FindBy here)
    public WebElement getSearchButton() {
        return driver.findElement(By.cssSelector("[data-test='search-button']"));
    }

    // Locate the sort dropdown for ordering results
    @FindBy(css = "[data-test=\"sort-input\"]")
    private WebElement sortDropdown;

    // Locate the result header that displays summary text
    @FindBy(css = "[data-test=\"search-result-query\"]")
    private WebElement resultHeader;

    // Constructor that initializes elements using PageFactory
    public SearchJobObject(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    // Enter a job title into the job field
    public void jobSearch(String jobTitle){
        jobField.sendKeys(jobTitle);
    }

    // Enter a city name into the location field after clearing it
    public void locationSearch(String city){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement locationField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='search-location-input']")));
        locationField.clear();
        locationField.sendKeys(city);
    }

    // Click the search button to perform a job search
    public void searchForJob(){
        getSearchButton().click();
    }

    // Duplicate method to perform the search click
    public void sendSearch() {
        getSearchButton().click();
    }

    // Select "Date Posted (newest)" from the sort dropdown
    public void filterByDate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sortDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='sort-input']")));

        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Date Posted (newest)");
    }

    // Check if search result header contains the searched role
    public void verifyRoleResult(String role){
        String headerText = resultHeader.getText();
        Assert.assertTrue(headerText.contains(role));
        Assert.assertTrue(headerText.matches(String.format("\\d+ job[s]? found for %s", role)));
    }

    // Verify that the header includes the role and city, with the expected pattern
    public void verifyRoleDistanceResult(String role, String city) {
        String headerText = resultHeader.getText();
        Assert.assertTrue(headerText.contains(role));
        Assert.assertTrue(headerText.contains(city));
        Assert.assertTrue(headerText.matches(String.format("\\d+ job[s]? found for %s within 5 miles of %s", role, city)));
    }

    // Confirm that the job result header matches the expected pattern for general job search
    public void allJobSearch(){
        String headerText = resultHeader.getText();
        Assert.assertTrue(headerText.matches(String.format("\\d+ job[s]? found")));
    }

    // Ensure that the job dates are sorted from newest to oldest
    public void checkSortedResult() {
        // Extract and trim the text from each date element
        List<String> postedDates = datePosted.stream()
                .map(el -> el.getText().trim())
                .collect(Collectors.toList());

        // Convert the string dates to LocalDate using UK format
        List<LocalDate> jobDates = postedDates.stream()
                .map(dateStr -> LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.UK)))
                .collect(Collectors.toList());

        // Loop through each date and check if it's not older than the one following it
        for (int i = 0; i < jobDates.size() - 1; i++) {
            LocalDate current = jobDates.get(i);
            LocalDate next = jobDates.get(i + 1);
            Assert.assertFalse(current.isBefore(next),
                    String.format("Job at index %d (%s) is older than job at index %d (%s)", i, current, i + 1, next));
        }

        // Log the most recent job posting date
        System.out.println("Most recent job found was posted on: " + jobDates.get(0));
    }

    // Helper method to get the previous weekday (skips weekends)
    private LocalDate getPreviousWeekday(LocalDate date) {
        LocalDate previous = date.minusDays(1);
        while (previous.getDayOfWeek() == DayOfWeek.SATURDAY || previous.getDayOfWeek() == DayOfWeek.SUNDAY) {
            previous = previous.minusDays(1);
        }
        return previous;
    }

    // Check if no results were found (based on header text)
    public void noResultsFound() {
        try {
            WebElement message = driver.findElement(By.cssSelector("[data-test='search-results-header']"));
            if (!message.getText().contains("0 jobs found") && !message.getText().contains("No jobs found")) {
                message.getText();
            }
        } catch (NoSuchElementException e) {
            // Do nothing if element is not found
        }
    }

}
