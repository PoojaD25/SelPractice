package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Com.PageLocators.LoginPageLocators;
import Utility.Reusable;

public class LoginPage extends Reusable{

	WebDriver driver;
	LoginPageLocators loginPageLocator;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public LoginPage launchApp(String link) {
		 driver.get(link);
		 return new LoginPage(driver);
		 
	}
	
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public ProductCatalogPage logintoApp(String un, String pass) {
		driver.findElement(By.id(loginPageLocator.usernametxtBox)).sendKeys(un);
		driver.findElement(By.xpath(loginPageLocator.passkeytxtBox)).sendKeys(pass);
		ClickOn(driver.findElement(By.name(loginPageLocator.loginbtn)));
		waitforElementInvisibilty(By.id(loginPageLocator.toastmsg));
		
		return new ProductCatalogPage(driver);
	
	}
	
	public void visibleToastMsg() {
		waitforElementToappear( By.id(loginPageLocator.toastmsg));
		
	}
	
	
	
}
