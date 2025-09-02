package Utilities;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReportConfig implements ITestListener {
	
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	public Properties propObj;
	String repName;
	
	private String setBrowserName()
	{
		WebDriver driver = driverManager.getDriver();
		Capabilities caps = ((RemoteWebDriver)driver).getCapabilities();
		return caps.getBrowserName();
	}

	public void onStart(ITestContext context)
	{
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());
		repName = "Test Report "+timeStamp+".html";
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+repName);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Functional Testing");
		spark.config().setTheme(Theme.DARK);
		
		try {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");		
		propObj = new Properties();
		propObj.load(file);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		report = new ExtentReports();
		report.attachReporter(spark);
		
		report.setSystemInfo("Application", "OrangeHRM");
		report.setSystemInfo("Environment", propObj.getProperty("environment"));
		report.setSystemInfo("User", System.getProperty("user.name"));
		
		String getCurrentOS = context.getCurrentXmlTest().getParameter("os");
		report.setSystemInfo("os", getCurrentOS);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		report.setSystemInfo("browser", browser);
		
		List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
		report.setSystemInfo("groups", groups.toString());
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test case Passed is "+result.getName());
		test.info("Browser: "+setBrowserName());
		List<String> logs = Reporter.getOutput(result);
		for(String log : logs)
		{
			test.info(log);
		}
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case Failed is "+result.getName());
		test.log(Status.FAIL, "Test case is Failed due to "+result.getThrowable().getMessage());
		test.info("Browser: "+setBrowserName());
		List<String> logs = Reporter.getOutput(result);
		for(String log : logs)
		{
			test.info(log);
		}
		
		WebDriver driver = driverManager.getDriver();
		screenshot ss = new screenshot(driver);
		String imgPath = ss.capturedScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case SKIPPED is "+result.getName());
		test.log(Status.SKIP, "Test case is Skipped due to "+result.getThrowable().getMessage());	
		test.info("Browser: "+setBrowserName());
		List<String> logs = Reporter.getOutput(result);
		for(String log : logs)
		{
			test.info(log);
		}
	}
	
	public void onFinish(ITestContext context) 
	{
	    report.flush();
	    //if you want to automatically open the report once the test finished
	  	// Build path to the report file
	  	String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	  	File extentReport = new File(pathOfExtentReport);

	  	// Wait until the file is created (with timeout)
	  	int waitTimeInMillis = 3000; // Max 3 seconds
	  	int interval = 100;
	  	int waited = 0;

	  	while (!extentReport.exists() && waited < waitTimeInMillis) 
	  	{
	  	        try {
	  	            Thread.sleep(interval);
	  	            waited += interval;
	  	        } catch (InterruptedException e) {
	  	            e.printStackTrace();
	  	        }
	  	}

	  	// Try to open it after it's created
	  	if (extentReport.exists()) 
	  	{
	  	        try {
	  	            Desktop.getDesktop().browse(extentReport.toURI());
	  	        } catch (IOException e) {
	  	            e.printStackTrace();
	  	        }
	  	 } 
	  	else {
	  	        System.err.println("Extent report file not found after waiting.");
	  	     }
	  }
}
