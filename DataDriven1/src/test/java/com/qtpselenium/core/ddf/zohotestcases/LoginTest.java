package com.qtpselenium.core.ddf.zohotestcases;

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

public class LoginTest extends BaseTest {
	
	public SoftAssert sa ;
    String testCaseName="LoginTest";
    Xls_Reader xls;
	
	@Test(priority=1,dataProvider="getData")
	public void testA1(Hashtable<String,String> data){
		
		sa = new SoftAssert();
	    System.out.println(data.get("Runmode")+"--------"+data.get("Runmode")
	    +"-----------"+data.get("Username")+"---"+data.get("Password"));

	     test = rep.startTest("LoginTest");
		 test.log(LogStatus.INFO,"starting LoginTest");
		 
		if((!DataUtil.isRunnable(xls,testCaseName))||data.get("Runmode").equals("N")){
			System.out.println(DataUtil.isRunnable(xls, testCaseName));
			test.log(LogStatus.SKIP,"skipping the test as eithe runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
          }
     
    	openBrowser("Chrome");
        navigate("zohoappurl");
        login(data.get("Username"),"133");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
	
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
	xls = new Xls_Reader(prop.getProperty("zohoxlspath"));
	return DataUtil.getTestData(xls,testCaseName);
		
	}
		
	
	@AfterMethod
	public void quitBrowser(){
        quit();

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
