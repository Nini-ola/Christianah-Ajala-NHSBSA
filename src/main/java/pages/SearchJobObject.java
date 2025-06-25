package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Locale;

public class SearchJobObject {
    @FindBy(css = "[data-test=\"search-jobTitle-input\"]")
    private WebElement jobField;

    @FindBy(css = "[data-test=\"search-location-input\"]")
    private WebElement locationField;

    @FindBy(css = "[data-test=\"search-distance-input\"]")
    private WebElement distanceDropdown;

    @FindBy(css = "[data-test=\"search-result-publicationDate\"]>strong")
    public List<WebElement> datePosted;

    @FindBy(css = "[data-test=\"search-button\"]")
    private WebElement searchButton;

    @FindBy(css = "[data-test=\"sort-input\"]")
    private WebElement sortDropdown;

    @FindBy(css = "[data-test=\"search-result-query\"]")
    private WebElement resultHeader;

    public SearchJobObject(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void jobSearch(String jobTitle){
        jobField.sendKeys(jobTitle);
    }

    public void locationSearch(String city){
        locationField.sendKeys(city);
    }

    public void searchForJob(){
        searchButton.click();
    }

    public void filterByDate(){
        Select byDate = new Select(sortDropdown);
        byDate.selectByVisibleText("Date Posted (newest)");
    }

    public void verifyRoleResult(String role){
        String headerText = resultHeader.getText();
        Assert.assertTrue(headerText.contains(role));
        Assert.assertTrue(headerText.matches(String.format("\\d+ jobs found for %s", role)));
    }

    public void checkSortedResult(){
        String DatePosted = datePosted.get(0).getText().trim();
        LocalDate firstJobDate = LocalDate.parse(DatePosted, DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.UK));

        LocalDate today = LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();
        LocalDate expectedDate = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
                ? today.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY))
                : today;
        Assert.assertEquals(firstJobDate, expectedDate);
    }


}
