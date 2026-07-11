package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {
    	
    	switch (browser) {
        case "chrome":
        	driver.set(new ChromeDriver());
            getDriver().manage().window().maximize();
            break;
        case "edge":
        	driver.set(new EdgeDriver());
            getDriver().manage().window().maximize();
            break;
        case "firefox":
        	driver.set(new FirefoxDriver());
            getDriver().manage().window().maximize();
            break;
        default:
        	driver.set(new ChromeDriver());
            getDriver().manage().window().maximize();
            break;
    	}

    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
