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
    	
    	
    	    // try by id, otherwise by name
    	    By idLocator   = By.id("twotabsearchtextbox");
    	    By nameLocator = By.name("field‑keywords");

    	    WebElement searchInput;
    	    try {
    	        searchInput = WaitUtil.waitForElementVisible(driver, idLocator, 20);
    	    } catch (Exception e) {
    	        test.log(Status.INFO, "ID locator failed, falling back to name=field‑keywords");
    	        searchInput = WaitUtil.waitForElementVisible(driver, nameLocator, 20);
    	    }

    	    test.log(Status.INFO, "<b>Search box located</b>");
    	    // … the rest stays the same
    	

        
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
