package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import Utilities.driverManager;

public class baseTest {
	public WebDriver driver;
	public Properties propObj;
	public WebDriverWait wait;
	public Logger logger;
	
	@BeforeClass(groups= {"positive","negative"})
	@Parameters({"os","browser"})
	public void setup() throws IOException
	{
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		propObj = new Properties();
		propObj.load(file);	
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driverManager.setDriver(driver); //set the driver to driverManager to be used on other classes
		driver.get(propObj.getProperty("appURL"));
		
		logger = LogManager.getLogger(this.getClass()); // will get the log of the class
	}
	
	@AfterClass(groups= {"positive","negative"})
	public void tearDown()
	{
		driver.quit();
		driverManager.removeDriver();
	}
	
	
}
