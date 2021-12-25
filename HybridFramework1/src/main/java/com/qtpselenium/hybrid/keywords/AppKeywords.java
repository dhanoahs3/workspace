package com.qtpselenium.hybrid.keywords;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class AppKeywords extends GenericKeywords {
	
	
	
	
	public void login(){
		test.log(Status.INFO, "Logging in"); 
		getObject("money_css").click();
		getObject("portfolio_xpath").click();
		test.log(Status.INFO, "hereee"); 

		
		String username="";
		String password="";
		
		if(data.get("Username") == null){
			username=envProp.getProperty("username");
			password=envProp.getProperty("password");
			test.log(Status.INFO, username+"---"+password); 

		}else{
			username=data.get("Username");
			password=data.get("Password");
		}
		getObject("username_id").sendKeys(username);
		
		
		getObject("password_id").sendKeys(password);
		getObject("continue_id").click();
		// security warining - mozilla
		//wait(5);
		
		//acceptAlert();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("password_id")));
		test.log(Status.INFO, "Login successfull"); 

	}
	
	public void validateLogin(){
		test.log(Status.INFO, "Validating Login");
		boolean result = isElementPresent("portfolioSelection_xpath");
		String expectedResult=data.get("ExpectedResult");
		String actualResult="";
		
		if(result)	
			actualResult="Loginsuccess";
		else
			actualResult="Loginfailure";
		if(!actualResult.equals(expectedResult))
			reportFailure("Got  result "+actualResult );

		
		
	}

	public void defaultLogin(){
	 
	 String username = envProp.getProperty("adminusername");
	 String password = envProp.getProperty("adminpassword");
     System.out.println("Default username is " +username);
     System.out.println("Default password is " +password);
}
	
	public void verifyPortfolio(){
		test.log(Status.INFO, "Verifying portfolio name "+data.get(dataKey));
		waitTillSelectionToBe("portfolioSelection_xpath",data.get(dataKey));
		
	}
	
	public void addStock(){
		test.log(Status.INFO, "Adding Stockdetails");
		waitForPageToLoad();
		click("addStockButton_id");
		test.log(Status.INFO, "click");

		type("stockName_id","Stock Name");
		test.log(Status.INFO, "click type");

		click("firstOption_xpath");
		test.log(Status.INFO, "clcik again");

		click("addStockCalendar_id");
		selectDate(data.get("Date"));
		type("stockQuantity_id","Quantity");
		type("stockPrice_id","PurchasePrice");
		click("addStockSubmit_id");
		waitForPageToLoad();
		test.log(Status.INFO, "Stock added and form submitted");
		test.log(Status.INFO, "Validating company in table");
		int rNum=getRowWithCellData(data.get("Stock Name"));
		if(rNum==-1)
			reportFailure("Could not find the Stock");
	}
	
	
	public void selectDate(String d){
		// day, month , year
		Date current = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date selected = sd.parse(d);
			String day = new SimpleDateFormat("d").format(selected);
			String month = new SimpleDateFormat("MMMM").format(selected);
			String year = new SimpleDateFormat("yyyy").format(selected);
			System.out.println(day+" --- "+month+" --- "+ year);
			String desiredMonthYear=month+" "+year;
			
			while(true){
				String displayedMonthYear=driver.findElement(By.cssSelector(".dpTitleText")).getText();
				if(desiredMonthYear.equals(displayedMonthYear)){
					// select the day
					driver.findElement(By.xpath("//td[text()='"+day+"']")).click();
					break;
				}else{
					
					if(selected.compareTo(current) > 0)
						driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[4]/button")).click();
					else if(selected.compareTo(current) < 0)
						driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[2]/button")).click();

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getRowWithCellData(String data){
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='stock']/tbody/tr"));
		for(int rNum=0;rNum<rows.size();rNum++){
			WebElement row = rows.get(rNum);
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for(int cNum=0;cNum<cells.size();cNum++){
				WebElement cell = cells.get(cNum);
				if(!cell.getText().trim().equals("") && data.contains(cell.getText()))
					return ++rNum;
			}
		}
		
		return -1;// not found
	}
	
}




		

