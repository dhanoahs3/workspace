package logging;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RequestLoggingExample {

	
@Test
	
	public void getHeaderLogs(){
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		System.out.println("---------------Getting the headers ---------------------------");

		
		Response response = given().header("X","Y").log().headers().get("/12");
		
		response.prettyPrint();
	
  }

@Test

public void getParameterLogs(){
	
	
	RestAssured.baseURI = "http://localhost";
	RestAssured.port = 8080;
	RestAssured.basePath = "/student";
	
	System.out.println("---------------Getting all paramteres---------------------------");
	Response response = given().param("programme","computer science").log().params().get("/list");
	
	response.prettyPrint();

}





@Test

public void getBodyLogs(){
	
	
	RestAssured.baseURI = "http://localhost";
	RestAssured.port = 8080;
	RestAssured.basePath = "/student";
	
	System.out.println("---------------Getting the body ---------------------------");
	
	File jsonBody = new File("C:\\workspace\\RestAssuredProject\\students.json");
	
	 given().contentType(ContentType.JSON).log().body().body(jsonBody).post();

	

}
	
	
	@Test

	public void assertValidations(){
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		System.out.println("---------------Asserting that status code is 200 ---------------------------");
		
		File jsonBody = new File("C:\\workspace\\RestAssuredProject\\students.json");
		
		//then() method is used when we want to put some validation on a req
		//so below we post a body i.e body(jsonBody) to the server and 
		//then we used then() method to put validation after that 
		//so we put statusCode(500) and before body we also put log.ifValidationFails
		//so that means this test will only log if validation fails i.e only if status code is not 200
		
		 given().contentType(ContentType.JSON).log().ifValidationFails().body(jsonBody).post().then().statusCode(200);

		

	}
		
	
	

		
}
