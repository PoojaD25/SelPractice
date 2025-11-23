package PageTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import Page.ProductCatalogPage;
import driverUtil.BaseTest;

public class LoginPageTest extends BaseTest{

ProductCatalogPage productcatalog;

	
	@Test
	public void verifySuccessfulLogin() {
		String un=prop.getProperty("username");
		String pass=prop.getProperty("password");
		login.logintoApp(un,pass);
		login.visibleToastMsg();
		//productcatalog	=
	}
	
	@Test(dependsOnMethods="verifySuccessfulLogin")
	public void verifyPageTitle() {
		
		String actual=login.getPageTitle();
		String expected="Let's Shop";
		
		Assert.assertEquals(actual, expected, "no match");
	}
	
	

	
}
