package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ReportHelper;
import utilities.WaitUtil;

public class HomePage {

    WebDriver driver;
    private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");

    ExtentTest test;

    public HomePage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    public void searchProduct(String productName) {
        // **1) early screenshot so you see exactly what page looks like before any waiting**
        ReportHelper.logStepWithScreenshot(driver, test);

        // **2) wait for the SEARCH BOX to be present in the DOM** (helps on slower CI loads)
        WaitUtil.waitForElementPresent(driver, searchBox, 20);
        WebElement searchInput = WaitUtil.waitForElementVisible(driver, searchBox, 20);
        test.log(Status.INFO, "<b>Search box located</b>");
        
        // existing steps...
        searchInput.click();
        test.log(Status.INFO, "<b>Cleared search box</b>");
        ReportHelper.logStepWithScreenshot(driver, test);

        searchInput.sendKeys(productName);
        test.log(Status.INFO, "<b>Entered search term: </b>" + productName);
        ReportHelper.logStepWithScreenshot(driver, test);

        WebElement searchBtn = WaitUtil.waitForElementClickable(driver, searchButton, 10);
        test.log(Status.INFO, "<b>Search button located</b>");

        searchBtn.click();
        ReportHelper.logStepPass(test, "Product Search completed!");
        test.log(Status.INFO, "<b>List of Search Items</b>");
        ReportHelper.logStepWithScreenshot(driver, test);
    }

}
