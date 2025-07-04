package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
    public static WebDriver startBrowser() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
