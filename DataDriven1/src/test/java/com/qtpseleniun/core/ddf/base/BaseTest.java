package com.qtpseleniun.core.ddf.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qtpselenium.core.ddf.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class BaseTest {
	

	
	WebDriver driver = null;
	public Properties prop= null;
	public Properties envProp;
	public ExtentReports rep = ExtentManager.getInstance();
    public ExtentTest test = null;
    private boolean gridRun = true;
    
    
    public void init(){
    	if(prop==null){
			prop = new Properties();
			envProp = new Properties();
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//project.properties");
	        prop.load(fs);	
	        String env  = prop.getProperty("env");
			FileInputStream envProp = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//"+env+".properties");

	        
		} catch (Exception e) {
			e.printStackTrace();
		  }
		}
    }
	
	public void openBrowser(String bType){
		test.log(LogStatus.INFO, "Opening browser "+bType );
		if(!gridRun){
			if(bType.equals("Mozilla"))
				driver=new FirefoxDriver();
			else if(bType.equals("Chrome")){
				System.out.println("About to start chrome eh ");
				System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
				driver=new ChromeDriver();
			}
			
		}else{// grid run
			
			DesiredCapabilities cap=null;
			if(bType.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(bType.equals("Chrome")){
				 cap = DesiredCapabilities.chrome();
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser opened successfully "+ bType);

}
	
	public void navigate(String urlKey){
		driver.get(prop.getProperty(urlKey));
	}
	
	public void type(String xpath,String textToType){
		driver.findElement(By.xpath(prop.getProperty(xpath))).sendKeys(textToType);;
	}
	
	public void click(String xpath){
		driver.findElement(By.xpath(prop.getProperty(xpath))).click();
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
		catch(Exception ex){
			reportFailure(ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test as the locator was not valid");
		}
		return e;
		
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
	
	public boolean verifyText(String locator,String expectedResult){
		if(getElement(locator).getText().trim().equals(prop.getProperty(expectedResult))){
			return true;
		}
		else
		return false;
	}
	
	public void verfiyTitle(){
		}

	public void reportPass(String message){
		test.log(LogStatus.PASS,message);
	}
	
	public void reportFailure(String message){
		test.log(LogStatus.FAIL,message);
		captureScreenshot();
		Assert.fail(message);
	}
	
	public void captureScreenshot(){
		Date d=new Date();
		String fileName=d.toString().replace(":", "_").replace(" ", "_");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL,"Screenshot -->"+test.addScreenCapture(System.getProperty("user.dir")+"\\screenshots\\"+fileName+".jpg"));

	}
	
	public void login(String username,String password){
		test.log(LogStatus.INFO,"Trying to log using credentials "+username +" and "+password);
		wait(1);
		waitForPageLoad();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		type("login_xpath",username);
		click("nextbtn_xpath");
         //		Code to write if test has passed
		if(true){
			test.log(LogStatus.INFO,"Login succesfull");

		 }
		// Code to write if test has failed
		if(true){
			reportFailure("Login test has failed");
		}
		
	}
	
	public void waitForPageLoad(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String state = (String)js.executeScript("return document.readyState");
		while(!state.equals("complete")){
		wait(2);
		state = (String)js.executeScript("return","execute.readyState");
		}
	}

   public void wait(int timeToWait){
	   try {
			Thread.sleep(timeToWait*1000);
	   } catch (InterruptedException e) {
			e.printStackTrace();
		  }
	 }
	
	public void quit(){
		if(driver!=null)
		driver.quit();
	}

	
	public void clickAndWait(String locatorClicked,String locatorPresent){
		int count =5;
		for(int i=1;i<=5;i++){
			getElement(locatorClicked).click();
		    wait(1);
		    if(isElementPresent(locatorPresent)){
		    	break;
		    }
		}
	}
	
	public void acceptAlert(){
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		test.log(LogStatus.INFO,"About to accept the results");
        driver.switchTo().defaultContent();
   	}
	
	
	public void selectData(String d) {
		/*Date currentDate = new Date(); //gets current date as Date object
		System.out.println(currentDate);
		String date = "25/12/2020"; //put date in String
		SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy"); //object of simple date format with particular format as argument
		Date d1  = sdf.parse(date); // convert Strinf date to Date object
		System.out.println(d1.compareTo(currentDate)); //compare 2 date objects
		sdf  = new SimpleDateFormat("MM"); //make another object of simple date format wth only month as parameters
		System.out.println(sdf.format(currentDate)); //get just the month from the date object.
		--------------------------------Actual test---------------------------------------------------------
*/		
		SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dateToBeSelected = sdf.parse(d);
			Date currentDate = new Date();
			  
			sdf  = new SimpleDateFormat("MMMM");
			String monthToBeSelected = sdf.format(dateToBeSelected);
			sdf  = new SimpleDateFormat("yyyy");
			String yearToBeSelected = sdf.format(dateToBeSelected);
			String monthYearSelected = monthToBeSelected + dateToBeSelected;
			
			while(true){
				
				if(currentDate.compareTo(dateToBeSelected)==1){
					click("next_xpath");
					
				}
				
				else  if(currentDate.compareTo(dateToBeSelected)==-1){
					click("previous_xpath");
					
				}
				String monthYearDisplayed = driver.findElement(By.xpath("")).getText();
				if(monthYearSelected.equals(monthYearDisplayed));
				break;
			}
			
			//Then we click the day 
            driver.findElement(By.xpath("")).click();
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
   	}
 }
