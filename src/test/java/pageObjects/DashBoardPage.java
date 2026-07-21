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
	
	By pimMenu = By.xpath("//span[text()='PIM']");
	
	@FindBy(xpath="//span[@class='oxd-userdropdown-tab']/i")
	WebElement clickProfile;
	
	@FindBy(xpath="//ul[@role='menu']/li[4]")
	WebElement logOutBtn;
	
	
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
