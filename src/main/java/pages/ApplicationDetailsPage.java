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
    private By setNextStepButton = By.xpath("//button/span[text()='Set next step']");
    private String commentMessageTemplate = "//div[contains(@class, 'c-chat-message')][1]//*[contains(@class, 'c-chat-message__body')]/*[text()='%s']";
    private String nextStepElementSuffix = "/../../div[contains(text(), 'invite for 1st interview')]";
    private By nextStepDropDown = By.xpath("//span[text()='Next step']");
    private By inviteForFirstInterviewOption = By.xpath("//a[text()='invite for 1st interview']");
    private By inviteForFirstInterviewSelected = By.xpath("//span[text()='invite for 1st interview']");


    public ApplicationDetailsPage(WebDriver driver) {
        super(driver);
        waitUntilElementsVisible(Arrays.asList(applicationEvaluationForm, commentInput, By.xpath(String.format(ratingTemplate, 1))));
    }

    public String getCommentText() {
        return "test comment " + LocalDateTime.now().format(formatter);
    }


    public void addComment(String commentText) {
        driver.findElement(commentInput).sendKeys(commentText);
        waitUntilElementsVisible(Arrays.asList(submitCommentButton));
    }


    public void addRating(int rating) {
        By ratingToSet = By.xpath(String.format(ratingTemplate, rating));
        waitUntilElementsVisible(Arrays.asList(ratingToSet));
        driver.findElement(ratingToSet).click();
        waitUntilElementsVisible(Arrays.asList(setNextStepButton));
    }

    public void submitNextStep() {
        waitUntilElementsVisible(Arrays.asList(setNextStepButton));
        driver.findElement(setNextStepButton).click();
        waitForPageToBeReady();
    }


    public void selectInviteForFirstInverview() {
        waitUntilElementsVisible(Arrays.asList(nextStepDropDown));
        driver.findElement(nextStepDropDown).click();
        waitUntilElementsVisible(Arrays.asList(inviteForFirstInterviewOption));
        driver.findElement(inviteForFirstInterviewOption).click();
        waitUntilElementsVisible(Arrays.asList(inviteForFirstInterviewSelected));
    }


    public void assertChangesSaved(String commentText) {
        refreshPage();
        By commentElement = By.xpath(String.format(commentMessageTemplate, commentText));
        By nextStepElement = By.xpath(String.format(commentMessageTemplate + nextStepElementSuffix, commentText));
        waitFor(commentElement);
        waitFor(nextStepElement);
        waitUntilElementsVisible(Arrays.asList(commentElement, nextStepElement));
    }
}
