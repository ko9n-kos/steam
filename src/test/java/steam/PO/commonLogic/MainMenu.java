package steam.PO.commonLogic;

import framework.elements.Label;
import framework.elements.Link;
import lombok.Getter;
import org.openqa.selenium.By;

import java.io.IOException;

import static steam.PO.commonLogic.Localization.localizationPropertySelect;

@Getter
public class MainMenu {

    public static String mainMenu = "//div[@class='store_nav']//a[@class='pulldown_desktop' and contains(text(),'%s')]";
    public static String genre = "//div[@id='genre_flyout']//child::a[normalize-space(text())= '%s']";

    public void selectMainMenuOption(String mainMenuOption) throws IOException {
        Label menu = new Label(By.xpath(String.format(mainMenu, localizationPropertySelect(mainMenuOption))));
        menu.click();
    }

    public void selectSubMenuOption(String subMenuOption) throws IOException {
        Link subMenu = new Link(By.xpath(String.format(genre, localizationPropertySelect(subMenuOption))));
        subMenu.click();
    }

    public void mainMenuNavigation(String mainMenuOption, String subMenuOption) throws IOException {
        selectMainMenuOption(mainMenuOption);
        selectSubMenuOption(subMenuOption);
    }
}