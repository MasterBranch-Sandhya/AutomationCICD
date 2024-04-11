package rahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder = 'Select Country']")
	WebElement countryField;

	By countriesResult = By.cssSelector(".ta-results");

	@FindBy(css = ".ta-item")
	List<WebElement> lisOfCountries;

	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement placeorderButton;

	public void selectCountry(String countryName) {
		Actions actions = new Actions(driver);
		countryField.sendKeys(countryName);
		waitForElementToAppear(countriesResult);

		for (WebElement country : lisOfCountries) {
			String str = country.getText().trim();
			if (str.equalsIgnoreCase(countryName)) {
				waitElementToBeclickable(country);
				actions.moveToElement(country).click().build().perform();
			}
		}
	}

	public void clickOnPlaceOrder() {
		Actions actions = new Actions(driver);
		waitElementToBeclickable(placeorderButton);
		actions.moveToElement(placeorderButton).click().build().perform();
	}
}
