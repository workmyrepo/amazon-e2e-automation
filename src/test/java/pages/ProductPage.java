package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import utilities.ReportHelper;

import java.time.Duration;


public class ProductPage {
    WebDriver driver;
    ExtentTest test;
    // A general XPath to get all product titles in search results

    public ProductPage(WebDriver driver,ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    public void clickFirstAvailableProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
       By productLink = By.linkText("iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Black");
      
       driver.findElement(productLink).click();
       
    }

    
}
