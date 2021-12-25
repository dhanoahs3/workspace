package extraction;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonExtractionExamples {
	
	@Test
	public void test001() {

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
	Response resp = given().contentType(ContentType.JSON).log().all().get("/94");
	resp.prettyPrint();
	
	JsonPath extractor = resp.jsonPath();
	
	  System.out.println("---------------------------------------------");

		String firstName = extractor.getString("firstName");
		System.out.println(firstName);
		
		String lastName = extractor.getString("lastName");
		System.out.println(lastName);
		
	}

}
