package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class LoginPage {
	
	public WebDriver driver;
	WaitHelper waitHelper;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}
	
	@FindBy(name="username")
	WebElement txtUsrName;
	
	@FindBy(name="password")
	WebElement txtPassword;
	
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
	WebElement loginBtn;
	
	
	public void openWebUrl(String url) {
		driver.get(url);
		waitHelper.waitForElement(loginBtn, Duration.ofSeconds(10));
	}
	public String viewLoginPage() {
		waitHelper.waitForElement(loginBtn, Duration.ofSeconds(10));
		String title = driver.getCurrentUrl();
		return title;
	}
	
	public void setUserName(String userName) {
		txtUsrName.clear();
		txtUsrName.sendKeys(userName);
		
	}
	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
		
	}
	public void login() {
		loginBtn.click();	
	}

}
