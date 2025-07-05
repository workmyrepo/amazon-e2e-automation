package testcases;

import java.util.ArrayList;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import utilities.ReportHelper;

public class EcommerceTest extends BaseTest {


   
	@Test
	public void endToEndAmazonFlow_SingleProduct() throws InterruptedException {
	    LoginPage login = new LoginPage(driver,test);
	    HomePage home = new HomePage(driver,test);
	    ProductPage product = new ProductPage(driver,test);
	    CartPage cart = new CartPage(driver,test);

	    String productName = "iphone 16 128gb";

	  //  test.info("Logging into Amazon");
	    ReportHelper.logStepInfo(test, "Signining into Amazon");
	    
	    login.loginToAmazon(config.get("username"), config.get("password"));
//	    test.pass("Successfully logged into Amazon");
	    ReportHelper.logStepPass(test, "SignIn Successfull! ✔️ ");

	    Thread.sleep(3000);// 
	    ReportHelper.logStepWithScreenshot(driver, test);
	    
	    ReportHelper.logStepInfo(test, "Searching for product: " + productName);
	    Thread.sleep(1000);
	    home.searchProduct(productName);
	   
	    
	    

	    Thread.sleep(3000);
	
	    

	    
	  // test.info("Clicking the first available product");
	    product.clickFirstAvailableProduct(productName);
	   ReportHelper.logStepPass(test, "Product Selected ✔️");
	   test.info("<b>Switched to product tab</b>");
	   Thread.sleep(1000);
	   ReportHelper.logStepWithScreenshot(driver, test);
	    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(tabs.size() - 1));
	   

	    Thread.sleep(3000);

//	    test.info("Adding product to cart");
	    ReportHelper.logStepInfo(test, "Adding "+ productName +" to cart "  );
	    cart.addToCart();
	    ReportHelper.logStepPass(test, "Product Added Successfully ✔️");
	    Thread.sleep(5000);
	    ReportHelper.logStepWithScreenshot(driver, test);
	    Thread.sleep(3000);
	}

	
}
