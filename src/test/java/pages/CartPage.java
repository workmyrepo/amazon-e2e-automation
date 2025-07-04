package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    WebDriver driver;

    // Target the <span> wrapping the button instead of the input
    private By addToCartSpan = By.xpath("(//input[@id='add-to-cart-button'])[2]");

    public CartPage(WebDriver driver) {
        System.out.println("this is Cart constructor");
        this.driver = driver;
    }

    public void addToCart() {
        System.out.println("this is before add to cart");

        WebElement addToCart = driver.findElement(addToCartSpan);

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);

        // Log display and enabled
        System.out.println("Displayed: " + addToCart.isDisplayed());
        System.out.println("Enabled: " + addToCart.isEnabled());

        // Use JavaScript click
        addToCart.click();
        System.out.println("this is after add to cart");
    }
}
