package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
    Properties prop;

    public ConfigDataProvider() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return prop.getProperty(key);
    }
}
