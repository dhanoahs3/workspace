package pages;

import org.openqa.selenium.WebDriver;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage goToLoginPage(){
		driver.get("https:///www.google.com");
	 // click on the link to go to sign-in page
		return new LoginPage();
	}


	

}
