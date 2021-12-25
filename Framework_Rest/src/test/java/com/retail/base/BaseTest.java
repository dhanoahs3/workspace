package com.retail.base;

import static io.restassured.RestAssured.given;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.retail.util.DataUtil;
import com.retail.util.ExtentManager;
import com.retail.util.ReadExcel;
import com.retail.util.Xls_Reader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {
	
	public static String sessionId;
//	Xls_Reader xls;
	ReadExcel xls;
	public static SoftAssert softAssert  = new SoftAssert();
	public Properties testProp;
	
    public ExtentReports rep;
	public static String reportFolder;
	public ExtentTest test;
	public int iteration;
	
	
	public StringWriter requestWriter; 
	public PrintStream requestCapture;
	
	
	@BeforeTest
	public void init(){
		
		try{
			testProp = new Properties();
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//project.properties");
		    testProp.load(fs);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	//	xls =  new Xls_Reader(testProp.getProperty("xlspath"));
		xls =  new ReadExcel(testProp.getProperty("xlspath"));

		RestAssured.baseURI = testProp.getProperty("baseurl");
		String testname = this.getClass().getSimpleName().toLowerCase();
		RestAssured.basePath = testProp.getProperty(testname);
	}
	

	@BeforeMethod
	public void before()
	{
		iteration++;
		rep = ExtentManager.getInstance(testProp.getProperty("reportPath"));
		test = rep.createTest("Login");
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter),true);
	}
	
	@AfterMethod
	public void after()
	{
      rep.flush();
	}
	
	
	@DataProvider
   public Object[][] getData(){
	  return DataUtil.getData1(xls,this.getClass().getSimpleName());
	     }
	
	
	protected static void reportFailure(String errorMsg, boolean stop) {
		
		softAssert.fail(errorMsg);
		
		if(stop){
			softAssert.assertAll();
		}
			
	  }
	
	public void addRequestToLink(String message,String fileName,String Content)
	
	{
		
		
		try {
			new File(reportFolder+"\\log\\"+fileName+".html").createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			FileWriter fw = new FileWriter(reportFolder+"\\log\\"+fileName+".html");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Content);
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
