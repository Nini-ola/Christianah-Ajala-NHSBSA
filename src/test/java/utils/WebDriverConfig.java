package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverConfig {

    public static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver == null ){
            setDriver();
        }

        return driver;
    }

    public static void setDriver(){
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        boolean setHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        switch (browser){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if (setHeadless){
                    driver = new FirefoxDriver();
                }else{
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                    driver = new FirefoxDriver(options);
                }
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (setHeadless){
                    driver = new ChromeDriver();
                }else{
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    driver = new ChromeDriver(chromeOptions);
                }

        }
    }

    public static void terminateSession(){
        if (driver != null){
            driver.quit();
        }
    }


}
