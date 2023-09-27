package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DropDown extends BaseElement {

    public DropDown(By locator) {
        super(locator);
    }

    public void selectFromDrpDown(String option) {
        Select drpDown = new Select(findElement());
        drpDown.selectByVisibleText(option);
    }
}