package CrudOperationExamples;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteReqTest {

@Test
	
	public void deleteStudent(){
	
	RestAssured.baseURI = "http://localhost";
	RestAssured.port = 8080;
	RestAssured.basePath = "/student";
	
	//We can pass a student id in delete method and it will delete that particular student.
	Response response = given().contentType(ContentType.JSON).header("X","Y").log().all().delete("/101");
		 	response.prettyPrint();
		 	
 }
}
