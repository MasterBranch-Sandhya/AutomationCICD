package rahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tbody//td[2]")
	List<WebElement> allProductNames;
	
	public String verifyProductIsPresentOnOrdersPage(String productName)
	{
		String matchingName = null;
		for(WebElement name : allProductNames)
		{
			if(name.getText().equals(productName))
				matchingName = name.getText();
		}
		return matchingName;
	}

}
