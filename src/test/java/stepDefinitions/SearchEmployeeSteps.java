package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import pageObjects.EmployeePage;
import utilities.DriverFactory;

public class SearchEmployeeSteps {
	
	private WebDriver driver;
    private EmployeePage ep;
	
	public SearchEmployeeSteps() {
        this.driver = DriverFactory.getDriver();
        this.ep  = new EmployeePage(driver);
    }
	
	
	@When("User enter EmployeeId {string}")
	public void user_enter_employee_id(String empId) {
	    ep.enterEmpId(empId);
	}
	@When("User click on search button")
	public void user_click_on_search_button() {
		ep.searchEmployeeButton();
	}
	@Then("User see the employee details of {string}")
	public void user_see_the_employee_details_of(String empId) {
	    boolean flag = ep.viewEmployeeDetails(empId);
	    Assert.assertTrue("Employee NOT Found", flag);
	}

	@Then("User do not see any employee records with message {string}")
	public void user_do_not_see_any_employee_records_with_message(String message) {
		try {
			String actual = ep.validateEmployeeDetails();
			Assert.assertTrue(actual.contains(message));
		}
		catch(NoSuchElementException nse) {
			Assert.assertTrue("Invalid Employee search failed \n"+nse.getMessage(), false);
		}
		catch(Exception e) {
			Assert.assertTrue("Invalid Employee search failed \n"+e.getMessage(), false);
		}
	}
}
