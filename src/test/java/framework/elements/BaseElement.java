package framework.elements;

import framework.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static framework.BaseTest.*;

public class BaseElement {

    protected WebDriver driver = BaseTest.driver;
    protected By element;

    public BaseElement(By elementLocator) {
        element = elementLocator;
    }

    public WebElement findElement() {
        return driver.findElement(element);
    }

    public WebElement findElement(By element) {
        return driver.findElement(element);
    }

    public List<WebElement> findElements(By element) {
        return driver.findElements(element);
    }


    public WebElement waitElementToBeClickable() {
        return wait.until(ExpectedConditions.elementToBeClickable(findElement()));
    }

    public WebElement waitToBePresented() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void clickViaJs(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public void click() {
        waitElementToBeClickable().click();
    }
}