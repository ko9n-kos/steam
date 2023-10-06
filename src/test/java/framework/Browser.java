package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static framework.PropertyReader.getProperties;

public class Browser extends DriverFactory {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public void getInstance() throws IOException {
        driver = DriverFactory.browserSetUp();
    }

    public void setUpWaiter() throws IOException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer
                .parseInt(getProperties("configuration.properties", "timeOut"))));
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void timeout() throws IOException {
        driver.manage().timeouts().implicitlyWait(Integer
                .parseInt(getProperties("configuration.properties", "timeOut")), TimeUnit.SECONDS);
    }

    public void getUrl() throws IOException {
        driver.get(getProperties("configuration.properties", "siteUrl"));
    }

    public static String windowHandle() {
        return driver.getWindowHandle();
    }

    public void quitDriver() {
        driver.quit();
    }

    public static void switchTab(String oldTab) {
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
    }
}