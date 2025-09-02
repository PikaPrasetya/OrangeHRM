package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class dashboardPage extends basePage {
	
	public dashboardPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h6[text()='Dashboard']")
	WebElement dashboardText;
	
	@FindBy(xpath="//span[normalize-space()='PIM']")
	WebElement PIM;
	
	@FindBy(xpath="//a[text()='Add Employee']")
	WebElement addEmployee;
	
	@FindBy(xpath="//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
	WebElement accountDropdown;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement logout;
	
	
	public void clickPIM()
	{
		PIM.click();
	}

	public void clickAddEmployee()
	{
		addEmployee.click();
	}
	
	public void clickAccountDropdown()
	{
		accountDropdown.click();
	}
	
	public void clickLogout()
	{
		logout.click();
	}
	
	public boolean loginStatus()
	{
		return dashboardText.isDisplayed();
	}
}
