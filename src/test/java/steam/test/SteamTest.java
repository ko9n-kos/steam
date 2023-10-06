package steam.test;

import framework.BaseTest;
import org.testng.annotations.Test;
import steam.PO.pages.*;

import java.io.IOException;

public class SteamTest extends BaseTest {

    @Test
    public void SteamSearchGameTest() throws IOException {
        MainPage mainPage = new MainPage();
        mainPage.selectLanguage("Russian");
        mainPage.selectCategory("categories");
        mainPage.selectGenre("action");
        ActionCategoryPage actionCategoryPage = new ActionCategoryPage();
        actionCategoryPage.selectHighestDiscount();
        AgeCheckPage ageCheckPage = new AgeCheckPage();
        ageCheckPage.ageCheck();
        GamePage gamePage = new GamePage();
        gamePage.clickInstallSteam();
        SteamInstallationPage steamInstallationPage = new SteamInstallationPage();
        steamInstallationPage.downloadSteam();
    }
}