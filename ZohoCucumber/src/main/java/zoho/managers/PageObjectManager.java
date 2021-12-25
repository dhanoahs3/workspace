package zoho.managers;

import zoho.pages.Homepage;

public class PageObjectManager {
	
	Homepage homePage;
	WebDriverManager app;
	
	
	public PageObjectManager(){
		this.app  = new WebDriverManager();
	}
	
	public Homepage getHomePage(){
	   if(homePage ==null)
		   this.homePage = new Homepage();
	   return this.homePage;
		
	}

}
