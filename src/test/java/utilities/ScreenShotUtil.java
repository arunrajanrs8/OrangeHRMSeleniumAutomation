package utilities;

import java.util.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil {

	public static byte[] captureScreenshot(WebDriver driver) {

		try {
			String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			return Base64.getDecoder().decode(base64);

		} catch (Exception e) {
			throw new RuntimeException("Unable to capture screenshot", e);
		}
	}
}