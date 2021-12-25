package com.qtpselenium.hybrid.portfoliosuite;

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

public class PortfolioTest extends BaseTest {
	
	
	
	@Test(dataProvider="getData")
	public void createPortfolioTest(Hashtable<String,String> data) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		test.log(Status.INFO,"createPortfolio Test");
		test.log(Status.INFO,data.toString());

		if(DataUtil.isSkip(xls, testCaseName)||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
			test.log(Status.SKIP,"Test skipped");

			throw new SkipException("Skippinng the test as runmode is N");}
		
		ds.executeKeywords(testCaseName, xls, data);
		test.log(Status.PASS,"Test paseed");

	}
	
	@Test(dataProvider="getData")
	
	public void deletePortfolioTest(Hashtable<String,String> data) throws Exception{
		test.log(Status.INFO, "Starting "+ testCaseName);
		
	


		if(DataUtil.isSkip(xls, testCaseName) ||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
			test.log(Status.SKIP, "Runmode is set to NO");
			throw new SkipException("Runmode is set to NO");
		}		
	    ds.executeKeywords(testCaseName, xls, data);

	}
}



