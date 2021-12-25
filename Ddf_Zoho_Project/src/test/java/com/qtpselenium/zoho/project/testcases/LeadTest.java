package com.qtpselenium.zoho.project.testcases;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qtpselenium.zoho.project.base.BaseTest;
import com.qtpselenium.zoho.project.util.DataUtil;
import com.qtpselenium.zoho.project.util.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

public class LeadTest extends BaseTest {
    @Test(dataProvider="getData")
	  public void createLeadTest(Hashtable<String,String> t)  throws InterruptedException{
    	
    	String testCaseName = "LoginTest";
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

		    boolean expectedResult = false;
		    if(t.get("ExpectedResult").equals("Y")){
		   	 expectedResult = true;
		   	 
		    }
		    if(expectedResult!=actualResult){
		   	// reportFailure("Login test failed ");
		    }
		    
		    
			test.log(LogStatus.INFO,"Going to crm link");

		    click("crmlink_xpath");
			test.log(LogStatus.INFO,"Going to leads link");

		    click("leads_xpath");
			test.log(LogStatus.INFO,"Going to create lead link");
Thread.sleep(4000);
		    click("createlead_xpath");
		    
			test.log(LogStatus.INFO,"Creating new lead");

			 type("company_xpath" ,"Abc26");
			    type("lastname_xpath" ,"Coyboy26");
			    click("savebutton_xpath");
			    Thread.sleep(5000);
			    click("leads_xpath");
			    
			    int rNum = findLeadRowNum("Coyboy26");
			    takeScreenShot();

    
    
    

    
   
    
	
	
	
	}
	
	@Test(priority=2,dataProvider="getDataCreateLead")
	public void convertLeadTest(Hashtable<String,String> t) throws InterruptedException{
		
		
		String testCaseName="ConvertLeadTest";
		Xls_Reader  xls = new Xls_Reader(p.getProperty("xlspath"));

		SoftAssert softAssert = new SoftAssert();
	    test = rep.startTest("ConvertLeadTest");
		test.log(LogStatus.INFO,"Convert lead Test starting");
	//	if(!DataUtil.IsRunnable(xls,testCaseName)||t.get("Runmode").trim().equals("N"))
		{
		//	test.log(LogStatus.SKIP,"We are skipping this test with Runmode" +t.get("Runmode"));
			//throw new SkipException("Skipping this test");
}		
		String browser_type = t.get("Browser").trim();
		openBrowser(browser_type);
		navigate("url");
 boolean actualResult= doLogin("dhanoa3@gmail.com","Zohotest1!");

    
	test.log(LogStatus.INFO,"Going to crm link");

    click("crmlink_xpath");
	test.log(LogStatus.INFO,"Going to leads link");

    click("leads_xpath");
		
	clickOnLead("Coyboy26");
	click("convertlead_xpath");
	click("convertleadsave_xpath");
	click("gotoleads_xpath");
takeScreenShot();
		
		
		
		
		
	}
	
	@Test(priority=3,dataProvider="getDataDeleteLead")
	public void deleteLeadTest(Hashtable<String,String> t) throws InterruptedException{
		
	
	}


@DataProvider
public Object[][] getData(){
	 super.init();
	 Xls_Reader xls = new Xls_Reader(p.getProperty("xlspath"));

    return DataUtil.getTestData(xls,"LoginTest");

	 
}

@DataProvider
public Object[][] getDataCreateLead(){
	 super.init();
	 Xls_Reader xls = new Xls_Reader(p.getProperty("xlspath"));

    return DataUtil.getTestData(xls,"CreateLeadTest");

	 
}


@DataProvider
public Object[][] getDataDeleteLead(){
	 super.init();
	 Xls_Reader xls = new Xls_Reader(p.getProperty("xlspath"));

    return DataUtil.getTestData(xls,"DeleteLeadAccountTest");

	 
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
quit();	
}




}



