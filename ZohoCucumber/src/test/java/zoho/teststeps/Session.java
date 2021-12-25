package zoho.teststeps;

import io.cucumber.java.en.Given;
import zoho.context.TestContext;
import zoho.pages.Homepage;
import zoho.pages.LoginPage;

public class Session {
	
  public TestContext testContext;
  public Homepage homePage;
  public LoginPage loginPage;
	 
  public Session(TestContext testContext){
	  System.out.println("<------------------ In the sessions constructor ------------------------>");
	  this.testContext = testContext;
	  this.homePage = this.testContext.getPageObjectManager().getHomePage();
	  
  }
	
  @Given("I am logged in zoho.com")
	public void zohoLogin(){
	  testContext.log("I am logged in zoho.com");
	  
  }
	
	

}
