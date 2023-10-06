package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;
import java.util.HashMap;

import static framework.PropertyReader.getProperties;

public class DriverFactory {
    public static String browser;
    public static String downloadDir;

    public static WebDriver browserSetUp() throws IOException {
        WebDriver driver = null;
        browser = getProperties("configuration.properties","browser").toLowerCase();
        downloadDir = System.getProperty("user.dir") +
                getProperties("configuration.properties", "downloadDirectory");

        switch (browser){
            case "chrome":
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popus", 0);
                chromePrefs.put("download.default_directory", downloadDir);
                chromePrefs.put("safebrowsing.enabled", "true");
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.dir", downloadDir);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/csv, text/csv, text/plain, application/octet-stream doc xls pdf txt");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions.setProfile(profile));
                break;
            default:
                break;
        }
        return driver;
    }
}