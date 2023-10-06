package framework;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class BaseTest extends Browser {

    public static SoftAssert softAssert = new SoftAssert();
    Browser browser = new Browser();

    @BeforeTest
    public void setUp() throws IOException {
        browser.getInstance();
        browser.setUpWaiter();
        browser.maximizeWindow();
        browser.timeout();
        browser.getUrl();
    }

    @AfterTest
    public void tearDown() {
        browser.quitDriver();
    }
}