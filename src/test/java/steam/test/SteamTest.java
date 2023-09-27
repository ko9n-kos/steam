package steam.test;

import framework.BaseTest;
import org.testng.annotations.Test;
import steam.PO.*;

import java.io.IOException;

public class SteamTest extends BaseTest {

    @Test
    public void SteamSearchGameTest() throws IOException {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectLanguage("Russian");
        mainPage.selectGenre("categories", "action");
        ActionCategoryPage actionCategoryPage = new ActionCategoryPage(driver);
        actionCategoryPage.selectHighestDiscount();
        GamePage gamePage = new GamePage(driver);
        gamePage.clickInstallSteam();
        SteamInstallationPage steamInstallationPage = new SteamInstallationPage(driver);
        steamInstallationPage.downloadSteam();
    }
}