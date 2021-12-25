package extraction;

import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ExtractionExamples {
	
	@Test
	public void test001() {

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		ArrayList<String> firstNameLists = given().contentType(ContentType.JSON).log().all().get("/list").then().extract().path("firstName");
	
	   System.out.println(firstNameLists);
	   
String firstName = given().contentType(ContentType.JSON).log().all().get("/12").then().extract().path("firstName");
String	lastName = given().contentType(ContentType.JSON).log().all().get("/12").then().extract().path("lastName");
String programme = given().contentType(ContentType.JSON).log().all().get("/12").then().extract().path("programme");
String email = given().contentType(ContentType.JSON).log().all().get("/12").then().extract().path("email");
ArrayList<String> courses = given().contentType(ContentType.JSON).log().all().get("/12").then().extract().path("courses");


System.out.println("----------------------------------------------------");

System.out.println("firstName----------> "+ firstName);
System.out.println("lastName----------> "+ lastName);
System.out.println("email----------> "+ email);
System.out.println("programme----------> "+ programme);
System.out.println("courses----------> "+ courses);

	}


}
