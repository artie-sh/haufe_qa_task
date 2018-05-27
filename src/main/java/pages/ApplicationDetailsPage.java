package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import static org.testng.Assert.assertTrue;

public class ApplicationDetailsPage extends BasePage {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private By applicationEvaluationForm = By.xpath("//application-evaluation");
    private String ratingTemplate = "//div[@name='evaluation']//span[@role='button'][%s]";
    private By commentInput = By.xpath("//textarea[@id='evaluationStatement']");
    private By submitCommentButton = By.xpath("//button/span[text()='Submit comment']");
    private By submitAssessmentButton = By.xpath("//button/span[text()='Submit assessment']");
    private String commentMessageTemplate = "//div[contains(@class, 'c-chat-message')][1]//div[contains(@class, 'c-chat-message__body')]/div[text()='%s']";
    private String ratingElementSuffix = "/../../div[contains(@class, 'c-chat-message__header')]//*[contains(@class, 'rating--active')]";


    public ApplicationDetailsPage(WebDriver driver) {
        super(driver);
        waitUntilElementsVisible(Arrays.asList(applicationEvaluationForm, commentInput, By.xpath(String.format(ratingTemplate, 1))));
    }

    public String getCommentText() {
        return "test comment " + LocalDateTime.now().format(formatter);
    }

    public void addAssessment(String commentText, int rating) {

        driver.findElement(commentInput).sendKeys(commentText);
        waitUntilElementsVisible(Arrays.asList(submitCommentButton));

        By ratingToSet = By.xpath(String.format(ratingTemplate, rating));
        waitUntilElementsVisible(Arrays.asList(ratingToSet));
        driver.findElement(ratingToSet).click();

        waitUntilElementsVisible(Arrays.asList(submitAssessmentButton));
        driver.findElement(submitAssessmentButton).click();
        waitForPageToBeReady();
        refreshPage();
    }

    public void assertCommentAndRatingSaved(String commentText, int rating) {
        By commentElement = By.xpath(String.format(commentMessageTemplate, commentText));
        By ratingElement = By.xpath(String.format(commentMessageTemplate + ratingElementSuffix, commentText));
        waitFor(commentElement);
        assertTrue(driver.findElements(ratingElement).size()==rating);
        waitUntilElementsVisible(Arrays.asList(commentElement));
    }
}
