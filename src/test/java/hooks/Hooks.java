package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.DriverFactory;
import utilities.ScreenShotUtil;
import utilities.WaitHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

public class Hooks {

	private static Properties configProp;
	private static FileInputStream configPropFile;
	private static final Logger logger = Logger.getLogger(Hooks.class.getName());

	@Before
	public void setUp() {
		PropertyConfigurator.configure("src/test/resources/log4j.properties");
		String browser = getPropertyValue("browser");
		DriverFactory.initDriver(browser);
	}

	public static String getPropertyValue(String key) {
		String value = "";
		try {
			configProp = new Properties();
			configPropFile = new FileInputStream("src/test/resources/config.properties");
			configProp.load(configPropFile);
			value = configProp.getProperty(key);
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		return value;
	}

	@After
	public void addScreenshotOnFailureAndCloseDriver(Scenario scenario) {
		WebDriver driver;
		try {
			if (scenario.isFailed()) {
				Thread.sleep(500);
				driver = DriverFactory.getDriver();
				WaitHelper.waitForPageToStabilize(driver);
				byte[] screenshot = ScreenShotUtil.captureScreenshot(driver);
				scenario.attach(screenshot, "image/png", scenario.getName().replaceAll(" ", "_"));
			}
		} catch (Exception e) {
			logger.info("Screenshot capture failed: " + e.getMessage());
		} finally {
			DriverFactory.quitDriver();
		}
	}

}
