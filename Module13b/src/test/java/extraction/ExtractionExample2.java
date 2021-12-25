package extraction;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ExtractionExample2 {
	
	
	
	@Test
	public void test001() {

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		Response resp = given().contentType(ContentType.JSON).log().all().get("/list");
	
       List<Integer> allIds = resp.then().extract().path("id");  
       List<String> allFirstNames = resp.then().extract().path("firstName"); 
       List<String> allLastNames = resp.then().extract().path("lastName");   
       List<String> allProgrammes = resp.then().extract().path("programme"); 
       List<String> allEmails = resp.then().extract().path("email");  
       
       
       for(int i =0;i<allIds.size();i++){
    	   
    	   System.out.println("The "+i+ " th  id is "+allIds.get(i));
    	   System.out.println("The "+i+ " th firstName is "+allFirstNames.get(i));
    	   System.out.println("The "+i+ " th lastName is "+allLastNames.get(i));
    	   System.out.println("The "+i+ " th programme is "+allProgrammes.get(i));
    	   System.out.println("The "+i+ " th email is "+allEmails.get(i));
    	   System.out.println("__________________________________________________________________________");

       }



	   
	}

}
