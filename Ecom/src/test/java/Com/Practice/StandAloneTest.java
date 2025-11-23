package Com.Practice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

//System.setProperty("webdriver.chrome.driver", "C:\\Users\\2pooj\\Downloads\\Driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		// devjanidas245@gmail.com, Sel#test2

		driver.findElement(By.id("userEmail")).sendKeys("devjanidas245@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Sel#test2");
		driver.findElement(By.name("login")).sendKeys(Keys.ENTER);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement matched_prod = products.stream()
				.filter(e -> e.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("adidas original"))
				.findFirst().orElse(null);
		
		System.out.println(matched_prod + " element found");

		WebElement cart = matched_prod.findElement(By.cssSelector(".card-body button:last-of-type"));

		wait.until(ExpectedConditions.elementToBeClickable(cart)).click();

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));

		WebElement cart_button = driver.findElement(By.cssSelector("[routerlink*='cart']"));
		wait.until(ExpectedConditions.elementToBeClickable(cart_button)).click();

		List<WebElement> cart_item = driver.findElements(By.cssSelector(".cart h3"));
		boolean match = cart_item.stream().anyMatch(e -> e.getText().equalsIgnoreCase("adidas original"));
		Assert.assertTrue(match, "no match found");

		WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
		checkout.click();

		WebElement country = driver.findElement(By.xpath("//input[contains(@placeholder,'Country')]"));
		country.clear();

		Actions act = new Actions(driver);

		act.moveToElement(country).click().keyDown(Keys.SHIFT).sendKeys("ind").build().perform();

		List<WebElement> country_list = driver.findElements(By.xpath("//section[contains(@class,'ta-results')]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(country_list));
		String country_name = "British";
		WebElement shipping_country = driver
				.findElement(By.xpath("//button/span[contains(text(),'" + country_name + "')]"));
		shipping_country.click();

		WebElement placeOrderButton = driver.findElement(By.cssSelector(".actions a"));
		wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();

		String actualMsg = driver.findElement(By.tagName("h1")).getText();
		String expectedMsg = "Thankyou for the order.";
		Assert.assertEquals(actualMsg, expectedMsg.toUpperCase(), "no match");

		String orderId = driver.findElement(By.xpath("(//label/parent:: td[@class='em-spacer-1'])[2]")).getText();
		System.out.println(orderId);

	}
}
