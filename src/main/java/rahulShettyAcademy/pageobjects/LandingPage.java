package rahulShettyAcademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginButton;
	
	//.ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css="[class*='toast-error']")
	WebElement errorForInvalidEmailOrpassword;

	public void goToURL() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public void loginApplication(String email, String password) {
		waitElementToBeclickable(userEmail);
		userEmail.sendKeys(email);
		waitElementToBeclickable(userPassword);
		userPassword.sendKeys(password);
		waitElementToBeclickable(loginButton);
		loginButton.click();
	}
	
	public String getErrorMessage()
	{
		waitForVisibilityOfElement(errorForInvalidEmailOrpassword);
		String error = errorForInvalidEmailOrpassword.getText();
		System.out.println("Error Message : "+error);
		return error;
	}

}
