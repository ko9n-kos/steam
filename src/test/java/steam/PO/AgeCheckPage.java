package steam.PO;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.DropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AgeCheckPage extends BasePage {

    protected static final By lblAgeCheckContainer = By.xpath("//div[@class='agegate_text_container']/child::h2");
    protected static final By drpDownYear = By.xpath("//select[@id='ageYear']");
    protected static final By btnView = By.xpath("//div[@class='agegate_btn_ctn']/a[@id='view_product_page_btn']");
    public AgeCheckPage(WebDriver driver) {
        super(driver, lblAgeCheckContainer);
    }

    public void adultConfirm(){
        DropDown age = new DropDown(drpDownYear);
        age.waitElementToBeClickable();
        age.selectFromDrpDown("1993");
        Button view = new Button(btnView);
        view.click();
    }
}