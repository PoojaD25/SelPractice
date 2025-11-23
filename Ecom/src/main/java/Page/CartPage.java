package Page;

import java.util.List;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Com.PageLocators.CartPageLocators;
import Utility.Reusable;

public class CartPage extends Reusable {
WebDriver driver;
CartPageLocators cartPageLocators;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public List<WebElement> getcartItems(){
		List<WebElement> items=driver.findElements(By.cssSelector(cartPageLocators.cartitemList));
		return items;
	}
	
	public boolean isItemPresentByName(String item) {
		
		boolean found=false;
		List<WebElement> cartProducts= getcartItems();
		Optional<WebElement> targetElementOptional = cartProducts.stream()
	            .filter(element -> element.getText().equalsIgnoreCase(item))
	            .findFirst();
		
		if (targetElementOptional.isPresent()) {
		    WebElement targetElement = targetElementOptional.get();
		    highlightElement(targetElement);
		    found=true;
		} else {
		    // Handle the case where the element is not found
		    System.out.println("Item not found: " + item);
		    found =false;
		}
		
		//boolean matchedProd=cartProducts.stream().anyMatch(e->e.getText().equalsIgnoreCase(item));
		
		 return found;
	}
	
	public String emptyCartText() {
		
		return driver.findElement(By.cssSelector(cartPageLocators.emptyCart)).getText();
	}
	
	public boolean isCheckoutClickable() {
		return driver.findElement(By.cssSelector(cartPageLocators.checkout)).isEnabled();
	}
	public PaymentPage clickCheckout() {
		 driver.findElement(By.cssSelector(cartPageLocators.checkout)).click();
		return new PaymentPage(driver);
	}
	
	

}
