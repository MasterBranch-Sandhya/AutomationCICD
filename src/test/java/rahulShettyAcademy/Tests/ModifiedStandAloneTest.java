package rahulShettyAcademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulShettyAcademy.TestComponents.BaseTest;
import rahulShettyAcademy.pageobjects.CartPage;
import rahulShettyAcademy.pageobjects.CheckoutPage;
import rahulShettyAcademy.pageobjects.FinalPage;
import rahulShettyAcademy.pageobjects.OrdersPage;
import rahulShettyAcademy.pageobjects.ProductCatalog;

public class ModifiedStandAloneTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", retryAnalyzer = Retry.class)
	public void shopOnWeb(HashMap<String, String> input) throws IOException {

		landingpage.loginApplication(input.get("email"), input.get("password"));

		ProductCatalog productCatalog = new ProductCatalog(driver);
		productCatalog.selectProduct(input.get("product"));
		productCatalog.goToCartPage();

		CartPage cartpage = new CartPage(driver);
		String capturedProduct = cartpage.verifyProductIsPresentInCart(input.get("product"));
		// Assert.assertEquals(capturedProduct, productName);
		cartpage.clickOnCheckout();

		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.selectCountry("India");
		checkoutpage.clickOnPlaceOrder();

		FinalPage finalpage = new FinalPage(driver);
		String expectedMessage = finalpage.successmessage();
		String actualMessage = "THANKYOU FOR THE ORDER.";
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	// To Verify "ADIDAS ORIGINAL" is displaying in orders page
	@Test(dependsOnMethods = { "shopOnWeb" }, enabled = false)
	public void orderHistoryTest() {

		landingpage.loginApplication("sanrthd@gmail.com", "Aaditi@30");
		landingpage.goToOrdersPage();
		OrdersPage orderspage = new OrdersPage(driver);
		String product = orderspage.verifyProductIsPresentOnOrdersPage(productName);
		Assert.assertEquals(productName, product);

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// way-1: Object[][] data = {{"sanrthd@gmail.com","Aaditi@30","ADIDAS
		// ORIGINAL"},{"SandhyaTest@gmail.com","Sandhya@Test1","ZARA COAT 3"}};

		/*
		 * //Way-2: Using HashMap HashMap<String, String> set1 = new HashMap<String,
		 * String>(); set1.put("email", "sanrthd@gmail.com"); set1.put("password",
		 * "Aaditi@30"); set1.put("product", "ADIDAS ORIGINAL");
		 * 
		 * HashMap<String, String> set2 = new HashMap<String, String>();
		 * set2.put("email", "SandhyaTest@gmail.com"); set2.put("password",
		 * "Sandhya@Test1"); set2.put("product", "ZARA COAT 3");
		 */

		// Way-3: Using JSON file
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\rahulShettyAcademy\\data\\PurchaseOrder.json";

		List<HashMap<String, String>> jsondata = getJsonDataToMap(filePath);

		Object[][] data = { { jsondata.get(0) }, { jsondata.get(1) } };
		return data;
	}

	

}
