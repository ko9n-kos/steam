package framework;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.io.InputStream;

public class ScreenshotTaker {
    public static InputStream takeScreenshot() throws IOException {
        return FileUtils.openInputStream(((TakesScreenshot) Browser.driver).getScreenshotAs(OutputType.FILE));
    }
}
