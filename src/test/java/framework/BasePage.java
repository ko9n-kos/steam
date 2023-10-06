package framework;

import framework.elements.BaseElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class BasePage {

    public SoftAssert softAssert = BaseTest.softAssert;
    public JavascriptExecutor js = BaseElement.js;
    public WebDriverWait wait = Browser.wait;
    public String downloadDir = BaseTest.downloadDir;
    BaseElement baseElement;

    public BasePage(WebElement pageTitle) {
        checkIsCorrectPage(pageTitle);
    }

    public BasePage(BaseElement title) {
        baseElement = title;
    }

    public void checkIsCorrectPage(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Throwable e) {
            wait.until(webDriver -> "complete".equals(((js.executeScript("return document.readyState")))));
        }
    }
}