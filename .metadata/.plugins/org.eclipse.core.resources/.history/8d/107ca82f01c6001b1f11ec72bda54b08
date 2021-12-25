package com.retail.testcases;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.Status;
import com.retail.base.BaseTest;
import com.retail.base.Session;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginTest extends BaseTest {
	
	@Test(dataProvider = "getData")
	public  void doLogin(Hashtable<String,String> data) {
		
		Session s = new Session();
		
		String username = data.get("Username");
		
		String password = data.get("Password");
		s.setUsername(username);
		
		s.setPassword(password);
		
		
		//Response resp = given().filter(new RequestLoggingFilter(requestCapture)).contentType(ContentType.JSON).log().all().when().body(s).post();
		
		//Without logging the request
		
		Response resp = given().filter(new RequestLoggingFilter(requestCapture)).contentType(ContentType.JSON).log().all().when().body(s).post();

		resp.prettyPrint();
	    sessionId = resp.getHeader("sessionId");
	    
	    System.out.println("The session id is "+sessionId);
	    System.out.println("--------------------------------------------------------------------------------");
	    
	    
          addRequestToLink(this.getClass().getSimpleName()+" Request",this.getClass().getSimpleName()+" Request-"+iteration,requestWriter.toString());
	    
  	    test.log(Status.INFO,resp.prettyPrint());

	}

}
