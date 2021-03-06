package testcases;

import org.testng.annotations.Test;

import data.Session;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class LoginReq {

	
	
	@Test
	
	public void doLogin(){
		
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.basePath = "Retail_App_Rest/retail/admin";
		
		Session s = new Session();
		
		s.setUsername("admin");
		
		s.setPassword("whizdom");
		
		Response resp = given().contentType(ContentType.JSON).log().body().when().body(s).post("login");
		
		resp.prettyPrint();
		
		System.out.println("----------------Session---------------------");
		
		String session = resp.getHeader("sessionId");
		System.out.println(session);
		
		System.out.println("-------------Headers----------------------");

		
		System.out.println(resp.getHeaders());
		
		
		
	}
}
