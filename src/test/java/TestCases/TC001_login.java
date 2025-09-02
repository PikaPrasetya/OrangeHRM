package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.dashboardPage;
import PageObjects.loginPage;

public class TC001_login extends baseTest {
	
	
	@Test(groups= {"positive"})
	public void login()
	{
		loginPage lp = new loginPage(driver);
		lp.clearUserName();
		lp.clearPassword();
		lp.setUserName(propObj.getProperty("username"));
		lp.setPassword(propObj.getProperty("password"));
		logger.info("TC 001 Fill login credential");
		
		lp.clickButton();
		logger.info("TC 001 Click login button");
		
		dashboardPage dp = new dashboardPage(driver);
		try
		{
			if(dp.loginStatus() == true)
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		logger.info("TC 001 Finish login validation");
	}
		
}
