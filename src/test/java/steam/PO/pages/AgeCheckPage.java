package steam.PO.pages;

import framework.elements.Button;
import framework.elements.DropDown;
import framework.elements.Label;
import org.openqa.selenium.By;

import static utils.MyLogger.info;

public class AgeCheckPage extends SteamBasePage {

    protected static final Label lblAgeCheckContainer = new Label(By.xpath("//div[@class='agegate_text_container']/child::h2"));
    protected static final DropDown drpDownYear = new DropDown(By.xpath("//select[@id='ageYear']"));
    protected static final Button btnView = new Button(By.xpath("//div[@class='agegate_btn_ctn']/a[@id='view_product_page_btn']"));

    public AgeCheckPage() {
        super(lblAgeCheckContainer, "Age check page");
    }

    public void ageCheck() {
        selectYearOfBorn();
        clickView();
        info("Age is confirmed");
    }

    public void selectYearOfBorn() {
        drpDownYear.waitElementToBeClickable();
        drpDownYear.selectFromDrpDown("1993");
    }

    public void clickView() {
        btnView.click();
    }
}