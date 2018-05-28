package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public static final int WEBDRIVER_WAIT_TIME_SECONDS = new Integer(System.getProperty("webdriver.wait.time.seconds", "60"));

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = (WebDriverWait) new WebDriverWait(driver, WEBDRIVER_WAIT_TIME_SECONDS).ignoring(WebDriverException.class);
    }

    public boolean waitFor(By locator) {
        boolean result = true;
        try { wait.until(ExpectedConditions.presenceOfElementLocated(locator)); }
        catch (TimeoutException e) { result = false; }
        return result;
    }

    protected void waitForPageToBeReady() {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript(
                        "return document.readyState").
                        equals("complete");
            }
        });
    }

    protected void waitUntilElementsVisible(List<By> elements) {
        boolean result;
        try {
            for (By element : elements) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            }
            result = true;
        }
        catch (TimeoutException e) { result = false; }
        assertTrue(result, String.format("At least one of the elements %s is not visible on the page", elements));
    }

    public void refreshPage() {
        driver.navigate().refresh();
        waitForPageToBeReady();
    }
}
