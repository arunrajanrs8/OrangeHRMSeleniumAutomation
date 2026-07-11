package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Features/",
		glue = {"stepDefinitions", "hooks"},
		dryRun = false,
		monochrome = true,
		plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
		tags = "@sanity or @regression"
		)
public class TestRunner {

}
