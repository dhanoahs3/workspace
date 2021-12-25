package com.qtpselenium.hybrid.driver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.hybrid.keywords.AppKeywords;
import com.qtpselenium.hybrid.util.Constants;
import com.qtpselenium.hybrid.util.Xls_Reader;

public class DriverScript {
	
	public Properties prop;
	public Properties envProp;
	public ExtentTest test;
	AppKeywords app ;
	
	
	
	
	
	public void executeKeywords(String testCaseName,Xls_Reader xls,Hashtable<String,String> testData) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		int rows = xls.getRowCount(Constants.KEYWORDS_SHEET);
		System.out.println("The no of rows are in Keywords "+ rows );
		
		app = new AppKeywords();

		app.setEnvProp(envProp);
		app.setProp(prop);
		app.setData(testData);
		
		for(int rNum=2;rNum<rows;rNum++){
			String tcid = xls.getCellData(Constants.KEYWORDS_SHEET,Constants.TCID_COL, rNum);
			if(tcid.equals(testCaseName)){
			String keyword = xls.getCellData(Constants.KEYWORDS_SHEET,Constants.KEYWORD_COL, rNum);
			String objectKey = xls.getCellData(Constants.KEYWORDS_SHEET,Constants.OBJECT_COL, rNum);
			String dataKey = xls.getCellData(Constants.KEYWORDS_SHEET,Constants.DATA_COL, rNum);
			String proceedOnFail = xls.getCellData(Constants.KEYWORDS_SHEET,Constants.PROCEED_COL, rNum);
			app.setProceedOnFail(proceedOnFail);

			String data = testData.get(dataKey);
			
			//test.log(Status.INFO,tcid+"----"+keyword+"------"+objectKey+"-------"+data);
			app.setDataKey(dataKey);
			app.setObjectKey(objectKey);
			app.setExtentTest(test);

			
			
			// Reflections Api
			System.out.println("The keyword is "+keyword);
			Method method;
			method = app.getClass().getMethod(keyword);
			method.invoke(app);
				
			

			/*if(keyword.equals("openBrowser")){
				app.openBrowser();
		     }
			
			else if(keyword.equals("navigate")){
				app.navigate();
		     }
			
			else if(keyword.equals("click")){
				app.click();
		     }
			
			else if(keyword.equals("type")){
				app.type();
		     }
			
			else if(keyword.equals("validateLogin")){
				app.validateLogin();
		     }
			*/
			  }
		}	
		app.assertAll();
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public Properties getEnvProp() {
		return envProp;
	}

	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
	}
	
	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}
	
	public void quit(){
		if(app!=null){
			app.quit();
		}
	}
	
	

}