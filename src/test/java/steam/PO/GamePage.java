package steam.PO;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class GamePage extends BasePage {

    protected static final By gameTitle = By.xpath("//div[@id='appHubAppName']");
    protected static final String btnInstall = "//div[@id='global_action_menu']/a/div[contains(text(),'%s')]";
    public GamePage(WebDriver driver) {
        super(driver, gameTitle);
    }

    public By getGameTitle(){
       return gameTitle;
    }

    public void clickInstallSteam() throws IOException {
        Button install = new Button(By.xpath(String.format(btnInstall, localizationPropertySelect("installSteam"))));
        install.click();
    }
}