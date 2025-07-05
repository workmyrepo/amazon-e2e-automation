package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;

import utilities.ReportHelper;
import utilities.WaitUtil;

public class LoginPage {
    WebDriver driver;
    ExtentTest test;

 // this one exists both in headed & headless modes
    private By homeSignInBtn = By.id("nav-link-accountList");

    private By emailInput = By.id("ap_email_login");
    private By continueBtn = By.id("continue");
    private By passwordInput = By.id("ap_password");
    private By signInBtn = By.id("signInSubmit");

    public LoginPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    public void loginToAmazon(String username, String password) {
    	
    	// in LoginPage.loginToAmazon(), before waitForElementClickable(...)
    	try {
    	    By cookieAccept = By.id("sp-cc-accept");             // Amazon's “Accept Cookies” button
    	    WebElement acceptBtn = new WebDriverWait(driver, Duration.ofSeconds(5))
    	        .until(ExpectedConditions.elementToBeClickable(cookieAccept));
    	    acceptBtn.click();
    	    test.log(Status.INFO, "Closed cookie banner");
    	} catch (Exception ignored) { /* no cookie banner shown */ }

    	
    	WebElement HomeSignInButton = WaitUtil.waitForElementPresent(driver, homeSignInBtn, 20);
        test.log(Status.INFO, "<b>Clicked Sign In on Home Page </b>");
        HomeSignInButton.click();
        ReportHelper.logStepWithScreenshot(driver, test);
        

        WebElement emailField = WaitUtil.waitForElementVisible(driver, emailInput, 20);
        emailField.sendKeys(username);
        test.log(Status.INFO, "<b>Secured Username Entry : </b>");
      //  test.log(Status.INFO, "Entered username.");
        ReportHelper.logStepWithScreenshot(driver, test);

        WebElement continueButton = WaitUtil.waitForElementClickable(driver, continueBtn, 20);
        continueButton.click();
        //test.log(Status.INFO, "Clicked Continue after entering username");


        WebElement passwordField = WaitUtil.waitForElementVisible(driver, passwordInput, 20);
        passwordField.sendKeys(password);
        test.log(Status.INFO, "<b>Secured Password Entry : </b>");
        //test.log(Status.INFO, "Entered password. (masked)");
        ReportHelper.logStepWithScreenshot(driver, test);
        
        By primary = By.id("nav-link-accountList-nav-line-1");
        By fallback = By.id("nav-link-accountList");
        WebElement signInBtn;
        try {
            signInBtn = WaitUtil.waitForElementClickable(driver, primary, 20);
        } catch (Exception e) {
            test.log(Status.INFO, "Primary sign‑in ID failed, falling back to nav-link-accountList");
            signInBtn = WaitUtil.waitForElementClickable(driver, fallback, 20);
        }
        signInBtn.click();
       // test.log(Status.INFO, "Clicked Sign In button");
        ReportHelper.logStepWithScreenshot(driver, test);
    }
}
