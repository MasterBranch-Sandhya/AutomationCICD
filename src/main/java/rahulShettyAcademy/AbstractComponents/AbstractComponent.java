package rahulShettyAcademy.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;

	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement ordersButton;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void waitElementToBeclickable(WebElement ele) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitForVisibilityOfElement(WebElement ele)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(2));
		w.until(ExpectedConditions.visibilityOf(ele));
	}

	
	public void goToCartPage() {
		
		//waitElementToBeclickable(cartButton);
		Actions actions = new Actions(driver);
		actions.moveToElement(cartButton).click().build().perform();
		//cartButton.click();
	}
	
	public void goToOrdersPage()
	{
		ordersButton.click();
	}

}
