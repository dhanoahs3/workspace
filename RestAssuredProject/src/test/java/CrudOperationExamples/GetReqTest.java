package CrudOperationExamples;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class GetReqTest {
	
@Test
	
	public void getAllStudent(){
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		
		//We can filter our get request based on data we provide within he get request--->("/list?programme=Computer Science&limit=2
		//Another important thing to note is when we filter our results in get method we might need to change the basePath as well
		//Like 
		
		Response response = given().contentType(ContentType.JSON).header("X","Y").log().all().get("/list?programme=Computer Science&limit=2");
		
	 	response.prettyPrint();

 }	 	
	 	
}
