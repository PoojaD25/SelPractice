package PageTest;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Page.CartPage;
import Page.PaymentPage;
import Page.ProductCatalogPage;
import driverUtil.BaseTest;

public class PaymentPageTest extends BaseTest 
{
	
PaymentPage paymentPage;
ProductCatalogPage productCatalogPage;
CartPage cartPage;

    @Test
	public void verifyPlaceOrderIsClickable() throws IOException, InterruptedException {
    	productCatalogPage = login.logintoApp(prop.getProperty("username"), prop.getProperty("password"));
		String name = prop.getProperty("product");
		productCatalogPage.addToCartProdByName(name);
		cartPage = productCatalogPage.goTocartPage();
		paymentPage=cartPage.clickCheckout();
		paymentPage.inputCountry(prop.getProperty("countryshortName"));
		paymentPage.SelectCountryByName(prop.getProperty("countryfullname"));
		paymentPage.placeOrder();
		Assert.assertTrue(paymentPage.isPopupDisplayed());
	}
    
    
}
