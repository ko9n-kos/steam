package steam.PO.pages;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

import static framework.Browser.getFilePath;
import static framework.Browser.waitFileCanRead;
import static framework.MyLogger.info;
import static steam.PO.commonLogic.Localization.localizationPropertySelect;

public class SteamInstallationPage extends SteamBasePage {

    protected static final Label lblSteamTitle = new Label(By.xpath("//div[@id='about_greeting']/div[@class='steam_logo']"));
    protected static String installSteam = "//div[@id='about_greeting']//a[contains(text(), '%s')]";

    public SteamInstallationPage() {
        super(lblSteamTitle, "Steam installation page");
    }

    public void downloadAndCheck() throws IOException {
        Button btnDownload = new Button(By.xpath(String.format(installSteam, localizationPropertySelect("installSteam"))));
        btnDownload.click();
        File file = new File(getFilePath());
        Assert.assertTrue(waitFileCanRead(file));
        info("Steam downloaded");
        file.delete();
        info("Steam file deleted from local source");
    }
}