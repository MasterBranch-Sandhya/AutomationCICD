package rahulShettyAcademy.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "IPHONE 13 PRO";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("sanrthd@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Aaditi@30");
		driver.findElement(By.id("login")).click();

		List<WebElement> allProducts = driver.findElements(By.xpath("//h5/b"));
		for (int i = 0; i < allProducts.size(); i++) {
			String pr = allProducts.get(i).getText();
			if (pr.contains("ADIDAS ORIGINAL"))
				driver.findElements(By.xpath("//*[text()=' Add To Cart']")).get(i).click();
		}

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));

		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		WebElement toast = driver.findElement(By.cssSelector("#toast-container"));
		System.out.println(toast.getText());

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		for (WebElement p : cartProducts) {
			if (productName.equals(p.getText())) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.cssSelector("[placeholder = 'Select Country']")).sendKeys("India");

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		List<WebElement> var = driver.findElements(By.cssSelector(".ta-item"));
		for (WebElement a : var) {
			String str = a.getText().trim();
			if (str.equalsIgnoreCase("India")) {
				w.until(ExpectedConditions.elementToBeClickable(a));
				actions.moveToElement(a).click().build().perform();
			}
		}

		WebElement placeOrder = driver.findElement(By.xpath("//a[text()='Place Order ']"));
		w.until(ExpectedConditions.elementToBeClickable(placeOrder));
		actions.moveToElement(placeOrder).click().build().perform();

		String actualMessage = "THANKYOU FOR THE ORDER.";
		String expectedMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(actualMessage, expectedMessage);

	}
}
