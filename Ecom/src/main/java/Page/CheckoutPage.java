package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Com.PageLocators.CheckoutPageLocator;
import Utility.Reusable;

public class CheckoutPage extends Reusable {

	WebDriver driver;
	CheckoutPageLocator checkoutPageLocators;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	
	
	public String getConfirmationMsg() {
		WebElement element= driver.findElement(By.tagName(checkoutPageLocators.confirmationMsg));
		highlightElement(element);
		String text=element.getText();
		return text;
	}
	
	public WebElement getOrderId() {
		WebElement element=driver.findElement(By.xpath(checkoutPageLocators.orderId));
		highlightElement(element);
		return element;
	}
}
