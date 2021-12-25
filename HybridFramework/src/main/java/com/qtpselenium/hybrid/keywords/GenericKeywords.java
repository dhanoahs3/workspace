package com.qtpselenium.hybrid.keywords;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.hybrid.reports.ExtentManager;

public class GenericKeywords {
	
	public Properties prop;
	public Properties envProp;
	public String dataKey;
	public String objectKey;
	public String proceedOnFail;
	public Hashtable<String,String> data;
	public WebDriver driver;
	public ExtentTest test;
	public SoftAssert sa = new SoftAssert();
	
        /******** Setters for above variables ***********/
	
	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;

   }

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;

	}

	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;

	}

	public void setData(Hashtable<String, String> data) {
		this.data = data;

	}
	
	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}
	
	
	
	
    public void setProceedOnFail(String proceedOnFail) {
		this.proceedOnFail = proceedOnFail;
	}

	/*****************************************************************/

	
public void openBrowser(){
	  String browserType = data.get(dataKey);
	  test.log(Status.INFO,"Opening browser "+data.get(dataKey));
	  if(browserType.equals("Mozilla")){
			driver=new FirefoxDriver();
		 System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");	
	  }
		else if(browserType.equals("Chrome")){
			System.out.println("About to start chrome ");
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
			driver=new ChromeDriver();
		}
	  
	  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	  driver.manage().window().maximize();
		
	}
	
   public void navigate(){
	   test.log(Status.INFO,"Navigating to website "+ this.envProp.getProperty(objectKey));
	   driver.get(envProp.getProperty(objectKey));
}

   public void type(){
	   
	   test.log(Status.INFO,"Typing something on webpage "+prop.getProperty(dataKey));
	  //getElement(objectKey).sendKeys(dataKey);

   }

   public void click(){
	   test.log(Status.INFO,"clicking on a webelement "+prop.getProperty(objectKey)+ " Data. "+data.get(dataKey));
		//  getElement(objectKey).click();

   }
   
   public WebElement getElement(String locator){
	   WebElement e = null;
	   
	   try{
		   
		   if(locator.endsWith("_id")){
			   e  =	driver.findElement(By.id(prop.getProperty(locator)));
			   }
		
			else if(locator.endsWith("_xpath")){
				e=	driver.findElement(By.xpath(prop.getProperty(locator)));
		     	}
			
			else{
				e= 	driver.findElement(By.cssSelector(prop.getProperty(locator)));
			    }
		   
		   }
	   catch(Exception exp){
		   reportFailure("Element not present" +locator);
	   }
	   return e;
 }
 
   
   
   
   public void    validateTitle(){
	   test.log(Status.INFO,"In validateTitle method ");
	   	 String expectedTitle = "ABC";
	   	 String actualTitle = driver.getTitle();
	   	 if(!actualTitle.equals(expectedTitle)){
	   		 reportFailure("Title not found");
	   	 }
}
   
   public void    validateElementPresent(){
	   test.log(Status.INFO,"In validateElementPresent method ");
	   	 if(!isElementPresent(objectKey)){
	   		 reportFailure("Element not present" +objectKey);
	   	 }
}
   
   
   public boolean isElementPresent(String locator){
		List<WebElement> elementList;
		if(locator.endsWith("_id")){
		   elementList  =	driver.findElements(By.id(prop.getProperty(locator)));
		   }
	
		else if(locator.endsWith("_xpath")){
			elementList =	driver.findElements(By.xpath(prop.getProperty(locator)));
	     	}
		
		else{
			elementList = 	driver.findElements(By.cssSelector(prop.getProperty(locator)));
		    }
		
		if(elementList.size()==0){
			return false;
		}
		else return true;
	}
   
   public void reportFailure(String message){
	   test.log(Status.FAIL,"Failed the test bacuse of  "+message);
	  // Assert.fail(message);
	   takeScreenShot();
     if(proceedOnFail.equals("Y")){
		   sa.fail(message);
   }
	   else {
		   sa.fail(message);
		   sa.assertAll();
	   }
 }
   
   
   
	public void takeScreenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
			//put screenshot file in reports
			test.log(Status.FAIL,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

   
   
   
   public void assertAll(){
	   sa.assertAll();
	   }

   
   public void quit(){
	   if(driver!=null){
		   driver.quit();
	   }
	  }
 
  

}
