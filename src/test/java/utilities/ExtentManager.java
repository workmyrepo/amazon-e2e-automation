package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    // Now each test run creates its own instance (NOT static singleton)
    public static ExtentReports createInstance(String browser) {
        String fileName = "test-output/ExtentReport_" + browser + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
        spark.config().setDocumentTitle("Amazon Automation Report - " + browser);
        spark.config().setReportName("E2E Test Results on " + browser);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }
}
