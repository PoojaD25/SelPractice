package PageTest;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Page.CartPage;
import Page.ProductCatalogPage;
import driverUtil.BaseTest;

public class CartPageTest extends BaseTest {

	CartPage cartPage;
	ProductCatalogPage productcatlog;

	@Test
	public void verifySelectedItemPresentInCart() throws IOException, InterruptedException {
		productcatlog = login.logintoApp(prop.getProperty("username"), prop.getProperty("password"));
		String name = prop.getProperty("product");
		productcatlog.addToCartProdByName(name);
		cartPage = productcatlog.goTocartPage();

		boolean actual = cartPage.isItemPresentByName(name);
		Assert.assertTrue(actual, "no macth");
	}

	@Test
	public void verifyCheckoutFunc() throws IOException, InterruptedException {
		productcatlog = login.logintoApp(prop.getProperty("username"), prop.getProperty("password"));
		String name = prop.getProperty("product");
		productcatlog.addToCartProdByName(name);
		cartPage = productcatlog.goTocartPage();
		boolean actual = cartPage.isCheckoutClickable();
		Assert.assertTrue(actual);
		cartPage.clickCheckout();
	}

	@Test
	public void isCartEmpty() throws IOException, InterruptedException {
		productcatlog = login.logintoApp(prop.getProperty("username"), prop.getProperty("password"));
		
		cartPage = productcatlog.goTocartPage();

		int numberOfItem = cartPage.getcartItems().size();
		Assert.assertTrue(numberOfItem <1, "Item is available in cart.");
		String actual = cartPage.emptyCartText().toLowerCase();
		String expected = "No Products in Your Cart !".toLowerCase();

		Assert.assertEquals(actual, expected, "cart is not empty");
	}
}
