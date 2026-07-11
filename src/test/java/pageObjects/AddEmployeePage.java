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
	By empIDTxt = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
	By loginDtlsToggle = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span");
	By userNameTxt = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input");
	By statusRadioBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/label");
	By passwordTxt = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input");
	By confirmPwdTxt = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input");
	By saveBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
	
	
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
