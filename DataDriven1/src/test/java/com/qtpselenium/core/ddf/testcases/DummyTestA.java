package com.qtpselenium.core.ddf.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qtpselenium.core.ddf.util.DataUtil;
import com.qtpselenium.core.ddf.util.ExtentManager;
import com.qtpselenium.core.ddf.util.Xls_Reader;
import com.qtpseleniun.core.ddf.base.BaseTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DummyTestA extends BaseTest {
	
	public SoftAssert sa ;
    String testCaseName="TestA1";
    Xls_Reader xls;
	
	@Test(priority=1,dataProvider="getData")
	public void testA1(Hashtable<String,String> data){
		 sa = new SoftAssert();
	       System.out.println(data.get("Runmode")+"--------"+data.get("Col1")+"---"+data.get("Col2"));

	     test = rep.startTest("testA1");
		 test.log(LogStatus.INFO,"starting test A1");
		 
		if((!DataUtil.isRunnable(xls,testCaseName))||data.get("Runmode").equals("N")){
			test.log(LogStatus.INFO,"skipping the test as eithe runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
          }
     
    	openBrowser("Chrome");
       navigate("appurl");
      
       sa.assertTrue(true,"error 1");
       sa.assertTrue(false,"error 2");
       sa.assertTrue(false,"error 3");
    //   sa.assertTrue(isElementPresent("email_id"),"email id not found");

       
     /*  if(!verifyText("textxpath","homepageText")){
    	   reportFailure("text did not match");
       }
       
       
 
       if(!isElementPresent("email_id")){
    	   reportFailure("Email not found");
       }
       captureScreenshot();*/
      // quit();
	
	}
	
	/*@Test(priority=2)
	public void testA2(){
		System.out.println("Test A2");
	}

	
	@Test(priority=3)
	public void testA3(){
		System.out.println("Test A3");
	}*/
	
	
	
	@DataProvider
	public Object[][] getData(){
	super.init();
	xls = new Xls_Reader(prop.getProperty("xlspath"));
	return DataUtil.getTestData(xls,testCaseName);
		
	}
		
		

	@AfterTest
	public void finshReport(){

		try{sa.assertAll();}
		catch(Error ex){
			test.log(LogStatus.FAIL,ex.getMessage());
		}
		
		rep.endTest(test);
		rep.flush();
		
	}

}
