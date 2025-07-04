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

    // ✅ Updated XPath for selecting actual product titles
    //private By productLinks = By.xpath("//*[@id=\"ce0015a9-f05f-4041-806e-4e5cd249a4a5\"]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/a/h2");
    private By productLinks = By.linkText("iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Black");
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstAvailableProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ✅ Wait for all visible product links
        List<WebElement> products = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(productLinks)
        );

        if (products.size() > 0) {
            products.get(0).click();  // Click the first real product
            System.out.println("✅ Clicked on the first available product.");
        } else {
            System.out.println("❌ No product found on the page.");
        }
    }
}
