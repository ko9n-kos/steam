package steam.PO;

import framework.BasePage;
import framework.elements.*;
import org.openqa.selenium.*;

import java.io.IOException;
import java.util.List;

import static framework.BaseTest.*;

public class MainPage extends BasePage {

    protected static final By steamLogo = By.xpath("//span[@id='logo_holder']/a/img");
    protected static final By languagesPullDown = By.xpath("//span[@id='language_pulldown']");
    protected static final By lblLanguages = By.xpath("//a[@class='popup_menu_item tight']");
    protected static final String lblCategories = "//div[@class='store_nav']//a[@class='pulldown_desktop' and contains(text(),'%s')]";
    protected static final String lblGenre = "//div[@id='genre_flyout']//child::a[normalize-space(text())= '%s']";
    protected static final String lblLanguage = "//a[@class='popup_menu_item tight' and contains(text(), '%s')]";

    public MainPage(WebDriver driver) {
        super(driver, steamLogo);
    }

    public void selectLanguage(String option) {
        language = option;
        Label languages = new Label(languagesPullDown);
        languages.click();
        Label language = new Label(By.xpath(String.format(lblLanguage, option)));
        List<WebElement> listOfLanguages = driver.findElements(lblLanguages);
        for (WebElement el : listOfLanguages) {
            if (el.getText().contains(option)) {
                language.click();
                wait.until(webDriver -> "complete".equals(((js.executeScript("return document.readyState")))));
                break;
            }
            softAssert.assertFalse(el.getText().contains(option));
        }
    }

    public void selectGenre(String mainMenuOption, String subMenuOption) throws IOException {
        Label mainMenu = new Label(By.xpath(String.format(lblCategories, localizationPropertySelect(mainMenuOption))));
        mainMenu.click();
        Link subMenu = new Link(By.xpath(String.format(lblGenre, localizationPropertySelect(subMenuOption))));
        subMenu.click();
    }
}