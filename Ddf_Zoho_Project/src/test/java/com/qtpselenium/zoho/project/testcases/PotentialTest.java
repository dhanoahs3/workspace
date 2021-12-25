package com.qtpselenium.zoho.project.testcases;
import java.util.Hashtable;

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

public class PotentialTest extends BaseTest {

	
	@Test(priority=1,dataProvider="getData")
	public void createPotentialTest(Hashtable<String ,String> t) throws InterruptedException{
		
		
		String testCaseName = "CreatePotentialTest";
		Xls_Reader  xls = new Xls_Reader(p.getProperty("xlspath"));

				SoftAssert softAssert = new SoftAssert();
			    test = rep.startTest("CreatePotentialTest");
				test.log(LogStatus.INFO,"Create Potential Test starting");
				
			
				
		
	}
	
	@Test(priority=2,dataProvider="getData")
	public void deletePotentialAccountTest(Hashtable<String,String> t) throws InterruptedException{
		
		String testCaseName = "DeletePotentialAccountTest";
		Xls_Reader  xls = new Xls_Reader(p.getProperty("xlspath"));

		SoftAssert softAssert = new SoftAssert();
	    test = rep.startTest("DeletePotentialTest");
		test.log(LogStatus.INFO,"Delete Potential Test starting");
		
		
    

	}
	@DataProvider
	public Object[][] getData(){
		 super.init();
		 Xls_Reader xls = new Xls_Reader(p.getProperty("xlspath"));

	    return DataUtil.getTestData(xls,"CreatePotentialTest");



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
