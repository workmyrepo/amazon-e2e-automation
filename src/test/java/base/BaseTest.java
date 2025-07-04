package base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ConfigDataProvider;
import utilities.ExtentManager;
import utilities.ScreenshotUtil;

public class BaseTest {
    public WebDriver driver;
    public ConfigDataProvider config;
    public ExtentReports extent; // no longer static!
    public ExtentTest test;
    public String browserName; // keep track of which browser

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(Method method, @Optional("chrome") String browser) {
        this.browserName = browser;

        // Initialize Config
        config = new ConfigDataProvider();

        // Create a new ExtentReports instance for this browser
        extent = ExtentManager.createInstance(browserName);

        // Start the test logger
        test = extent.createTest(method.getName());

        // Browser setup
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--start-maximized");
                
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115 Safari/537.36");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        // Open base URL
        driver.get(config.get("baseUrl"));
        test.log(Status.INFO, "Launched " + browser + " and navigated to " + config.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            test.fail("Test failed: " + result.getThrowable());
            try {
                test.addScreenCaptureFromPath("../" + screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test skipped: " + result.getThrowable());
        } else {
            test.log(Status.PASS, "Test passed");
        }

        if (driver != null) {
            driver.quit();
            test.log(Status.INFO, "Browser closed");
        }

        // Write this browser's report to disk
        extent.flush();
    }
}
