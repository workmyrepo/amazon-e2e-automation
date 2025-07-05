package base;

import java.awt.Desktop;
import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import utilities.ReportHelper;
import utilities.ReportingWebDriverListener;
import utilities.ScreenshotUtil;
import utilities.WaitUtil;

public class BaseTest {
    public WebDriver driver;
    public ConfigDataProvider config;
    public ExtentReports extent;
    public ExtentTest test;
    public String browserName;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(Method method, @Optional("chrome") String browser) {
        this.browserName = browser;
        config = new ConfigDataProvider();
        extent = ExtentManager.createInstance(browserName);

        String testName = method.getName().replace("_", " ");

        test = extent.createTest(testName)
                     .assignAuthor("Automation Tester")
                     .assignDevice(browserName)
                     .assignCategory("Amazon E2E Tests");

        // Detect if running in CI
        boolean isCI = Boolean.parseBoolean(System.getenv("CI"));
        System.out.println("CI Mode: " + isCI);

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-notifications");

                if (isCI) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());
                } else {
                    chromeOptions.addArguments("--start-maximized");
                }

                WebDriver rawDriver = new ChromeDriver(chromeOptions);
                ReportingWebDriverListener listener = new ReportingWebDriverListener(rawDriver, test);
                driver = new org.openqa.selenium.support.events.EventFiringDecorator<>(listener).decorate(rawDriver);
                break;

            case "firefox":
                WebDriver rawFirefoxDriver = new FirefoxDriver();
                ReportingWebDriverListener firefoxListener = new ReportingWebDriverListener(rawFirefoxDriver, test);
                driver = new org.openqa.selenium.support.events.EventFiringDecorator<>(firefoxListener).decorate(rawFirefoxDriver);
                break;

            case "edge":
                WebDriver rawEdgeDriver = new EdgeDriver();
                ReportingWebDriverListener edgeListener = new ReportingWebDriverListener(rawEdgeDriver, test);
                driver = new org.openqa.selenium.support.events.EventFiringDecorator<>(edgeListener).decorate(rawEdgeDriver);
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.get(config.get("baseUrl"));
        test.log(Status.INFO, "<b>Launched </b>" + browser + "<b> and navigated to </b>" + config.get("baseUrl"));
        ReportHelper.logStepWithScreenshot(driver, test);
        test.log(Status.INFO, "<b>Test started at: </b>" + java.time.LocalDateTime.now());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
                test.fail("Test failed: " + result.getThrowable());
                test.addScreenCaptureFromPath("../" + screenshotPath);
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.log(Status.SKIP, "Test skipped: " + result.getThrowable());
            } else {
                test.log(Status.PASS, "<b>Test passed successfully.</b>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                test.log(Status.INFO, "<b>Browser Closed!!</b>");
            }
            extent.flush();

            // Skip auto-open in CI
            boolean isCI = Boolean.parseBoolean(System.getenv("CI"));
            if (!isCI) {
                try {
                    File htmlFile = new File("test-output/ExtentReport_" + browserName + ".html");
                    Desktop.getDesktop().browse(htmlFile.toURI());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Skipping report auto-open in CI.");
            }
        }
    }
}
