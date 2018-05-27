package tests;

import bootstrap.Driver;
import bootstrap.EnvironmentParams;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;


public class BaseTest {

    protected static WebDriver driver;
    protected WebDriverWait wait;

    protected String baseUrl;
    protected String username;
    protected String password;
    protected String jobName;
    protected String candidateName;
    protected int ratingToSet;

    @BeforeMethod
    public void startUp() {
        driver = Driver.getDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, BasePage.WEBDRIVER_WAIT_TIME_SECONDS).ignoring(WebDriverException.class);
        EnvironmentParams envParams = new EnvironmentParams();

        baseUrl = envParams.getBaseUrl();
        username = envParams.getUsername();
        password = envParams.getPassword();
        jobName = envParams.getJobName();
        candidateName = envParams.getCandidateName();
        ratingToSet = envParams.getRatingToSet();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    protected void log(String logMessage) {
        Driver.logger.info(logMessage);
    }
}
