package utilities;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReportHelper {

    public static void logStepPass(ExtentTest test, String message) {
        Markup m = MarkupHelper.createLabel(message, ExtentColor.GREEN);
        test.pass(m);
    }

    public static void logStepFail(ExtentTest test, String message) {
        Markup m = MarkupHelper.createLabel(message, ExtentColor.RED);
        test.fail(m);
    }

    public static void logStepWarning(ExtentTest test, String message) {
        Markup m = MarkupHelper.createLabel(message, ExtentColor.ORANGE);
        test.warning(m);
    }

    public static void logStepInfo(ExtentTest test, String message) {
        Markup m = MarkupHelper.createLabel(message, ExtentColor.BLUE);
        test.info(m);
    }

    public static void logDataTable(ExtentTest test, String[][] data) {
        Markup m = MarkupHelper.createTable(data);
        test.info(m);
    }
    
    public static void logStepWithScreenshot(WebDriver driver, ExtentTest test) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "Step");
        try {
            test.info(
                MediaEntityBuilder.createScreenCaptureFromPath("../" + screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
