package rahulShettyAcademy.stepDefinition;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import rahulShettyAcademy.TestComponents.BaseTest;
import rahulShettyAcademy.pageobjects.LandingPage;

public class StepDefinitionImplementation extends BaseTest {
	
	public LandingPage landingpage;
	@Given("I landed on Ecommerce Page")
	public void I_Landed_On_Ecommerce_Page() throws IOException
	{
		launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		landingpage.loginApplication(username, password);
	}

	@When("^I add the product (.+) to cart$")
	public void I_add_the_product_to_cart()
	{
		
	}
}
