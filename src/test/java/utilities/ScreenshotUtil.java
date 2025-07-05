package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String dest = "test-output/screenshots/" + screenshotName + "_" + timestamp + ".png";
            File destFile = new File(dest);
            destFile.getParentFile().mkdirs();
            Files.copy(src.toPath(), destFile.toPath());
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
