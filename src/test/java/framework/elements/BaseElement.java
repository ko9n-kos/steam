package framework.elements;

import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static framework.PropertyReader.getProperties;

public class BaseElement {

    public static WebDriver driver = Browser.driver;
    public static JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = Browser.wait;
    protected By element;
    WebElement webElement;

    public BaseElement(By elementLocator) {
        element = elementLocator;
    }

    public WebElement findElement() {
        return waitToBePresented();
    }

    public boolean isPresent() {
        try {
            webElement = driver.findElement(element);
            return webElement.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDisplayed() {
        return waitToBePresented(element).isDisplayed();
    }

    public String getText() {
        return findElement().getText();
    }

    public String getInnerHTML() {
        return findElement().getAttribute("innerHTML");
    }

    public void scrollToElement() {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block:'center'});", findElement());
    }

    public List<WebElement> findElements(By element) {
        waitToBePresented(element);
        return driver.findElements(element);
    }

    public List<WebElement> findElements() {
        waitToBePresented(element);
        return driver.findElements(element);
    }

    public WebElement waitElementToBeClickable() {
        return wait.until(ExpectedConditions.elementToBeClickable(findElement()));
    }

    public void waitForPageToLoad() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer
                .parseInt(getProperties("configuration.properties", "timeOut"))));
        try {
            wait.until((ExpectedCondition<Boolean>) d -> {
                if (!(d instanceof JavascriptExecutor)) {
                    return true;
                }
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                return result instanceof Boolean && (Boolean) result;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebElement waitToBePresented() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public WebElement waitToBePresented(By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void clickViaJs(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public WebElement getRandomElement(List<WebElement> elements) {
        Random random = new Random();
        return elements.get(random.nextInt(elements.size()));
    }

    public void clickAndWait() throws IOException {
        click();
        waitForPageToLoad();
    }

    public void click() {
        waitElementToBeClickable().click();
    }
}