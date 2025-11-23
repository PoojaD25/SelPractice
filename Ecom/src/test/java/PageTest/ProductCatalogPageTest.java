package PageTest;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Page.CartPage;
import Page.ProductCatalogPage;
import driverUtil.BaseTest;

public class ProductCatalogPageTest extends BaseTest{

	
	ProductCatalogPage productCatalogPage;
	CartPage cartPage;
	
	@Test
	public void verifyProductByNameFound() {
		productCatalogPage=login.logintoApp(prop.getProperty("username"), prop.getProperty("password"));
		String name=prop.getProperty("product");
		WebElement element=productCatalogPage.findProductByName(name);
		String actual=element.getText().toLowerCase();
		Assert.assertEquals(actual, name, "no match found "+ actual);
	}
	
	@Test
	public void verifyProductAddedToCartSuccessfully() throws IOException, InterruptedException {
		productCatalogPage=login.logintoApp(prop.getProperty("username"), prop.getProperty("password"));
		String name=prop.getProperty("product");
		productCatalogPage.addToCartProdByName(name);
		cartPage=productCatalogPage.goTocartPage();
		boolean actual=cartPage.isItemPresentByName(name);
		Assert.assertEquals(actual, true,"added item not found");
	}
	
}
