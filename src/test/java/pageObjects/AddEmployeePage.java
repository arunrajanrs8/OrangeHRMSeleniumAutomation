package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import pojo.Employee;
import utilities.WaitHelper;

public class AddEmployeePage  {
	
	public WebDriver driver;
	WaitHelper waitHelper;
	
	public AddEmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}

	By firstNameTxt = By.name("firstName");
	By middleNameTxt = By.name("middleName");
	By lastNameTxt = By.name("lastName");
	By empIDTxt = By.xpath("//label[text()='Employee Id']/following::input[1]");
	By loginDtlsToggle = By.xpath("//input[@type='checkbox']/following-sibling::span");
	By userNameTxt = By.xpath("//label[text()='Username']/following::input[1]");
	By statusRadioBtn = By.xpath("//label/input[@type='radio' and @value='1']/following-sibling::span");
	By passwordTxt = By.xpath("//label[text()='Password']/following::input[1]");
	By confirmPwdTxt = By.xpath("//label[text()='Confirm Password']/following::input[1]");
	By saveBtn = By.xpath("//div[@class='oxd-form-actions']/button[2]");
	
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public void enterEmployeeDetails(Employee empDtls) {
		
		waitHelper.waitForElementbyPath(empIDTxt, Duration.ofSeconds(10));
		driver.findElement(firstNameTxt).sendKeys(empDtls.getFirstName());
		driver.findElement(middleNameTxt).sendKeys(empDtls.getMiddleName());
		driver.findElement(lastNameTxt).sendKeys(empDtls.getLastName());
		
		driver.findElement(empIDTxt).click();
		driver.findElement(empIDTxt).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(empIDTxt).sendKeys(Keys.DELETE);

		driver.findElement(empIDTxt).sendKeys(empDtls.getEmpId());
		driver.findElement(loginDtlsToggle).click();
		driver.findElement(userNameTxt).sendKeys(empDtls.getUserName());
		driver.findElement(statusRadioBtn).click();
		driver.findElement(passwordTxt).sendKeys(empDtls.getPassword());
		driver.findElement(confirmPwdTxt).sendKeys(empDtls.getPassword());	
		
	}
	
	public void saveEmployeeDetails() {
		driver.findElement(saveBtn).click();
	}

}
