package com.qtpselenium.hybrid.suitea;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.hybrid.base.BaseTest;
import com.qtpselenium.hybrid.reports.ExtentManager;
import com.qtpselenium.hybrid.util.Constants;
import com.qtpselenium.hybrid.util.DataUtil;

public class TestB extends BaseTest {
	
	
	
	@Test(dataProvider="getData")
	public void testBTest(Hashtable<String,String> data) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		test.log(Status.INFO,"Starting test B in Suite A");
		test.log(Status.INFO,data.toString());

       System.out.println("In Suite A Test B");
		if(DataUtil.isSkip(xls, testCaseName)||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
			test.log(Status.SKIP,"Test skipped");

			throw new SkipException("Skippinng the test as runmode is N");}
		
		ds.executeKeywords(testCaseName, xls, data);
		test.log(Status.PASS,"Test paseed");

	}

}
