package stepDefinitions;

import io.cucumber.java.en.*;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utilities.DriverFactory;

import pageObjects.AddEmployeePage;
import pageObjects.EmployeePage;
import pojo.Employee;


public class AddEmployeeSteps {
	
	private WebDriver driver;
    private AddEmployeePage aep;
    private EmployeePage ep;
	
	public AddEmployeeSteps() {
        this.driver = DriverFactory.getDriver();
        this.aep = new AddEmployeePage(driver);
        this.ep  = new EmployeePage(driver);
    }

	@When("User click on Add option")
	public void user_click_on_add_option() {
		ep.addEmployeeButton();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Then("User can view Add new employee page {string}")
	public void user_can_view_add_new_employee_page(String pageUrl) {
		Assert.assertEquals(pageUrl, aep.getPageUrl());
	}

	@When("User enter employee details")
	public void user_enter_employee_details(Employee employee) {
		aep.enterEmployeeDetails(employee);
	}

	@When("User click on Save button")
	public void user_click_on_save_button() {
		aep.saveEmployeeDetails();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Then("User can see the success message {string}")
	public void user_can_see_the_success_message(String msg) {
		try {
			String actual = ep.validateSuccessMsg();
			Assert.assertTrue(actual.contains(msg));
		}
		catch(NoSuchElementException nse) {
			Assert.assertTrue("Employee creation failed \n"+nse.getMessage(), false);
		}
		catch(Exception e) {
			Assert.assertTrue("Employee creation failed \n"+e.getMessage(), false);
		}
	}

}
