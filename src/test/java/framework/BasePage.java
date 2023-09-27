package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static framework.BaseTest.wait;
import static framework.PropertyReader.getProperties;

public class BasePage {

    protected WebDriver driver;
    protected static String language;

    public BasePage(WebDriver driver, By pageTitle) {
        this.driver = driver;
        Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(pageTitle))).isDisplayed());
    }

    public void switchTab(String oldTab) {
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
    }

    public String localizationPropertySelect(String value) throws IOException {
        String propertyFile = null;
        switch (language) {
            case ("English"):
                propertyFile = "eng_loc.properties";
                break;
            case ("Russian"):
                propertyFile = "rus_loc.properties";
                break;
        }
        return getProperties(propertyFile, value);
    }

    public WebElement getRandomElement(List<WebElement> elements) {
        Random random = new Random();
        return elements.get(random.nextInt(elements.size()));
    }
}