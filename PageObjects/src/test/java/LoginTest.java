import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LandingPage;
import pages.LoginPage;

public class LoginTest {
	WebDriver driver;
	
	@Test
	public void doLogin(){
		
		System.setProperty("webdriver.chrome.driver","C:\\drivers\\latest\\chromedriver.exe");
        driver = new ChromeDriver();
        
		
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = homePage.goToLoginPage();
		LandingPage landingPage = loginPage.doLogin("ass","asas");
	}

}
