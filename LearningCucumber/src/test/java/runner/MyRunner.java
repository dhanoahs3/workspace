package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/resources/"},
		//features= {"@rerun/failed_scenarios.txt"},
        glue = {"search","select","buy.computer","cnn"},
		plugin= {"html:target/site/cucumber-reports.html","rerun:rerun/failed_scenarios.txt"},
	   // tags = "@BuyCar or @BuyComputer"
      //  tags = "@BuyCar or @BuyComputer and not @BuyDesktop" //dont run desktop tests in computer
	  //  tags = "@CNN",
		 tags = "@BuyCar",
	     monochrome = false

		
)

public class MyRunner  extends AbstractTestNGCucumberTests {
	
	
		
}