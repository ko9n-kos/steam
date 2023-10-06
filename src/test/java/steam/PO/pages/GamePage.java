package steam.PO.pages;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;
import steam.PO.commonPageLogic.SteamBasePage;

import java.io.IOException;

public class GamePage extends SteamBasePage {

    protected static final Label lblGameTitle = new Label(By.xpath("//div[@id='appHubAppName']"));
    protected static final String install = "//div[@id='global_action_menu']/a/div[contains(text(),'%s')]";
    protected static final Label lblGameDiscount = new Label(By.xpath("//div[@id='game_area_purchase']//div[@id='game_area_purchase_section_add_to_cart_126432']//div[@class='discount_pct']"));

    public GamePage() {
        super(lblGameTitle.findElement());
        checkDiscount();
    }

    public void checkDiscount() {
        Assert.assertTrue(lblGameDiscount.getInnerHTML().contains(Integer.toString(ActionCategoryPage.max)));
    }

    public void clickInstallSteam() throws IOException {
        Button btnInstall = new Button(By.xpath(String.format(install, localizationPropertySelect("installSteam"))));
        btnInstall.click();
    }
}