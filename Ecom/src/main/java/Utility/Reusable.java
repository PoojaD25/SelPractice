package Utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Page.CartPage;

public class Reusable {
	
	public  WebDriver driver; 
	public  JavascriptExecutor js=  (JavascriptExecutor)driver;
	public  WebDriverWait wait;
	
	
	public Reusable(WebDriver driver) {
		this.driver=driver;
	    wait= new WebDriverWait(driver,Duration.ofSeconds(15));
	    js=  (JavascriptExecutor)driver;
	}
	
	public void waitForPageToLoadByHeading(By locator, String heading) {
		wait.until(ExpectedConditions.textToBe(locator, heading));
	}

	public  void waitforElementToappear(By locator) {
		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public  void waitforElementInvisibilty(By locator) {
		 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator)));
	}
	
	
	public  void waitForElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	public  void ClickOn(WebElement element) {
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element)).click();;
	}
	
	public void clickByJs(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}
	
	public  void highlightElement(WebElement element) {
		
		js.executeScript("arguments[0].style.border='5px solid yellow'", element);
	}

	
	public void captureScreenshot() throws IOException {
	TakesScreenshot ts	= (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File des=new File(System.getProperty("user.dir") + "target");
		FileUtils.copyFile(src, des);
	}
	
	public CartPage goToCart( By locator) {
	clickByJs(driver.findElement(locator));
	return new CartPage(driver);	
	}
	
	
}
