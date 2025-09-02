package TestCases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import PageObjects.dashboardPage;
import PageObjects.loginPage;
import PageObjects.pimPage;
import Utilities.dataProviders;
import Utilities.databaseConnection;

public class TC002_addEmployee extends baseTest{
	@Test(priority=1, groups= {"positive"}, dataProvider="New Employee", dataProviderClass=dataProviders.class)
	public void fillEmployeeData(String firstName,String middleName,String lastName,
		String employeeId, String photoPath) throws InterruptedException
	{
		Reporter.log("Test for employee id "+employeeId);
		loginPage lp = new loginPage(driver);
		dashboardPage dp = new dashboardPage(driver);
		pimPage pp = new pimPage(driver);
		
		lp.clearUserName();
		lp.clearPassword();
		lp.setUserName(propObj.getProperty("username"));
		lp.setPassword(propObj.getProperty("password"));
		logger.info("TC002 login");
		
		lp.clickButton();
		logger.info("TC002 click login button");
		
		dp.clickPIM();
		dp.clickAddEmployee();
		logger.info("TC002 click add employee button");
		
		pp.setFirstName(firstName);
		pp.setMiddleName(middleName);
		pp.setLastName(lastName);
		pp.clearEmpId();
		pp.setEmployeeId(employeeId);
		logger.info("TC002 fill new employee data");
		
		pp.clickSave();
		logger.info("TC 002 click save button");
		
		Thread.sleep(5000);
		
		//Add employee front end validation
		WebElement idField = wait.until
		(ExpectedConditions.presenceOfElementLocated
		(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input")));
		
		wait.until(ExpectedConditions.attributeToBeNotEmpty(idField, "value"));
		String text = idField.getAttribute("value");
		Assert.assertEquals(text, employeeId);
		logger.info("TC 002 finished add employee validation");
		
		dp.clickAccountDropdown();
		dp.clickLogout();
		logger.info("TC 002 click logout");
	}
	
	@Test(priority=2, groups= {"positive"}, dataProvider="New Employee", 
	dataProviderClass=dataProviders.class, dependsOnMethods="fillEmployeeData")
	public void checkDatabase(String firstName,String middleName,String lastName,
	String employeeId, String photoPath) throws SQLException
	{
		Reporter.log("Test for employee id "+employeeId);
		databaseConnection db = new databaseConnection();
		Connection conn = db.connect();
		logger.info("TC 002 finish connect to DB");
		
		Statement st = conn.createStatement();
		ResultSet result = st.executeQuery
		("SELECT employee_id FROM hs_hr_employee WHERE employee_id ="+employeeId);
		logger.info("TC 002 finish SQL query");
		
		if(result.next())
		{
			String empId = result.getString("employee_id");		
			Assert.assertEquals(empId, employeeId);
		}
		else
		{
			Assert.assertTrue(false);
			System.out.println("No employee id found");
		}
		logger.info("TC 002 finish DB employee validation");
		
		db.disconnect();
	}
	
}
