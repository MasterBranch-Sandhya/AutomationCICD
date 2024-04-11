package rahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h5/b")
	List<WebElement> allProducts;

	@FindBy(xpath = "//*[text()=' Add To Cart']")
	List<WebElement> addTocartButton;

	@FindBy(id = "toast-container")
	By cont;

	@FindBy(css = ".ng-animating")
	WebElement loaderIcon;

	public List<WebElement> getProductList() {
		return allProducts;
	}

	public void selectProduct(String productName) {
		List<WebElement> products = getProductList();
		for (int i = 0; i < products.size(); i++) {
			String pr = products.get(i).getText();
			if (pr.contains(productName))
				addTocartButton.get(i).click();
		}
		waitForElementToDisappear(loaderIcon);
	}

}
