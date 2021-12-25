package com.qtpselenium.zoho.project.base;
import com.qtpselenium.zoho.project.util.ExtentManager;


import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.IExtentTestClass;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	WebDriver driver = null;
	public Properties p =null;
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	public SoftAssert softAssert = new SoftAssert();
	public boolean gridRun = false;
	//public FirefoxOptions options =  new FirefoxOptions();	
    
		
		

	public void init(){
		FileInputStream fs1;
		if(p==null){
			p = new Properties();
			try {
			fs1 = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\projectconfig.properties");
			p.load(fs1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
	}
	
	
	

	public void openBrowser(String browser){
System.setProperty("webdriver.chrome.driver",p.getProperty("chromeexe"));
System.setProperty("webdriver.gecko.driver",p.getProperty("firefoxexe"));

if(!gridRun){
        if(browser.equals("Mozilla")){
        	
    		test.log(LogStatus.INFO,"Opening Browser "+ browser);
	
       
		driver =  new FirefoxDriver();
        	}
        
        else if(browser.equals("Chrome")){
        	
driver = new ChromeDriver();
        }
        
		test.log(LogStatus.INFO,"Opening Browser "+ browser);

}
else{
	
	DesiredCapabilities cap=null;
	if(browser.equals("Mozilla")){
		cap = DesiredCapabilities.firefox();
		cap.setBrowserName("firefox");
		cap.setJavascriptEnabled(true);
		cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		
	}else if(browser.equals("Chrome")){
		 cap = DesiredCapabilities.chrome();
		 cap.setBrowserName("chrome");
		 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
	}
	
	try {
		driver = new RemoteWebDriver(new URL("http://172.20.10.4:4444/wd/hub"), cap);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
        	
	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	public void navigate(String UrlPath){
driver.get(p.getProperty(UrlPath));
test.log(LogStatus.INFO,"Navigating to page " + p.getProperty(UrlPath));

		
	}
	public void type(String xPath ,String text){
		try {
			findElement(xPath).sendKeys(text);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void click(String xPath){
		try {
			findElement(xPath).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		
		
	}
	public WebElement findElement(String locator) throws InterruptedException{
		WebElement   e = null; 
		try{
		if(locator.endsWith("_xpath"))
		e =	driver.findElement(By.xpath(p.getProperty(locator)));
		else if(locator.endsWith("_id"))
		e = driver.findElement(By.id(p.getProperty(locator)));
		else if(locator.endsWith("_name"))
		e = 	driver.findElement(By.name(p.getProperty(locator)));
		else {
			reportFailure("Locator not correct ---" + locator);
			Assert.fail("Locator not correct ---"+locator);
		}
		}
		catch(Exception ex){
			reportFailure("Locator not correct ---" + ex.getMessage());
           ex.printStackTrace();
			System.out.println("Locator not found " + ex.getMessage());
		}
		
		return e;
		
		
	}
	public Boolean isElementPresent(String locator) throws InterruptedException{
		List<WebElement> alllinks = null;
		if(locator.endsWith("_xpath"))
			alllinks =	driver.findElements(By.xpath(p.getProperty(locator)));
			else if(locator.endsWith("_id"))
			alllinks = driver.findElements(By.id(p.getProperty(locator)));
			else if(locator.endsWith("_name"))
			alllinks = 	driver.findElements(By.name(p.getProperty(locator)));
			else {
				reportFailure("Locator not correct ---" + locator);
				Assert.fail("Locator not correct ---"+locator);
			}
		if(alllinks.size()>0){
			System.out.println("Element found");
			return true;
			
		}
		
		return false;
	}
	
	public String getText(String locatorKey) throws InterruptedException{
		test.log(LogStatus.INFO, "Getting text from "+locatorKey);
		return findElement(locatorKey).getText();

	}
	

	
	public Boolean verifyText(String locator ,String text) throws InterruptedException{
		String expected_text = p.getProperty(text);
		String actual_text  = findElement(locator).getText();
	      if(actual_text.contains(expected_text)){
		System.out.println("Actual text contains portion of expected text");
		return true;
		}
		return false;
		}
	
	
	public void reportFailure(String msg) throws InterruptedException{
		test.log(LogStatus.FAIL , msg);
		takeScreenShot();
		Assert.fail(msg);
		System.out.println(msg);
	}
	
	public void takeScreenShot() throws InterruptedException{
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		//System.out.println(screenshotFile);
       // System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");

		//driver = new FirefoxDriver();
		//driver.get("https:\\ndtv.com");

Thread.sleep(2000);
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
	}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		
	test.log(LogStatus.INFO,"Screenshot-> "+ test.addScreenCapture(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
		



}
	public void quit(){
		if(driver!=null){
			driver.quit();
		}
	}

	/**********************App functions
	 * @return 
	 * @throws InterruptedException ***************/
	
	public boolean doLogin(String username ,String password) throws InterruptedException{
		test.log(LogStatus.INFO,"trying to login with the credentials "+ username + password);
 // driver.findElement(By.xpath()).click();
		
		click("loginLink_xpath");
        System.out.println("logging");
		type("email_xpath",username);
		click("next_xpath");

		type("password_xpath",password);
		
		WebDriverWait wait = new WebDriverWait(driver,10);

		wait.until(ExpectedConditions.elementToBeClickable(findElement("loginbutton_xpath")));


		Thread.sleep(3000);

		click("loginbutton_xpath");

	//	click("clikbutton_xpath");


		if(isElementPresent("crmlink_xpath"))
		{
test.log(LogStatus.INFO,"loggin successful with  the credentials "+ username + password);
return true;
		}
		else 
		{
	test.log(LogStatus.INFO,"loggin NOT successful the credentials "+ username + password);
	return false;

		}
	}
	
	public int findLeadRowNum(String lastname){
		test.log(LogStatus.INFO,"Trying to find the row no of lead we just created");

List<WebElement> allleads = driver.findElements(By.xpath(p.getProperty("leadtable_xpath")));
for(int i=0;i<allleads.size();i++){
if(allleads.get(i).getText().trim().equals(lastname)){
test.log(LogStatus.INFO,"lead found on row no "+(i+1));

				return(i+1);}
		}
		



		test.log(LogStatus.INFO,"lead not found ");

		return -1;
	
		
	}
	
	public void clickOnLead(String leadName) throws InterruptedException{
		test.log(LogStatus.INFO, "Clicking the lead "+leadName);
		int rNum=findLeadRowNum(leadName);
		if(rNum==-1)
		reportFailure("Lead not found "+leadName );
		driver.findElement(By.xpath(p.getProperty("leadpart1_xpath")+rNum+p.getProperty("leadpart2_xpath"))).click();

	}
	
	
	public void selectDate(String d){
		test.log(LogStatus.INFO, "Selecting the date "+d);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dateTobeSelected = sdf.parse(d);
			Date currentDate = new Date();
			sdf = new SimpleDateFormat("MMMM");
			String monthToBeSelected=sdf.format(dateTobeSelected);
			sdf = new SimpleDateFormat("yyyy");
			String yearToBeSelected=sdf.format(dateTobeSelected);
			sdf = new SimpleDateFormat("d");
			String dayToBeSelected=sdf.format(dateTobeSelected);
			//June 2016
			String monthYearToBeSelected=monthToBeSelected+" "+yearToBeSelected;
			
			while(true){
				if(currentDate.compareTo(dateTobeSelected)==1){
					//back
					click("back_xpath");
				}else if(currentDate.compareTo(dateTobeSelected)==-1){
					//front
					click("forward_xpath");
				}
				
				if(monthYearToBeSelected.equals(getText("monthYearDisplayed_xpath"))){
					break;
				}
				
				
			}
			driver.findElement(By.xpath("//td[text()='"+dayToBeSelected+"']")).click();
			test.log(LogStatus.INFO, "Date Selection Successful "+d);

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public void moveToCoordinate(String locator) throws InterruptedException{
	int y = findElement(locator).getLocation().y;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollTo(0,"+660+")");
}
	
	
public int findContactRowNum(String lastname){
	test.log(LogStatus.INFO,"Trying to find the row no of contact we just created");

List<WebElement> allcontacts = driver.findElements(By.xpath(p.getProperty("contacttable_xpath")));
for(int i=0;i<allcontacts.size();i++){
	System.out.println(allcontacts.get(i).getText());

if(allcontacts.get(i).getText().contains(lastname)){
test.log(LogStatus.INFO,"contact found on row no "+(i+1));

			return(i+1);}
	}
	



	test.log(LogStatus.INFO,"contact not found ");

	return -1;

	
}

public void clickOnContact(String contactName) throws InterruptedException{
	test.log(LogStatus.INFO, "Clicking the account "+contactName);
	int rNum=findContactRowNum(contactName);
	if(rNum==-1)
	reportFailure("Contact not found "+contactName );
	System.out.println("Contact has been found on row no "+rNum);
	/*
	List<WebElement> allcols = driver.findElements(By.xpath("//*[@id='listViewTable']/tbody/tr["+rNum+"]/td[3]/table/tbody/tr[2]/td[5]"));
	System.out.println("Size of list is "+allcols.size());
	for(int i=0;i<allcols.size();i++){
		System.out.println(i+"---------"+allcols.get(i).getText());
	}
*/	
WebElement e=	driver.findElement(By.xpath(p.getProperty("contactpart1_xpath")+rNum+p.getProperty("contactpart2_xpath")));
int y = e.getLocation().y;
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("window.scrollTo(0,"+y+")");
Thread.sleep(2000);
e.click();

}



}
