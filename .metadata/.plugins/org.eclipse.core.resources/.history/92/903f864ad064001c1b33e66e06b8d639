package zoho.runner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/resources/"},
		//features= {"@rerun/failed_scenarios.txt"},
        glue = {"zoho.teststeps"},
	    tags = "@CreateLead",
	    monochrome = false,
	    dryRun = false
)

public class MyRunner  extends AbstractTestNGCucumberTests {
}