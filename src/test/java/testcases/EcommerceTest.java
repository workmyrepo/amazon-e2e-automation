package testcases;

import base.BaseTest;

import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.*;

public class EcommerceTest extends BaseTest {

	@DataProvider(name = "excelData")
	public Object[][] getExcelData() {
	    return utilities.ExcelUtil.getTestData("src/test/resources/TestData.xlsx", "Sheet1");
	}

//    @DataProvider(name = "productData")
//    public Object[][] getProductData() {
//        return new Object[][] {
//            {"iphone 16 128gb"},
//            {"Samsung Galaxy S24"},
//            {"OnePlus 12 Pro"}
//        };
//    }

	@Test(dataProvider = "excelData")
	public void endToEndAmazonFlow(String productName) throws InterruptedException {
	    LoginPage login = new LoginPage(driver);
	    HomePage home = new HomePage(driver);
	    ProductPage product = new ProductPage(driver);
	    CartPage cart = new CartPage(driver);

	    test.info("Logging into Amazon");
	    login.loginToAmazon(config.get("username"), config.get("password"));
	    test.pass("Successfully logged into Amazon");

	    // Add a wait after login
	    Thread.sleep(2000 + new java.util.Random().nextInt(3000));

	    test.info("Searching for product: " + productName);
	    home.searchProduct(productName);
	    test.pass("Search completed");

	    Thread.sleep(2000 + new java.util.Random().nextInt(3000));

	    test.info("Clicking the first available product");
	    product.clickFirstAvailableProduct();
	    test.pass("Clicked first available product");

	    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(tabs.size() - 1));
	    test.info("Switched to new tab");

	    Thread.sleep(2000 + new java.util.Random().nextInt(3000));

	    test.info("Adding product to cart");
	    cart.addToCart();
	    test.pass("Product added to cart");

	    Thread.sleep(2000 + new java.util.Random().nextInt(3000));
	}

}
