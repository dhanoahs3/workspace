package basics;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllStudentsBetter {
	
	
	@Test
	
	public void getAllStudentsData(){
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student/list";
		
		
		// We can do everything we did in earlier program here in just one line
		//i.e create a reponse object ,then add contentType ,then add header ,then log the request and then 
		//apply get object
		//and since get method applied on given() returns an response obect so we can define a reference to 
		//response obect on the left side.
		
		Response response = given().contentType(ContentType.JSON).header("X","Y").log().all().get();
		
		
		//Then we can get the response in a nice format
		response.prettyPrint();
		
		
		//or we can get contentType or headers etc from response.
		System.out.println(response.getContentType());
		
		  
	    System.out.println("--------------------------------");

		//Getting the header
		

	   System.out.println(response.getHeaders());
	   
	   
	    System.out.println("--------------------------------");

		//Getting the time

		 System.out.println(response.getTime());
		 
		
		 System.out.println("--------------------------------");

	   
		//Getting the status code
		
	   System.out.println(response.getStatusCode());
		
		
		
	}

}
