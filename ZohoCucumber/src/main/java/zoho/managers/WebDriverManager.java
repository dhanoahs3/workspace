package zoho.managers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class WebDriverManager {
	
	WebDriver driver;
	
	public void openBrowser(String browser){

		if (browser.equals("Chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equals("Mozilla")) {
			driver = new FirefoxDriver();

		} else if (browser.equals("Edge")) {
			driver = new EdgeDriver();
		}
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  }
	
	public void navigate(String url){
		driver.get(url);
	}
	
	public void click(String locator){
		driver.findElement(By.xpath(locator)).click();
	}
	
	public void type(String locator,String data){
		driver.findElement(By.xpath(locator)).sendKeys(data);
	}

}
