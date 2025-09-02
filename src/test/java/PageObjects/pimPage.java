package PageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pimPage extends basePage{
	
	private WebDriver driver;
    private WebDriverWait wait;

	public pimPage(WebDriver driver)
	{
	      super(driver);
	      this.driver = driver;
	      this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(name="firstName")
	WebElement firstName;
	
	@FindBy(name="middleName")
	WebElement middleName;
	
	@FindBy(name="lastName")
	WebElement lastName;
	
	@FindBy(xpath = "//label[text()='Employee Id']/../following-sibling::div/input")
    WebElement employeeId;
	
	@FindBy(xpath="//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")
	WebElement uploadPicButton;
	
	@FindBy(xpath="//button[normalize-space()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//a[text()='Personal Details']")
	WebElement personalDetails;
	
	@FindBy(xpath="//span[text()='Employee Id already exists']")
	WebElement existingId;
	
	public void setFirstName(String FirstName)
	{
		firstName.sendKeys(FirstName);
	}
	
	public void setMiddleName(String MiddleName)
	{
		middleName.sendKeys(MiddleName);
	}
	
	public void setLastName(String LastName)
	{
		lastName.sendKeys(LastName);
	}
	
	public void clearEmpId()
	{
        WebElement empIdField = wait.until(ExpectedConditions.elementToBeClickable(employeeId));
        empIdField.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
	}
	
	public void setEmployeeId(String id)
	{
		employeeId.sendKeys(id);
	}
	
	public void clickSave()
	{
		wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
		saveBtn.click();
	}
	
	public void clickUploadPic()
	{
		uploadPicButton.click();
	}
	
	public boolean successAddEmployee()
	{
		boolean status = personalDetails.isDisplayed();
		return status;
	}
	
	public boolean idStatus()
	{
		boolean status = existingId.isDisplayed();
		return status;
	}
}
