package Utilities;
import org.openqa.selenium.WebDriver;

public class driverManager {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	
	public static void setDriver(WebDriver driverInstance)
	{
		driver.set(driverInstance);
	}
	
	public static void removeDriver()
	{
		driver.remove();
	}

}
