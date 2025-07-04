package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    WebDriver driver;

    // A general XPath to get all product titles in search results
    private By productLinks = By.xpath("//div[@data-component-type='s-search-result']//h2/a");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstAvailableProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
       By productLink = By.linkText("iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Black");
        driver.findElement(productLink).click();
    }

    
}
