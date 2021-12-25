package com.qtpselenium.hybrid.stocksuite;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qtpselenium.hybrid.base.BaseTest;
import com.qtpselenium.hybrid.util.Constants;
import com.qtpselenium.hybrid.util.DataUtil;

public class StockTest extends BaseTest{

	

	@Test(dataProvider="getData")
	public void AddStockTest(Hashtable<String,String> data) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		test.log(Status.INFO,"Add Stock Test");
		test.log(Status.INFO,data.toString());

		if(DataUtil.isSkip(xls, testCaseName)||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
			test.log(Status.SKIP,"Test skipped");

			throw new SkipException("Skippinng the test as runmode is N");}
		
		ds.executeKeywords(testCaseName, xls, data);
		test.log(Status.PASS,"Test paseed");

	}

}

