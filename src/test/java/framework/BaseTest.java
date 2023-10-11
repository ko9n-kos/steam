package framework;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class BaseTest extends Browser {
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