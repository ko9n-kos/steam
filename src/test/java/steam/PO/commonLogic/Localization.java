package steam.PO.commonLogic;

import java.io.IOException;

import static framework.PropertyReader.getProperties;

public class Localization {

    public static String language = Header.language;

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
}