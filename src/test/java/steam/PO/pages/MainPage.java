package steam.PO.pages;

import framework.elements.*;
import org.openqa.selenium.*;

public class MainPage extends SteamBasePage {

    protected static final Label lblSteamLogo = new Label(By.xpath("//span[@id='logo_holder']/a/img"));

    public MainPage() {
        super(lblSteamLogo, "Main page");
    }
}