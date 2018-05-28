package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;

public class DashBoardPage extends BasePage {


    private By dashboardHeader = By.xpath("//a[text()='Dashboard']");
    private By jobsDiv = By.xpath("//div/h1[contains(text(), 'Jobs')]");
    private String jobXpathTemplate = "//h3[@title='%s']";

    private By jobApplications = By.xpath("//job-applications");
    private String applicationByCandidateName = "//article//h4[text()='%s']";


    public DashBoardPage(WebDriver driver) {
        super(driver);
        waitUntilElementsVisible(Arrays.asList(dashboardHeader, jobsDiv));
    }

    public void clickOnJob(String jobName) {
        driver.findElement(By.xpath(String.format(jobXpathTemplate, jobName))).click();
        waitUntilElementsVisible(Arrays.asList(jobApplications));
    }

    public ApplicationDetailsPage getOnApplicationDetailsPage(String candidateName) {
        By application = By.xpath(String.format(applicationByCandidateName, candidateName));
        waitUntilElementsVisible(Arrays.asList(application));
        driver.findElement(application).click();
        return new ApplicationDetailsPage(driver);
    }
}
