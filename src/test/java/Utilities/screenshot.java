package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class screenshot {
	WebDriver driver;
	
	public screenshot(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String capturedScreen(String testName)
	{
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFileName = System.getProperty("user.dir")+"\\screenshots\\"+testName+"_"+timeStamp+".PNG";
		File targetFile = new File(targetFileName);
		sourceFile.renameTo(targetFile);
		return targetFileName;
	}

}
