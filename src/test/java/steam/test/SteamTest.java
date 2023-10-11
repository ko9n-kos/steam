package steam.test;

import framework.BaseTest;
import org.testng.annotations.Test;
import steam.PO.pages.*;

import java.io.IOException;

public class SteamTest extends BaseTest {

    @Test
    public void SteamSearchGameTest() throws IOException {
        MainPage mainPage = new MainPage();
        mainPage.getHeader().selectLanguage("Russian");
        mainPage.getMainMenu().mainMenuNavigation("categories", "action");
        ActionCategoryPage actionCategoryPage = new ActionCategoryPage();
        actionCategoryPage.selectHighestDiscount();
        try{
            AgeCheckPage ageCheckPage = new AgeCheckPage();
            ageCheckPage.ageCheck();
        } catch (Exception ignored){
        }
        GamePage gamePage = new GamePage();
        gamePage.getHeader().clickInstall();
        SteamInstallationPage steamInstallationPage = new SteamInstallationPage();
        steamInstallationPage.downloadAndCheck();
    }
}