package steam.PO.pages;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

import static framework.MyLogger.info;

public class GamePage extends SteamBasePage {

    protected static final Label lblGameTitle = new Label(By.xpath("//div[@id='appHubAppName']"));
    protected static final Label lblGameDiscount = new Label(By.xpath("//div[@id='game_area_purchase']//div[contains(@id,'game_area_purchase_section_add_to_car')]//div[@class='discount_pct']"));

    public GamePage() {
        super(lblGameTitle, "Game page");
        checkDiscount();
    }

    public void checkDiscount() {
        Assert.assertTrue(lblGameDiscount.getInnerHTML().contains(Integer.toString(ActionCategoryPage.max)));
        info("Game discount is " + ActionCategoryPage.max);
    }
}