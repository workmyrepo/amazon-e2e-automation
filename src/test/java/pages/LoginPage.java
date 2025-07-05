package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;

import utilities.ReportHelper;
import utilities.WaitUtil;

public class LoginPage {
    WebDriver driver;
    ExtentTest test;

    private By homeSignInBtn = By.id("nav-link-accountList-nav-line-1");
    private By emailInput = By.id("ap_email_login");
    private By continueBtn = By.id("continue");
    private By passwordInput = By.id("ap_password");
    private By signInBtn = By.id("signInSubmit");

    public LoginPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    public void loginToAmazon(String username, String password) {
    	
    	
        WebElement HomeSignInButton = WaitUtil.waitForElementClickable(driver, homeSignInBtn, 20);
        test.log(Status.INFO, "<b>Clicked Sign In on Home Page </b>");
        HomeSignInButton.click();
        ReportHelper.logStepWithScreenshot(driver, test);
        

        WebElement emailField = WaitUtil.waitForElementVisible(driver, emailInput, 20);
        emailField.sendKeys(username);
        test.log(Status.INFO, "<b>Secured Username Entry : </b>");
      //  test.log(Status.INFO, "Entered username.");//
        ReportHelper.logStepWithScreenshot(driver, test);

        WebElement continueButton = WaitUtil.waitForElementClickable(driver, continueBtn, 20);
        continueButton.click();
        //test.log(Status.INFO, "Clicked Continue after entering username");


        WebElement passwordField = WaitUtil.waitForElementVisible(driver, passwordInput, 20);
        passwordField.sendKeys(password);
        test.log(Status.INFO, "<b>Secured Password Entry : </b>");
        //test.log(Status.INFO, "Entered password. (masked)");
        ReportHelper.logStepWithScreenshot(driver, test);

        WebElement signInButton = WaitUtil.waitForElementClickable(driver, signInBtn, 20);
        signInButton.click();
       // test.log(Status.INFO, "Clicked Sign In button");
        ReportHelper.logStepWithScreenshot(driver, test);
    }
}
