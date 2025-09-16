package com.SeleniumPractice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FillForm {

	// global declaration and initialization is done to locate element after click
	// action
	public String mismathPwdMsg = "//div[@id='toast-container']";
	
	//anshika@gmail.com
	//Iamking@000

	public void highlight(By locator, WebDriver driver) {
		WebElement element=driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='yellow'", element);

	}
	
	public void waitForElementToAppear(WebElement element, WebDriver d) {
		WebDriverWait w=new WebDriverWait(d, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(element));
	}

	public String isDisplayedMismatchPwdCriteria(WebElement element, WebDriver d) {
		
		element.sendKeys(Keys.ENTER);
		WebDriverWait w=new WebDriverWait(d, Duration.ofSeconds(10));
		String errMsg	=w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mismathPwdMsg))).getText();
		highlight(By.xpath(mismathPwdMsg), d);
		if (errMsg.equalsIgnoreCase("Password must be 8 Character Long!")) 
			System.out.println("--Password criteria not matched--");
			
		return errMsg;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/Acer/Downloads/driver/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.rahulshettyacademy.com/client");

		WebElement registerBtn = driver.findElement(By.xpath("//a[text()='Register']")); // xpath
		registerBtn.click();

		WebElement firstName = driver.findElement(By.id("firstName")); // id
		firstName.sendKeys("pooja");

		WebElement lastName = driver.findElement(By.cssSelector("input[formcontrolname='lastName']")); // classname
		lastName.sendKeys("das");

		WebElement email = driver.findElement(By.cssSelector("#userEmail")); // cssSelector
		email.sendKeys("devjanidas245@gmail.com");

		WebElement confirmPass = driver
				.findElement(By.cssSelector("input[placeholder='Confirm Passsword'][id='confirmPassword']")); // css
																												// combination
																												// of
																												// multiple
																												// attribute
		confirmPass.sendKeys("raj$Gir");

		
		// //css combination of id and class
		WebElement password = driver.findElement(By.cssSelector("input#userPassword.form-control"));
		password.sendKeys("raj$Gir");

		// note if class has space in between remove the spaces.

		WebElement phnNum = driver.findElement(By.id("userMobile"));
		phnNum.sendKeys("7369082317");

		WebElement radioBtnMale = driver.findElement(By.xpath("//input[@value='Male' and @type='radio']"));

		WebElement radioBtnFemale = driver.findElement(By.xpath("//input[@value='Female' and @type='radio']"));
		radioBtnFemale.click();

		// WebElement occupation=
		// driver.findElement(By.className("custom-select.ng-pristine.ng-valid.ng-touched"));
		WebElement occupation = driver.findElement(By.className("custom-select"));
		Select select = new Select(occupation);

		select.selectByVisibleText("Student");

		WebElement olderthan18 = driver.findElement(By.cssSelector("input[type='checkbox']"));
		olderthan18.click();

		WebElement submit = driver.findElement(By.name("login"));

		FillForm f = new FillForm();
		f.isDisplayedMismatchPwdCriteria(submit, driver);

	}

}
