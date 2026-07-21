package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class WaitHelper {

	public WebDriver driver;

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElements(By elements, Duration timeinSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeinSec);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elements));
	}

	public WebElement waitForElementandReturn(By path, Duration timeinSec) {
		WebElement element;
		WebDriverWait wait = new WebDriverWait(driver, timeinSec);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(path));
		return element;
	}

	public void waitForElementbyPath(By path, Duration timeinSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeinSec);
		wait.until(ExpectedConditions.visibilityOfElementLocated(path));
	}

	public void waitForElement(WebElement webElement, Duration timeinSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeinSec);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitForNumberOfRowsMorethanOne(By path, Duration timeinSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeinSec);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(path, 0));
	}

	public static void waitForPageToStabilize(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(webDriver -> {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			return js.executeAsyncScript(
					"const callback = arguments[arguments.length - 1];" +
							"requestAnimationFrame(() => {" + "  requestAnimationFrame(() => {"
						+ "      callback(true);" + "  });" + "});").equals(true);
		});
	}

}
