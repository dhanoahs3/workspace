package restvalid;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Validationss {
	
	
	
	@Test
	public void test001() {

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		
		File schema  = new File("C:\\workspace\\Module15a\\schema.json");
			RestAssured.given().contentType(ContentType.JSON).log()
			.all().get("/12").then().body(matchesJsonSchema(schema));
		
	}	

}
