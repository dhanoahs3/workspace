package com.qtpselenium.hybrid.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qtpselenium.hybrid.driver.DriverScript;
import com.qtpselenium.hybrid.reports.ExtentManager;
import com.qtpselenium.hybrid.util.DataUtil;
import com.qtpselenium.hybrid.util.Xls_Reader;

public class BaseTest {
	
	
	public Properties prop;
	public Properties envProp;
    protected Xls_Reader xls;
    public String testCaseName;
    public DriverScript ds;
    public  ExtentReports rep;
    public ExtentTest test;

	
	
	@BeforeTest
	public void init(){
	
    prop= new Properties();
     envProp = new Properties();
     testCaseName = this.getClass().getSimpleName();
     String[] packageNameArray = this.getClass().getPackage().getName().split("\\.");
     String packageName = packageNameArray[packageNameArray.length-1];
    
	try {
     FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//env.properties");
	 prop.load(fs);
				
	String env = prop.getProperty("env");
				
	fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//"+env+".properties");
    envProp.load(fs);


		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	        xls = new Xls_Reader(envProp.getProperty(packageName+"_xls"));
			ds = new DriverScript();
			ds.setEnvProp(envProp);
			ds.setProp(prop);
			
			}
	
	
	@DataProvider
	public Object[][] getData(){
	return DataUtil.getTestData(xls,testCaseName);
		
	}
	
	
	@BeforeMethod
	public void initTest(){
	 rep = ExtentManager.getInstance(prop.getProperty("reportPath"));
	 test = rep.createTest(testCaseName);
	 ds.setExtentTest(test);
	}
	
	@AfterMethod
	public void quit(){
		if(ds!=null){
			ds.quit();
		}
		if(rep!=null){
			rep.flush();

		}
	}

}
