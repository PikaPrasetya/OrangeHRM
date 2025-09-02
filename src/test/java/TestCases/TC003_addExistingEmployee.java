package TestCases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageObjects.dashboardPage;
import PageObjects.loginPage;
import PageObjects.pimPage;
import Utilities.databaseConnection;
import Utilities.randomizer;

public class TC003_addExistingEmployee extends baseTest{
	
	@Test(groups= {"negative"})
	public void addExistingId() throws SQLException, InterruptedException
	{
		databaseConnection db = new databaseConnection();
		Connection conn = db.connect();
		logger.info("TC 003 finish connect to DB");
		
		Statement st = conn.createStatement();
		ResultSet result = st.executeQuery
		("SELECT employee_id FROM hs_hr_employee LIMIT 1;");
		logger.info("TC 003 finish SQL query");
		
		if(result.next() == false)
		{
			throw new SkipException("Skipping test due to no existing employeeId");
		}
		else
		{
			loginPage lp = new loginPage(driver);
			dashboardPage dp = new dashboardPage(driver);
			pimPage pp = new pimPage(driver);
			
			lp.clearUserName();
			lp.clearPassword();
			lp.setUserName(propObj.getProperty("username"));
			lp.setPassword(propObj.getProperty("password"));
			logger.info("TC 003 fill login credential");
			
			lp.clickButton();
			logger.info("TC 003 click login button");
			
			dp.clickPIM();
			dp.clickAddEmployee();
			logger.info("TC 003 click add employee button");
			
			pp.setFirstName(randomizer.randomAlphabet());
			pp.setMiddleName("");
			pp.setLastName(randomizer.randomAlphabet());
			pp.clearEmpId();
			pp.setEmployeeId(result.getString("employee_id"));
			logger.info("TC 003 fill existing id employee data");
			
			SoftAssert sa = new SoftAssert();		
			if(pp.idStatus() == true)
			{
				Assert.assertTrue(true);
			}
			else
			{
				sa.assertTrue(false); //use soft assert so that lines below still run on Fail condition
			}
			logger.info("TC 003 finish validate text existing id warning appear");
			
			//Validation save button is not working
			String urlBeforeSave = driver.getCurrentUrl();
			pp.clickSave();
			Thread.sleep(3000);
			if(urlBeforeSave.equals(driver.getCurrentUrl()))
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
			logger.info("TC 003 finish validate save button not working");
		}
	}
}
