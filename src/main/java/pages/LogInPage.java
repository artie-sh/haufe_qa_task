package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;


public class LogInPage extends BasePage {

    private String baseUrl;

    private By userNameInput = By.xpath("//input[@id='input_UserName']");
    private By passwordInput = By.xpath("//input[@id='input_password']");
    private By submitButton = By.xpath("//button[@name='Login']");

    public LogInPage(WebDriver driver, String baseUrl) {
        super(driver);
        this.baseUrl = baseUrl;
    }

    public void getOnLogInPage() {
        driver.get(baseUrl);
        waitUntilElementsVisible(Arrays.asList(userNameInput, passwordInput, submitButton));
    }

    public DashBoardPage logIn(String userName, String password) {
        driver.findElement(userNameInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
        return new DashBoardPage(driver);
    }
}
