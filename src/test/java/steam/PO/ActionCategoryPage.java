package steam.PO;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

import static framework.BaseTest.*;
import static steam.PO.AgeCheckPage.*;

public class ActionCategoryPage extends BasePage {

    protected static final By title = By.xpath("//div[@id='SaleSection_56339']//child::div[@class='contenthubmaincarousel_ContentHubTitle_9tb4j ContentHubTitle']");
    protected static String linkApp = "//div[@class='salepreviewwidgets_StoreSaleDiscountBox_2fpFv' and contains(text(), '%s')]/parent::div/parent::div/parent::div//preceding-sibling::div[@class='salepreviewwidgets_TitleCtn_1F4bc']/a/div";
    protected static String linkSpecOffApp = "//div[@class='salepreviewwidgets_StoreSaleDiscountBox_2fpFv' and contains(text(), '%s')]/parent::div/parent::span/preceding-sibling::span/parent::div";
    protected static final By lblDiscount = By.xpath("//div[@class='salepreviewwidgets_StoreSaleWidgetRight_1lRFu']//child::div[@class='salepreviewwidgets_StoreActionWidgetContainer_1gO7r']//child::div[@class='salepreviewwidgets_StoreSaleDiscountBox_2fpFv']");
    protected static final By lblSpecialDiscounts = By.xpath("//div[@id='SaleSection_61486']//div[@class='salepreviewwidgets_CapsuleBottomBar_6IVLn CapsuleBottomBar']//div[@class='salepreviewwidgets_StoreSaleDiscountBox_2fpFv']");
    protected static final By btnPrevious = By.xpath("//div[@id='SaleSection_61486']//div[@class='carousel']//child::button[contains(@class, 'CarouselBtnLeft') and @aria-label='previous']");
    protected static final By bottomOfFirstSection = By.xpath("//div[@id='SaleSection_93094']/child::div//child::button");
    protected static final By lblMatches = By.xpath("//div[@class='facetedbrowse_FacetedBrowseInnerCtn_hWbTI']/child::div//div/following-sibling::div[@class='facetedbrowse_FacetedBrowseMatchCount_3jXlH']");
    protected static final String lblSpecialOffers = "//div[@id='SaleSection_61486']//child::div[contains(text(), '%s')]";
    protected static final String btnDiscounted = "//div[@id='SaleSection_13268']//div[@class='saleitembrowser_SaleItemBrowserHeader_26iQ9 Panel Focusable']/child::div[contains(text(), '%s')]";

    public ActionCategoryPage(WebDriver driver) {
        super(driver, title);
    }

    public void findHighestDiscount(By element, String locator) {
        Label label = new Label(element);
        label.waitToBePresented();
        List<WebElement> listOfDiscounts = label.findElements(element);
        int max = Integer.parseInt(label.findElement(element).getAttribute("innerHTML").replace("-", "").replace("%", "").trim());
        for (WebElement el : listOfDiscounts) {
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            if (Integer.parseInt(listOfDiscounts.get(listOfDiscounts.indexOf(el)).getAttribute("innerHTML")
                    .replace("-", "").replace("%", "").trim()) > max) {
                max = Integer.parseInt(listOfDiscounts.get(listOfDiscounts.indexOf(el)).getAttribute("innerHTML")
                        .replace("-", "").replace("%", "").trim());
            }
        }
        String oldTab = driver.getWindowHandle();
        label.clickViaJs(getRandomElement(label.findElements(By.xpath(String.format(locator, max)))));
        try {
            switchTab(oldTab);
            isAgeCheck();
        } catch (IndexOutOfBoundsException exception) {
            isAgeCheck();
        }
    }

    public void selectHighestDiscount() throws IOException {
        Label specialOffers = new Label(By.xpath(String.format(lblSpecialOffers, localizationPropertySelect("specialOffers"))));
        if (specialOffers.waitToBePresented().isDisplayed()) {
            Button previous = new Button(btnPrevious);
            previous.click();
            findHighestDiscount(lblSpecialDiscounts, linkSpecOffApp);
        } else {
            Button bottom = new Button(bottomOfFirstSection);
            bottom.waitElementToBeClickable();
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", bottom.findElement());
            Button discounted = new Button(By.xpath(String.format(btnDiscounted, localizationPropertySelect("discounted"))));
            discounted.click();
            Label matches = new Label(lblMatches);
            matches.waitToBePresented();
            wait.until(ExpectedConditions.textToBePresentInElement(matches.findElement(), matches.findElement().getText()));
            findHighestDiscount(lblDiscount, linkApp);
        }
    }

    public void isAgeCheck() {
        try {
            driver.findElement(lblAgeCheckContainer);
            AgeCheckPage ageCheckPage = new AgeCheckPage(driver);
            ageCheckPage.adultConfirm();
        } catch (NoSuchElementException e) {
            new GamePage(driver);
        }
    }
}