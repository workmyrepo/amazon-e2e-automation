package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;

public class ReportingWebDriverListener implements WebDriverListener {

    private final WebDriver driver;
    private final ExtentTest test;

    public ReportingWebDriverListener(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    private void attachScreenshot(String description) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "Step");
        try {
            test.info(description,
                MediaEntityBuilder.createScreenCaptureFromPath("../" + screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
