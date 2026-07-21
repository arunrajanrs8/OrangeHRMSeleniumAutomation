package pageObjects;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;


public class EmployeePage {
	
	private static final Logger logger = Logger.getLogger(EmployeePage.class.getName());
	public WebDriver driver;
	WaitHelper waitHelper;
	
	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}

	By addEmpBtn = By.xpath("//button[text()= ' Add ']");
	By successToast = By.cssSelector(".oxd-toast--success");
	By successToastMsg = By.xpath("//p[contains(@class,'oxd-toast-content-text') and contains(@class,'oxd-text--toast-title')]\"");
	By infoToast = By.xpath("//p[contains(@class,'oxd-text--toast-title')]");
	By infoToastMsg = By.xpath("//p[contains(@class,'oxd-text--toast-message')]");
	
	By empIdTxt = By.xpath("//label[text()='Employee Id']/following::input[1]");
	By searchEmpBtn = By.xpath("//button[text()=' Search ']");
	
	By empTable = By.xpath("//div[@class='oxd-table-body']");
	By empTableRows = By.xpath("//div[@class='oxd-table-body']//div[@role='row']");
	By empTableColumns = By.xpath(".//div[@role='cell']");
	
	
	public void addEmployeeButton() {
		driver.findElement(addEmpBtn).click();
	}
	
	public void enterEmpId(String empId) {
		driver.findElement(empIdTxt).clear();
		driver.findElement(empIdTxt).sendKeys(empId);;
	}
	
	public void searchEmployeeButton() {
		driver.findElement(searchEmpBtn).click();
	}
	
	public String validateSuccessMsg() {
		WebElement toastEl = waitHelper.waitForElementandReturn(successToast, Duration.ofSeconds(10));
		String toastMsg = toastEl.getText();
		return toastMsg;
	}
	
	public boolean viewEmployeeDetails(String empId) {
		
		boolean empPresent = false;
		waitHelper.waitForElementbyPath(empTable, Duration.ofSeconds(10));
		waitHelper.waitForNumberOfRowsMorethanOne(empTableRows, Duration.ofSeconds(10));
	    int retry = 0;
	    while (retry < 5) {
	        try {
	            List<WebElement> rows = driver.findElements(empTableRows);
	            for (WebElement row : rows) {
	                List<WebElement> cols = row.findElements(empTableColumns);
	                String empIdFromUI = cols.get(1).getText().trim();
	                logger.info("Employee ID From UI: "+empIdFromUI);
	                if (empIdFromUI.equals(empId)) {
	                	empPresent = true;
	                }
	            }
	            return empPresent;
	        } catch (StaleElementReferenceException e) {
	            retry++;
	            logger.info("Retrying due to stale DOM. Attempt: "+retry);
	            waitHelper.waitForElementbyPath(empTable, Duration.ofSeconds(10));
	        }
	    }
	    return empPresent;
	}
	
	public String validateEmployeeDetails() {
		waitHelper.waitForElementbyPath(infoToastMsg, Duration.ofSeconds(10));
		String toastMsg = driver.findElement(infoToastMsg).getText();
		return toastMsg;
	}

}
