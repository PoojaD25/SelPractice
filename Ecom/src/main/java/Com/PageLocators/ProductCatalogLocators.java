package Com.PageLocators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCatalogLocators {

	public static String productInventorylist=".mb-3"; //css
	public static String productCard="b";  //css
	public static String product= "adidas original";  
	public static String headerCartBtn="[routerlink*='cart']"; //css
	public static String cartlbl="//label[text()='dynamic']";
	public static String prodCartbtn="//b[contains(text(), 'dynamicxpath')]/ancestor::div[@class='card-body']/button[last()]";  //xpath
	public static String prodCart=".card-body button:last-of-type";  //css
	public static String toastmsg="toast-container ";  //id

	
}
