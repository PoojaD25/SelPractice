package PageTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;

import org.testng.Assert;

import Page.CartPage;
import Page.CheckoutPage;
import Page.PaymentPage;
import Page.ProductCatalogPage;
import driverUtil.BaseTest;

public class CheckoutPageTest extends BaseTest {

	CheckoutPage checkoutPage;
	ProductCatalogPage productCatalogPage;
	CartPage cartPage;
	PaymentPage paymentPage;

	@Test
	public void verifyConfirmationMsg() throws IOException, InterruptedException {
		productCatalogPage = login.logintoApp(prop.getProperty("username"), prop.getProperty("password"));
		String name = prop.getProperty("product");
		productCatalogPage.addToCartProdByName(name);
		cartPage = productCatalogPage.goTocartPage();
		paymentPage = cartPage.clickCheckout();
		paymentPage.inputCountry(prop.getProperty("countryshortName"));
		paymentPage.SelectCountryByName(prop.getProperty("countryfullname"));
		checkoutPage= paymentPage.placeOrder();

		String expected = "Thankyou for the order.";
		Assert.assertEquals(checkoutPage.getConfirmationMsg(), expected.toUpperCase(), "no match");
	}

	@Test
	public void isOrderIdDisplayed() throws IOException, InterruptedException {
		productCatalogPage = login.logintoApp(prop.getProperty("username"), prop.getProperty("password"));
		String name = prop.getProperty("product");
		productCatalogPage.addToCartProdByName(name);
		cartPage = productCatalogPage.goTocartPage();
		paymentPage = cartPage.clickCheckout();
		paymentPage.inputCountry(prop.getProperty("countryshortName"));
		paymentPage.SelectCountryByName(prop.getProperty("countryfullname"));
		checkoutPage= paymentPage.placeOrder();
		boolean ispresent = checkoutPage.getOrderId().isDisplayed();
		Assert.assertTrue(ispresent, "element not found");

	}

}
