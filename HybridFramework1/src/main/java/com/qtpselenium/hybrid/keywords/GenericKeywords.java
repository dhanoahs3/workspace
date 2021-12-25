package com.qtpselenium.hybrid.keywords;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.hybrid.reports.ExtentManager;
import org.openqa.selenium.remote.RemoteWebDriver;


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
	  
	  if(prop.getProperty("gridRun").equals("Y")) {
			// run on grid
			
			DesiredCapabilities cap=null;
			if(browserType.equals("Mozilla")) {
				cap = DesiredCapabilities.firefox();
				cap.setJavascriptEnabled(true);
				cap.setPlatform(Platform.WINDOWS);
			}else if(browserType.equals("Chrome")) {
				cap = DesiredCapabilities.chrome();
				cap.setJavascriptEnabled(true);
				cap.setPlatform(Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}else {// run on normal browser

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
	}
	
   public void navigate(){
	   test.log(Status.INFO,"Navigating to website "+ this.envProp.getProperty(objectKey));
	   driver.get(envProp.getProperty(objectKey));
}

   public void type(){
	   
	   test.log(Status.INFO,"Typing something on webpage "+prop.getProperty(dataKey));
	  getObject(objectKey).sendKeys(dataKey);

   }
   

	public void type(String objectKey, String dataKey){
		setDataKey(dataKey);
		setObjectKey(objectKey);
		type();
	}


   public void click(){
	   test.log(Status.INFO,"clicking on a webelement "+prop.getProperty(objectKey)+ " Data. "+data.get(dataKey));
		  getObject(objectKey).click();

   }
   

	public void click(String objectKey){
		setObjectKey(objectKey);
		click();
	}

   

   public void clear(){
	   test.log(Status.INFO,"clearing webelement "+prop.getProperty(objectKey)+ " Data. "+data.get(dataKey));
		  getObject(objectKey).clear();

   }
   
   
   public WebElement getObject(String locator){
	   WebElement e = null;
	   System.out.println("the loacter is "+locator);
	   System.out.println("the loacter is "+prop.getProperty(locator));

	   
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
		   reportFailure("Element not present" +prop.getProperty(locator));
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
	
	public void waitForPageToLoad(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		
		while(i!=10){
		String state = (String)js.executeScript("return document.readyState;");
		System.out.println(state);

		if(state.equals("complete"))
			break;
		else
			wait(2);

		i++;
		}
		// check for jquery status
		i=0;
		while(i!=10){
	
			Long d= (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if(d.longValue() == 0 )
			 	break;
			else
				 wait(2);
			 i++;
				
			}
		
		test.log(Status.INFO, "wait over now going to verifyPortfoio method");

		
		}
	
	public void acceptAlert(){
		test.log(Status.INFO, "Switching to alert");
		
		try{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			test.log(Status.INFO, "Alert accepted successfully");
		}catch(Exception e){
			if(objectKey.equals("Y")){
				reportFailure("Alert not found when mandatory");
			}
		}
		
	}
	
	
	public void wait(int timeSec){
		try {
			Thread.sleep(timeSec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public void waitTillSelectionToBe(String objectkey , String expected) {
		int i=0;
		String actual="";
		while(i!=10){
			WebElement e = getObject(objectkey);
			Select s = new Select(e);
		    actual = s.getFirstSelectedOption().getText();
			if(actual.equals(expected))
				return;
			else
				wait(1);			
				i++;	
		}
		// reach here
		reportFailure("Values Dont match. Got value as "+actual);
		
		
	}
	
	public void validateElementNotInList(){
		if(isElementInList())
			reportFailure("Could not delete the option");
	}
	
	public boolean isElementInList(){
		// validate whether value is present in dropdown
				List<WebElement> options = new Select(getObject(objectKey)).getOptions();
				for(int i=0;i<options.size();i++){
					if(options.get(i).getText().equals(data.get(dataKey)))
						return true;
				}
				
				return false;
	}

	

	
	public void select(){
		test.log(Status.INFO,"Selecting from "+prop.getProperty(objectKey)+" . Data - "+data.get(dataKey));
		if(!isElementInList())
			reportFailure("Option not found in list "+ data.get(dataKey));
		
		new Select(getObject(objectKey)).selectByVisibleText(data.get(dataKey));
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
