package steam.test;

import framework.BaseTest;
import org.testng.annotations.Test;
import steam.PO.pages.*;

import java.io.IOException;

import static utils.ScreenshotTaker.takeScreenshot;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;

public class SteamTest extends BaseTest {
    @Test
    public void SteamSearchGameTest() throws IOException {
        MainPage mainPage = new MainPage();
        step("Main page is opened");
        mainPage.getHeader().selectLanguage("Russian");
        step("Language select");
        addAttachment("Language selected", takeScreenshot());
        mainPage.getMainMenu().mainMenuNavigation("categories", "action");
        step("Navigate to the action category page");
        ActionCategoryPage actionCategoryPage = new ActionCategoryPage();
        addAttachment("Action page", takeScreenshot());
        step("Action page is opened");
        actionCategoryPage.selectHighestDiscount();
        step("Select game with highest discount");
        try{
            AgeCheckPage ageCheckPage = new AgeCheckPage();
            addAttachment("Age check page", takeScreenshot());
            step("Age check page is opened");
            ageCheckPage.ageCheck();
        } catch (Exception ignored){
        }
        GamePage gamePage = new GamePage();
        step("Game page is opened");
        addAttachment("Highest discount game", takeScreenshot());
        gamePage.getHeader().clickInstall();
        step("Click install button from header");
        SteamInstallationPage steamInstallationPage = new SteamInstallationPage();
        step("Installation page is opened");
        addAttachment("Installation page", takeScreenshot());
        steamInstallationPage.downloadAndCheck();
        step("Download steam");
    }
}