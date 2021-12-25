package zoho.teststeps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import zoho.context.TestContext;

public class TopMenu {
	
  public TestContext testContext;

  public TopMenu(TestContext testContext){
		System.out.println("<-------------------- In the topmenu constructor ----------------------->");
		this.testContext = testContext;
    }
	
  @And("I click on {string} in top menu")
  	 public void verifyLeadsPage(String menuItem){
	  testContext.log("I click on "+menuItem +" in top menu");
	}
	
	

}
