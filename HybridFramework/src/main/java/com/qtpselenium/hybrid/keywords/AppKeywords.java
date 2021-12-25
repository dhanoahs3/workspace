package com.qtpselenium.hybrid.keywords;

public class AppKeywords extends GenericKeywords {

 public void validateLogin(){
	 
	  System.out.println("Validating Login");

	}
 
 public void defaultLogin(){
	 
	 String username = envProp.getProperty("adminusername");
	 String password = envProp.getProperty("adminpassword");
     System.out.println("Default username is " +username);
     System.out.println("Default password is " +password);


	}
		
}
