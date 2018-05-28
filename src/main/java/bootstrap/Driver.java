package bootstrap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.logging.Logger;


public class Driver {

    protected static WebDriver driver;
    public static Logger logger = Logger.getLogger("Haufe Test");

    public static WebDriver getDriver() {
        Dimension d = new Dimension(1920, 1080);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(d);
        logger.info(driver.manage().window().getSize().toString());
        return driver;
    }
}
