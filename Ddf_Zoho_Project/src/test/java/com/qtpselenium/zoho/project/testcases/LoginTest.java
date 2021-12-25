package com.qtpselenium.zoho.project.testcases;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;




import com.qtpselenium.zoho.project.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import com.qtpselenium.zoho.project.util.DataUtil;
import com.qtpselenium.zoho.project.util.Xls_Reader;

public class LoginTest extends BaseTest{
     @Test(dataProvider="getData")
	  public void testB(Hashtable<String,String> t)  throws InterruptedException{
    		String testCaseName="LoginTest";

		Xls_Reader  xls = new Xls_Reader(p.getProperty("xlspath"));

		SoftAssert softAssert = new SoftAssert();
	    test = rep.startTest("LoginTest");
		test.log(LogStatus.INFO,"Login Test starting");
		if(!DataUtil.IsRunnable(xls,testCaseName)||t.get("Runmode").trim().equals("N"))
		{
		test.log(LogStatus.SKIP,"We are skipping this test with Runmode" +t.get("Runmode"));
		throw new SkipException("Skipping this test");
}		
		String browser_type = t.get("Browser").trim();
		openBrowser(browser_type);
		navigate("url");
 boolean actualResult= doLogin("dhanoa3@gmail.com","Zohotest1!");

  //   boolean expectedResult = false;
    // if(t.get("ExpectedResult").equals("Y")){
    	// expectedResult = true;
    	 
   //  }
   //  if(expectedResult!=actualResult){
    	// reportFailure("Login test failed ");
     //}
     
     
     
    /* click("crmlink_xpath");
     Thread.sleep(5000);
     click("leads_xpath");
     
     Thread.sleep(5000);

     click("createlead_xpath");
     Thread.sleep(5000);

     type("company_xpath" ,"Abc1");
     type("lastname_xpath" ,"Coyboy");
     click("savebutton_xpath");
     
    
     */
     
     
    }
     
     
     
     
     @DataProvider
     public Object[][] getData(){
		 super.init();
		 Xls_Reader xls = new Xls_Reader(p.getProperty("xlspath"));
		
        return DataUtil.getTestData(xls,"LoginTest");

		 
	 }
	
	
	
	
	
	@BeforeMethod
	public void start(){
		
	}
	
	
	@AfterMethod
	public void afterTest(){
		try{
		softAssert.assertAll();
		}
		catch(Error e){
			test.log(LogStatus.FAIL,e.getMessage());
		}
		
		rep.endTest(test);
		rep.flush();
	//quit();	
	}
	
	

	
}
