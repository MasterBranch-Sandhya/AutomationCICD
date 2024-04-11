package rahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(xpath = "//li/button[contains(text(),'Checkout')]")
	WebElement checkoutButton;

	public String verifyProductIsPresentInCart(String productName) {
		String matchingProduct = null;
		for (WebElement cartItem : cartProducts)
			if (cartItem.getText().contains(productName)) {
				matchingProduct = cartItem.getText();
				System.out.println("Product Successfully added to cart: " + matchingProduct);
			}

		return matchingProduct;

	}

	public void clickOnCheckout() {
		waitForVisibilityOfElement(checkoutButton);
		waitElementToBeclickable(checkoutButton);
	    checkoutButton.click();
	}
}
