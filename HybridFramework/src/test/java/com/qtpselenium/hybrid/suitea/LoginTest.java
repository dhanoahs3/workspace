package com.qtpselenium.hybrid.suitea;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qtpselenium.hybrid.base.BaseTest;
import com.qtpselenium.hybrid.driver.DriverScript;
import com.qtpselenium.hybrid.util.Constants;
import com.qtpselenium.hybrid.util.DataUtil;
import com.qtpselenium.hybrid.util.Xls_Reader;

public class LoginTest extends BaseTest {
	
	
	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String,String> data) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

		test.log(Status.INFO,"Starting LoginTest");
		test.log(Status.INFO,data.toString());
		if(DataUtil.isSkip(xls, testCaseName)||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
			test.log(Status.SKIP,"LoginTest skipped as Rumode is N");

			throw new SkipException("Skippinng the test as runmode is N");}
		
		ds.executeKeywords(testCaseName, xls, data);
	}
	
}
