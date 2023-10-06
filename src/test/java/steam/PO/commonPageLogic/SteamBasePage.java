package steam.PO.commonPageLogic;

import framework.BasePage;
import framework.elements.BaseElement;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static framework.PropertyReader.getProperties;

public class SteamBasePage extends BasePage {

    protected static final Label lblLanguagesPullDown = new Label(By.xpath("//span[@id='language_pulldown']"));
    protected static final Label lblLanguages = new Label(By.xpath("//a[@class='popup_menu_item tight']"));
    protected static final String lblLanguage = "//a[@class='popup_menu_item tight' and contains(text(), '%s')]";
    protected static String btmDownloadSteam = "//div[@id='about_greeting']//a[contains(text(), '%s')]";
    protected static String language;
    protected static String filePath;

    public SteamBasePage(WebElement pageTitle) {
        super(pageTitle);
    }

    public SteamBasePage(BaseElement title) {
        super(title);
    }

    public void defineLanguage(String value) {
        language = value;
        lblLanguagesPullDown.click();
        Label language = new Label(By.xpath(String.format(lblLanguage, value)));
        List<WebElement> listOfLanguages = lblLanguages.findElements();
        for (WebElement el : listOfLanguages) {
            if (el.getText().contains(value)) {
                language.click();
                wait.until(webDriver -> "complete".equals(((js.executeScript("return document.readyState")))));
                break;
            }
            softAssert.assertFalse(el.getText().contains(value));
        }
    }

    public static String localizationPropertySelect(String value) throws IOException {
        String propertyFile = null;
        switch (language) {
            case ("English"):
                propertyFile = "eng_loc.properties";
                break;
            case ("Russian"):
                propertyFile = "rus_loc.properties";
                break;
        }
        return getProperties(propertyFile, value);
    }

    public void clickInstallButton() throws IOException {
        Button install = new Button(By.xpath(String.format(btmDownloadSteam, localizationPropertySelect("downloadSteam"))));
        install.click();
    }

    public void checkSteamIsDownloaded() throws IOException {
        filePath = downloadDir + getProperties("configuration.properties", "fileName");
        clickInstallButton();
        File file = new File(filePath);
        Assert.assertTrue(wait.until(webDriver -> file.canRead()));
        file.delete();
    }
}
