package com.qtpselenium.core.ddf.zohotestcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qtpselenium.core.ddf.util.DataUtil;
import com.qtpselenium.core.ddf.util.Xls_Reader;
import com.qtpseleniun.core.ddf.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class LeadsTest extends BaseTest {
	public SoftAssert sa ;
    String testCaseName="CreateLeadTest";
    Xls_Reader xls;
	
	@Test(priority=1,dataProvider="getData")
	public void createLeadTest(Hashtable<String,String> data){
		 sa = new SoftAssert();

		 test = rep.startTest("Create Lead");
         test.log(LogStatus.INFO,"Starting create lead");
		
		 	if((!DataUtil.isRunnable(xls,testCaseName))||data.get("Runmode").equals("N")){
			test.log(LogStatus.SKIP,"skipping the test as  runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
          }
    	}
	
	@Test(priority=2)
	public void modifyLeadTest(){
		 sa = new SoftAssert();

		 test = rep.startTest("Modify Lead");
         test.log(LogStatus.INFO,"Starting modify lead");
		
	}
	
	@Test(priority=2,dependsOnMethods="createLeadTest")
	public void deleteLeadTest(){
		 sa = new SoftAssert();

		 test = rep.startTest("Delete Lead");
         test.log(LogStatus.INFO,"Starting delete lead");
		
	}
	
	
	@DataProvider(parallel=true)
	public Object[][] getData(){
	super.init();
	xls = new Xls_Reader(prop.getProperty("zohoxlspath"));
	return DataUtil.getTestData(xls,testCaseName);
		
	}
	

	@AfterMethod
	public void quitBrowser(){
        quit();

	}
		
		

	@AfterMethod
	public void finshReport(){

		try{sa.assertAll();}
		catch(Error ex){
			test.log(LogStatus.FAIL,ex.getMessage());
		}
		
		rep.endTest(test);
		rep.flush();
		
	}


}
