package steam.PO.pages;

import framework.Browser;
import framework.elements.BaseElement;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

import static framework.BaseTest.*;
import static framework.MyLogger.info;
import static steam.PO.commonLogic.Localization.localizationPropertySelect;

public class ActionCategoryPage extends SteamBasePage {

    protected static final Label lblTitle = new Label(By.xpath("//div[@id='SaleSection_56339']//child::div[@class='contenthubmaincarousel_ContentHubTitle_9tb4j ContentHubTitle']"));
    protected static String app = "//div[@class='salepreviewwidgets_StoreSaleDiscountBox_2fpFv' and contains(text(), '%s')]/parent::div/parent::div/parent::div//preceding-sibling::div[@class='salepreviewwidgets_TitleCtn_1F4bc']/a/div";
    protected static String specOffApp = "//div[@class='salepreviewwidgets_StoreSaleDiscountBox_2fpFv' and contains(text(), '%s')]/parent::div/parent::span/preceding-sibling::span/parent::div";
    protected static final Label lblDiscount = new Label(By.xpath("//div[@class='salepreviewwidgets_StoreSaleWidgetRight_1lRFu']//child::div[@class='salepreviewwidgets_StoreActionWidgetContainer_1gO7r']//child::div[@class='salepreviewwidgets_StoreSaleDiscountBox_2fpFv']"));
    protected static final Label lblSpecialDiscounts = new Label(By.xpath("//div[@id='SaleSection_61486']//div[@class='salepreviewwidgets_CapsuleBottomBar_6IVLn CapsuleBottomBar']//div[@class='salepreviewwidgets_StoreSaleDiscountBox_2fpFv']"));
    protected static final Button btnPrevious = new Button(By.xpath("//div[@id='SaleSection_61486']//div[@class='carousel']//child::button[contains(@class, 'CarouselBtnLeft') and @aria-label='previous']"));
    protected static final Button bottomOfFirstSection = new Button(By.xpath("//div[@id='SaleSection_93094']/child::div//child::button"));
    protected static final Label lblMatches = new Label(By.xpath("//div[@class='facetedbrowse_FacetedBrowseInnerCtn_hWbTI']/child::div//div/following-sibling::div[@class='facetedbrowse_FacetedBrowseMatchCount_3jXlH']"));
    protected static final String specOffers = "//div[@id='SaleSection_61486']//child::div[contains(text(), '%s')]";
    protected static final String discounted = "//div[@id='SaleSection_13268']//div[@class='saleitembrowser_SaleItemBrowserHeader_26iQ9 Panel Focusable']/child::div[contains(text(), '%s')]";
    protected static int max;

    public ActionCategoryPage() {
        super(lblTitle, "Action category page");
    }

    public void findHighestDiscount(BaseElement baseElement, String locator) {
        baseElement.waitToBePresented();
        List<WebElement> listOfDiscounts = baseElement.findElements();
        max = Integer.parseInt(baseElement.getInnerHTML().replace("-", "").replace("%", "").trim());
        for (WebElement el : listOfDiscounts) {
            if (Integer.parseInt(listOfDiscounts.get(listOfDiscounts.indexOf(el)).getAttribute("innerHTML")
                    .replace("-", "").replace("%", "").trim()) > max) {
                max = Integer.parseInt(listOfDiscounts.get(listOfDiscounts.indexOf(el)).getAttribute("innerHTML")
                        .replace("-", "").replace("%", "").trim());
            }
        }
        info("Highest discount is " + max);
        String oldTab = Browser.windowHandle();
        baseElement.clickViaJs(baseElement.getRandomElement(baseElement.findElements(By.xpath(String.format(locator, max)))));
        try {
            switchTab(oldTab);
        } catch (Exception ignored) {
        }
    }

    public void selectHighestDiscount() throws IOException {
        Label specialOffers = new Label(By.xpath(String.format(specOffers, localizationPropertySelect("specialOffers"))));
        if (specialOffers.isDisplayed()) {
            btnPrevious.click();
            btnPrevious.click();
            findHighestDiscount(lblSpecialDiscounts, specOffApp);
        } else {
            bottomOfFirstSection.waitElementToBeClickable();
            bottomOfFirstSection.scrollToElement();
            Button btnDiscounted = new Button(By.xpath(String.format(discounted, localizationPropertySelect("discounted"))));
            btnDiscounted.clickAndWait();
            lblMatches.waitToBePresented();
            wait.until(ExpectedConditions.textToBePresentInElement(lblMatches.findElement(), lblMatches.getText()));
            findHighestDiscount(lblDiscount, app);
        }
    }
}