package zoho.teststeps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import zoho.context.TestContext;

public class Leads {
	
	TestContext testContext;
	 
	public Leads(TestContext testContext){
		System.out.println("<---------------------- In the leads constructor -------------------->");
		this.testContext = testContext;
    }
	
	@Before
	public void before(Scenario scenario){
		testContext.createScenario(scenario.getName());
		testContext.log("Starting of scenario " +scenario.getName());
	}
	
	@After
	public void after(Scenario scenario){
		testContext.endScenario();
		testContext.log("Ending of scenario " +scenario.getName());
	}
	

	@When("I go to {string} page")
	 public void verifyLeadsPage(String pageName){
		testContext.log("I go to "+pageName+ " page");
	}
	
	@And("enter and submit lead details")
		public void submitDetails(){
		testContext.log("enter and submit lead details");
	}
	
	@And("I verify leads details")
		public void verifyDetails(){
		testContext.log("I verify leads details");
	}
	
	@And("Lead should be present inside the grid")
		public void verifyLeadCreation(){
		testContext.log("Lead should be present inside the grid");
	}
	
}
