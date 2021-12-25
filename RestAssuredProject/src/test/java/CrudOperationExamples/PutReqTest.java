package CrudOperationExamples;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutReqTest {
	
@Test
	
	public void putStudent(){
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		//For post request we update our request with the data we want to update.
		File jsonPutReq = new File("C:\\workspace\\RestAssuredProject\\students.json");
		
		
		//Then we need to know the student id which we are updating and we pass that id in put method and 
		// it will update that particular student with the data we passed above in students.json file.
		Response response = given().contentType(ContentType.JSON).header("X","Y").log().all().body(jsonPutReq).put("/101");
			 	response.prettyPrint();

 }	 	

}
