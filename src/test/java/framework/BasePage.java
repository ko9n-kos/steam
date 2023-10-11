package framework;

import framework.elements.BaseElement;

import static framework.Browser.waitForPageToLoad;

public class BasePage {

    BaseElement baseElement;

    public BasePage(BaseElement title) {
        baseElement = title;
        checkPage(title);
    }

    public void checkPage(BaseElement title) {
        title.waitVisibilityOfElement();
        waitForPageToLoad();
    }
}