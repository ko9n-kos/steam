package framework;

import framework.elements.BaseElement;

import static framework.Browser.waitForPageToLoad;
import static framework.MyLogger.info;

public class BasePage {
    BaseElement baseElement;
    String pageTitle;

    public BasePage(BaseElement title, String name) {
        baseElement = title;
        pageTitle = name;
        checkPage();
    }

    public void checkPage() {
        baseElement.waitVisibilityOfElement();
        waitForPageToLoad();
        info(pageTitle + " is opened");
    }
}