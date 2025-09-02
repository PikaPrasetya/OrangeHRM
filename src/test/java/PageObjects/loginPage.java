package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginPage extends basePage{
	
	public loginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(name="username")
	WebElement userName;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginBtn;
	
	public void clearUserName()
	{
		userName.clear();
	}
	
	public void clearPassword()
	{
		password.clear();
	}
	
	public void setUserName(String username)
	{
		userName.sendKeys(username);
	}
	
	public void setPassword(String pswrd)
	{
		password.sendKeys(pswrd);
	}
	
	public void clickButton()
	{
		loginBtn.click();
	}
	
}
