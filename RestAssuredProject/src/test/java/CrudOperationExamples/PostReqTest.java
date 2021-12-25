package CrudOperationExamples;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostReqTest {
	
@Test
	
	public void postStudent(){
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		//For post request we have to create a external json file like we created and then create a File
		//object to access that file.
		
		File jsonPostReq = new File("C:\\workspace\\RestAssuredProject\\students.json");
		
		//Then based on given and adding contentType ,adding header and logs  and then we add the file
		//we created to the post request and then instead of get() this time we use post method --->post()
		Response response = given().contentType(ContentType.JSON).header("X","Y").log().all().body(jsonPostReq).post();
		response.prettyPrint();

 }	 	

}
