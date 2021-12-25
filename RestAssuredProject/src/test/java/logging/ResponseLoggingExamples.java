package logging;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ResponseLoggingExamples {

	@Test
	
	public void getHeaderLogs(){
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		System.out.println("--------------Log headers from response for get request---------------");
		
		given().contentType(ContentType.JSON).get("/18").then().log().headers();
		 
		System.out.println("--------------Log body from response for get request---------------");
		
		given().contentType(ContentType.JSON).get("/list").then().log().body();
			 
		System.out.println("--------------Log  if there is an error---------------");


        given().contentType(ContentType.JSON).get("/list").then().log().ifError();

                
        File jsonBody = new File("C:\\workspace\\RestAssuredProject\\students.json");
        
		System.out.println("--------------Log header from response from post request---------------");

        		
     //   given().contentType(ContentType.JSON).body(jsonBody).post().then().log().headers();
        
		System.out.println("--------------Log body from response from post request---------------");

        
        given().contentType(ContentType.JSON).body(jsonBody).post().then().log().body();


        		
		
	
  }
}
