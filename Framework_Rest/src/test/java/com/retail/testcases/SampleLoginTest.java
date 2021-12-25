package com.retail.testcases;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.retail.base.BaseTest;
import com.retail.base.Session;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SampleLoginTest extends BaseTest {
	
	@Test
	public static void doLogin(){
		
		Session s = new Session();
		
		
		s.setUsername("admin");
		
		s.setPassword("whizdom");
		
		Response resp = given().contentType(ContentType.JSON).log().body().when().body(s).post("login");
		
		resp.prettyPrint();
	    sessionId = resp.getHeader("sessionId");
	    
	    System.out.println("The session id is "+sessionId);
	    System.out.println("-----------------------------------------------------------");
	    
       
	    JsonPath js = resp.jsonPath();
	    String actualStatus = js.getString("loginStatus");
	    String expectedStatus = "success";
	    
	    if(!Pattern.matches(actualStatus,expectedStatus)){
	        reportFailure("invalid reponse",false);
	    	
	       }
	   
	    if(!Pattern.matches("^[a-zA-Z0-9]*",sessionId)){
	        reportFailure("Session id format not correct",true);
	    	
	       }
	    

	    
	    softAssert.assertAll();
	    
	    

			
	}
}
