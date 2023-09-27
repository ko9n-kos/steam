package steam.PO;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static framework.BrowserFactory.downloadDir;
import static framework.PropertyReader.getProperties;

public class SteamInstallationPage extends BasePage {

    protected static final By steamTitle = By.xpath("//div[@id='about_greeting']/div[@class='steam_logo']");
    protected static String btmDownloadSteam = "//div[@id='about_greeting']//a[contains(text(), '%s')]";
    protected static String filePath;

    public SteamInstallationPage(WebDriver driver) {
        super(driver, steamTitle);
    }

    public boolean waitDownloading(File file) throws IOException {
        return new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(getProperties("configuration.properties", "timeOut"))))
                .until(webDriver -> file.canRead());
    }

    public void downloadSteam() throws IOException {
        filePath = downloadDir + getProperties("configuration.properties", "fileName");
        Button install = new Button(By.xpath(String.format(btmDownloadSteam, localizationPropertySelect("downloadSteam"))));
        install.click();
        File file = new File(filePath);
        Assert.assertTrue(waitDownloading(file));
        file.delete();
    }
}