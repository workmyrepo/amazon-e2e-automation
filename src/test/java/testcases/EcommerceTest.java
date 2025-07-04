package testcases;

import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.*;

public class EcommerceTest extends BaseTest {


   
	@Test
	public void endToEndAmazonFlow_SingleProduct() throws InterruptedException {
	    LoginPage login = new LoginPage(driver);
	    HomePage home = new HomePage(driver);
	    ProductPage product = new ProductPage(driver);
	    CartPage cart = new CartPage(driver);

	    String productName = "iphone 16 128gb";

	    test.info("Logging into Amazon");
	    login.loginToAmazon(config.get("username"), config.get("password"));
	    test.pass("Successfully logged into Amazon");

	    Thread.sleep(3000);

	    test.info("Searching for product: " + productName);
	    home.searchProduct(productName);
	    test.pass("Search completed");

	    Thread.sleep(3000);

	    test.info("Clicking the first available product");
	    product.clickFirstAvailableProduct(productName);
	    test.pass("Clicked first available product");

	    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(tabs.size() - 1));
	    test.info("Switched to product tab");

	    Thread.sleep(3000);

	    test.info("Adding product to cart");
	    cart.addToCart();
	    test.pass("Product added to cart");

	    Thread.sleep(3000);
	}


}
