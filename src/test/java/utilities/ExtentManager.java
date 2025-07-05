package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static ExtentReports createInstance(String browser) {
        String fileName = "test-output/ExtentReport_" + browser + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Amazon Automation Report");
        spark.config().setReportName("E2E Test Results - " + browser);
        spark.config().setEncoding("utf-8");
        spark.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester", "Srinu");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Chrome", browser);

        return extent;
    }
}
