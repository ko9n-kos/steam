package steam.PO.pages;

import framework.elements.Label;
import org.openqa.selenium.By;
import steam.PO.commonPageLogic.SteamBasePage;

import java.io.IOException;

public class SteamInstallationPage extends SteamBasePage {

    protected static final Label lblSteamTitle = new Label(By.xpath("//div[@id='about_greeting']/div[@class='steam_logo']"));

    public SteamInstallationPage() {
        super(lblSteamTitle.findElement());
    }

    public void downloadSteam() throws IOException {
        checkSteamIsDownloaded();
    }
}