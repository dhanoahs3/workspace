package zoho.context;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import zoho.managers.PageObjectManager;
import zoho.reports.ExtentManager;

public class TestContext {
	
	ExtentReports report;
	ExtentTest test;
	private PageObjectManager pageObjectManager;
	
   public TestContext(){
	   System.out.println("Constructor TestContext");
	   report = ExtentManager.getReports();
	   test = report.createTest("New Test");
	   this.pageObjectManager = new PageObjectManager();
   }
   
   
   public PageObjectManager getPageObjectManager(){
	   return pageObjectManager;
   }
   
   public void createScenario(String scenarioName){
    	test = report.createTest(scenarioName);
    }
    
    public void endScenario(){
	  report.flush();
    }
    
    public void log(String message){
    	test.log(Status.INFO,message);
		System.out.println(message);
   }
    
   
   
   
}




