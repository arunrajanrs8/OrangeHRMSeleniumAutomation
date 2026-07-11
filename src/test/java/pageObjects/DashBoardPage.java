package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class DashBoardPage {
	
	public WebDriver driver;
	WaitHelper waitHelper;
	
	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}
	
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/p")
	WebElement clickProfile;
	
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/ul/li[4]/a")
	WebElement logOutBtn;
	
	By pimMenu = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span");
	

	public void logOutUser() {
		clickProfile.click();
		logOutBtn.click();	
	}
	
	public void clickOnPIMMenu() {
		driver.findElement(pimMenu).click();
	}
	
	public String viewDashboard() {
		waitHelper.waitForElementbyPath(pimMenu, Duration.ofSeconds(10));
		String dashboardUrl = driver.getCurrentUrl();
		return dashboardUrl;
	}

}
