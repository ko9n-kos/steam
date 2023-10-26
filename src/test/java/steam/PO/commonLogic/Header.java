package steam.PO.commonLogic;

import framework.elements.BaseElement;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

import static framework.MyLogger.info;
import static steam.PO.commonLogic.Localization.localizationPropertySelect;

public class Header {

    public final Label lblLanguagesPullDown = new Label(By.xpath("//span[@id='language_pulldown']"));
    public final Label lblLanguages = new Label(By.xpath("//a[@class='popup_menu_item tight']"));
    public String lang = "//a[@class='popup_menu_item tight' and contains(text(), '%s')]";
    public String downloadSteam = "//div[@id='global_action_menu']/a/div[contains(text(),'%s')]";
    public static String language;

    public BaseElement getDefinedLanguage(String value) {
        return new Label(By.xpath(String.format(lang, value)));
    }

    public BaseElement installSteam() throws IOException {
        return new Label(By.xpath(String.format(downloadSteam, localizationPropertySelect("downloadSteam"))));
    }

    public void clickInstall() throws IOException {
        installSteam().click();
    }

    public List<WebElement> listOfLanguages() {
        return lblLanguages.findElements();
    }

    public void selectLanguage(String value) {
        language = value;
        lblLanguagesPullDown.click();
        for (WebElement el : listOfLanguages()) {
            if (el.getText().contains(value)) {
                getDefinedLanguage(value).click();
                break;
            }
        }
        info(language + " language selected");
    }
}