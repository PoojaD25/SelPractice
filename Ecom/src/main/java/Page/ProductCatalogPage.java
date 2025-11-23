package Page;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Com.PageLocators.CartPageLocators;
import Com.PageLocators.ProductCatalogLocators;
import Utility.Reusable;

public class ProductCatalogPage extends Reusable {
	WebDriver driver;
	ProductCatalogLocators productCatalogLocators;
	CartPageLocators carPageLocators;
	CartPage cartPage;

	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/**
	 * Description: get the list of all products present in inventory
	 * @return
	 */
	public List<WebElement> getInventoryProduct() {
		waitforElementToappear(By.cssSelector(productCatalogLocators.productInventorylist));
		List<WebElement> prodList = driver.findElements(By.cssSelector(productCatalogLocators.productInventorylist));

		return prodList;
	}

	/**
	 * Description: find product by its name
	 * @param prod
	 * @return
	 */
	public WebElement findProductByName(String prod) {
		WebElement found = null;
		List<WebElement>prodList  = getInventoryProduct();
		
		for (WebElement e : prodList) {
			found = e.findElement(By.cssSelector(productCatalogLocators.productCard));
			if(found.getText().equalsIgnoreCase(prod)) {
				return found;
			}

		}
		
		return found;
	}

	/**
	 * Description: Add product to cart by searching its by name and back traversing cart button
	 * @param prod
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void addToCartProdByName(String prod) throws IOException, InterruptedException {
		
		String xpathNew=productCatalogLocators.prodCartbtn.replace("'dynamicxpath'", prod .replace(prod, "'" +prod.toUpperCase() +"'"));
		WebElement cart=driver.findElement(By.xpath(xpathNew));
		highlightElement(cart); 
		cart.click();
		Thread.sleep(Duration.ofSeconds(5));
		 
		}
		

	/**
	 *Description: Navigate to cart page
	 */
	public CartPage goTocartPage() {
		By locator=By.cssSelector(productCatalogLocators.headerCartBtn);
		cartPage = goToCart(locator);

		waitForPageToLoadByHeading(By.cssSelector( CartPageLocators.cartheading),CartPageLocators.heading);
		return cartPage;
	}

}
