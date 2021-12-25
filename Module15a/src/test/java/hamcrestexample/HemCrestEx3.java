package hamcrestexample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class HemCrestEx3 {
	
	/*@Test
	public void getAllStudents() {
		// Hard Assert
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student/10";
		
		//Here we can put multiple assertions on the same request. We just need to keep adding .body and we can 
		//keep putting multiple assertions. If all assertions pass test will pass ,otherwise it will fail.
		//Please note here we are talking about hard assertions i.e if we put greaterThan(20) in second line 
		//the test wil fail there itself. it will never check the below 2 assertions i.e lessThanOrEqualTo 
		// and greaterThanOrEqualTo
		given().contentType(ContentType.JSON).get().then().body("id", equalTo(10))
		.body("id", lessThan(11))
		.body("id", greaterThan(9))
		.body("id", lessThanOrEqualTo(12))
		.body("id", greaterThanOrEqualTo(10));
	}*/
	
	
	
	@Test
	public void getAllStudents1() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student/10";
		
		/*Below  is an example of soft assert. Here we put all validations in the same body rather than adding new 
		 * .body for different assertions as we did above. This test will check for all assertions as it is a soft 
		 * assertion. So if greaterThan(12) fails  the test will not stop there. it will check the remaining 
		 * assertions as well and if multiple assertions fail it will print all of them*/
		given().contentType(ContentType.JSON)
		.get().then()
		.body("id", equalTo(10),"id", lessThan(11),
				"id", greaterThan(9),"id",
				lessThanOrEqualTo(11),"id",
				greaterThanOrEqualTo(10));
	}

}
