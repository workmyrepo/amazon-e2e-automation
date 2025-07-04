package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitUtil;

public class LoginPage {
    WebDriver driver;
    private By homeSignInBtn = By.id("nav-link-accountList-nav-line-1");
    private By emailInput = By.id("ap_email_login");
    private By continueBtn = By.id("continue");
    private By passwordInput = By.id("ap_password");
    private By signInBtn = By.id("signInSubmit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginToAmazon(String username, String password) {
        
    	WebElement HomeSignInButton = WaitUtil.waitForElementClickable(driver, homeSignInBtn, 10);
        HomeSignInButton.click();
    	WebElement emailField = WaitUtil.waitForElementVisible(driver, emailInput, 10);
        emailField.sendKeys(username);

        WebElement continueButton = WaitUtil.waitForElementClickable(driver, continueBtn, 10);
        continueButton.click();

        WebElement passwordField = WaitUtil.waitForElementVisible(driver, passwordInput, 10);
        passwordField.sendKeys(password);

        WebElement signInButton = WaitUtil.waitForElementClickable(driver, signInBtn, 10);
        signInButton.click();
    }
}
