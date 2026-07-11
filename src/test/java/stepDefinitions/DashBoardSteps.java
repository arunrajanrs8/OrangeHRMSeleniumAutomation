package stepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DashBoardPage;
import utilities.DriverFactory;

public class DashBoardSteps {
	
	private WebDriver driver;
    private DashBoardPage dp;
	
	public DashBoardSteps() {
        this.driver = DriverFactory.getDriver();
        this.dp  = new DashBoardPage(driver);
    }
	
	@Then("User can view the dashboard {string}")
	public void user_can_view_the_dashboard(String pageUrl) {
		String title = dp.viewDashboard();
	    Assert.assertEquals("User not in dashboard page \n", pageUrl, title);
	}

	@When("User click on PIM menu")
	public void user_click_on_pim_menu() {
		dp.clickOnPIMMenu();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

}
