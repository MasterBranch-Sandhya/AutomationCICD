package rahulShettyAcademy.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulShettyAcademy.TestComponents.BaseTest;
import rahulShettyAcademy.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest {

	@Test
	public void loginErrorValidation() {

		String email = "sanrthd@gmail.com";
		String password = "Aaditi@30";
		landingpage.loginApplication(email, password);
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation(String productName)
	{
		ProductCatalog productCatalog = new ProductCatalog(driver);
		productCatalog.selectProduct(productName);
		productCatalog.goToCartPage();

	}

}
