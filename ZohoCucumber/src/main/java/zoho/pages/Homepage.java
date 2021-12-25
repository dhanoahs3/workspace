package zoho.pages;

import zoho.managers.WebDriverManager;

public class Homepage {
	
	WebDriverManager app;
	
	public Homepage(WebDriverManager app){
		this.app = app;
		
	}
	
	
	public void load(String browser){
		app.openBrowser(browser);
		app.navigate("https://www.zoho.com");
	}
	
	

}
