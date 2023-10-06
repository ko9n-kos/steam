package steam.PO.pages;

import framework.elements.*;
import org.openqa.selenium.*;
import steam.PO.commonPageLogic.SteamBasePage;

import java.io.IOException;

public class MainPage extends SteamBasePage {

    protected static final Label lblSteamLogo = new Label(By.xpath("//span[@id='logo_holder']/a/img"));
    protected static final String categories = "//div[@class='store_nav']//a[@class='pulldown_desktop' and contains(text(),'%s')]";
    protected static final String genre = "//div[@id='genre_flyout']//child::a[normalize-space(text())= '%s']";

    public MainPage() {
        super(lblSteamLogo.findElement());
    }

    public void selectLanguage(String option) {
        defineLanguage(option);
    }

    public void selectCategory(String mainMenuOption) throws IOException {
        Label mainMenu = new Label(By.xpath(String.format(categories, localizationPropertySelect(mainMenuOption))));
        mainMenu.click();
    }

    public void selectGenre(String subMenuOption) throws IOException {
        Link subMenu = new Link(By.xpath(String.format(genre, localizationPropertySelect(subMenuOption))));
        subMenu.click();
    }
}