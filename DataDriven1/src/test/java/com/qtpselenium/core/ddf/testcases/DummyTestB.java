package com.qtpselenium.core.ddf.testcases;

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

public class DummyTestB extends BaseTest {
	
	public SoftAssert sa ;
    String testCaseName="TestB";
    Xls_Reader xls;
	
	@Test(priority=1,dataProvider="getData")
	public void testB1(Hashtable<String,String> data){
		 sa = new SoftAssert();

		 test = rep.startTest("testB1");

		System.out.println("Test B1");
		test.log(LogStatus.INFO,"starting test B1");
		
		 
		if((!DataUtil.isRunnable(xls,testCaseName))||data.get("Runmode").equals("N")){
			test.log(LogStatus.INFO,"skipping the test as eithe runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
          }
     
		test.log(LogStatus.FAIL,"Screenshot -->"+test.addScreenCapture("C:\\report\\jmeter\\test.jpg"));
		
	}
	
	
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
