package Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Com.PageLocators.PaymentPageLocators;
import Utility.Reusable;

public class PaymentPage extends Reusable {
WebDriver driver;
PaymentPageLocators paymentPageLocators;

public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	
	public void inputCountry(String name) {
	
		/*
		 * Actions act = new Actions(driver);
		 * act.moveToElement(driver.findElement(By.xpath(paymentPageLocators.country))).
		 * click(). keyDown(Keys.SHIFT).sendKeys(name). build(). perform();
		 */
		driver.findElement(By.xpath(paymentPageLocators.country)).sendKeys(name);
		
	}
	
	public List<WebElement> getCountryAutoSuggestion(String name){
		inputCountry(name);
				
		List<WebElement> list= driver.findElements(By.xpath(paymentPageLocators.countryList));
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		 return list;
	}
	
	public void SelectCountryByName(String name) {
	String newxpath=paymentPageLocators.shipping_country.replace("dynamicxpath", name);

				driver.findElement(By.xpath(newxpath)).click();
	}
	
	
	public CheckoutPage placeOrder() {
		waitForElementClickable(driver.findElement(By.cssSelector(paymentPageLocators.placeOrder))); 
		return new CheckoutPage(driver);
	}
	
	public boolean isPopupDisplayed() {
		return driver.findElement(By.cssSelector(paymentPageLocators.popupMsg)).isDisplayed();
	}

}
