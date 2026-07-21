package stepDefinitions;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import hooks.Hooks;
import io.cucumber.java.en.*;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.junit.Assert;

import pageObjects.DashBoardPage;
import pageObjects.LoginPage;
import utilities.DriverFactory;

public class LoginSteps {
	
	private WebDriver driver;
    private LoginPage lp;
    private DashBoardPage dp;
    private static final Logger logger = Logger.getLogger(LoginSteps.class.getName());
	
	public LoginSteps() {
        this.driver = DriverFactory.getDriver();
        this.dp  = new DashBoardPage(driver);
        this.lp  = new LoginPage(driver);
    }
	
	@Given("User launch Chrome browser")
	public void user_launch_chrome_browser() {
		logger.info("Launching browser");
		lp = new LoginPage(driver);
	}

	@When("User opens url")
	public void user_opens_url() throws InterruptedException {
		logger.info("Opening Orange HRM Login page");
		String url = Hooks.getPropertyValue("weburl");
	    lp.openWebUrl(url);
	}

	@When("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("Enter email and password");
	    lp.setUserName(email);
	    lp.setPassword(password);
	}

	@When("User clicks on Login button")
	public void user_clicks_on_login_button() throws InterruptedException {
		logger.info("CLicking login button");
		lp.login();
	}

	@Then("User should be in dashboard {string}")
	public void user_should_be_in_dashboard(String pageTitle) {
	    try {
	    	String title = dp.viewDashboard();
		    Assert.assertEquals("Login Passed for the user \n", pageTitle, title);
		}
		catch(NoSuchElementException nse) {
			Assert.assertTrue("Login Failed for the user \n"+nse.getMessage(), false);
		}
		catch(Exception e) {
			Assert.assertTrue("Login Failed for the user \n"+e.getMessage(), false);
		}
	    
	}
	
	@Then("User should not be in dashboard {string}")
	public void user_should_not_be_in_dashboard(String pageTitle) {
		String title = "";
	    try {
	    	title = dp.viewDashboard();
		    Assert.fail("Invalid user was able to access dashboard and dashboard url is: "+title);
		}
		catch(Exception e) {
			Assert.assertNotEquals("Login Failed for the invalid user \n", pageTitle, title);
		}  
	}

	@When("User click on logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		dp.logOutUser();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Then("User should be in login {string}")
	public void user_should_be_in_login(String pageTitle) {
	    String title = lp.viewLoginPage();
	    Assert.assertEquals("Logout Passed for the user \n", pageTitle, title);
	}

	@Then("User close the browser")
	public void user_close_the_browser() {
	    driver.quit();
	}

}
