package driverUtil;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Page.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	
	public WebDriver driver;
	public Properties prop;
	public FileInputStream file;
	public String filepath= "//src//main//java//Utility//config.properties";
	public LoginPage login;
	
	public WebDriver initializeDriver() throws IOException {
		
		file=new FileInputStream(System.getProperty("user.dir")+filepath);
		prop= new Properties();
		prop.load(file);
		
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		
		else
		{
			driver = null;
			}
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	@BeforeMethod
	public LoginPage goToWebsite() throws IOException {
		driver=initializeDriver();
	     login=new LoginPage(driver);
		login.launchApp(prop.getProperty("url"));
		return login;
		
	}
	
	
	
	
	@AfterMethod
	
	public void tearDown() {
		driver.quit();
	}
}
