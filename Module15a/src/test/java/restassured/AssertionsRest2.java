package restassured;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AssertionsRest2 {
	
	
	@Test
	
	public void getAllStudentsData() throws IOException, JSONException{
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student/1";

		String expected = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"expected.txt")));
		String actual = given().contentType(ContentType.JSON).get().asString();
		
		System.out.println(actual);
		
		
		/*LENIENT will only fail if firstName or any other field is different that is its vernon in actual 
		results but verfon in txt file
		LENIENT will pass if we remove id frome expected file . i.e id will be present in actual response but 
		will not be there in expected file.*/
	//	JSONAssert.assertEquals(expected, actual,JSONCompareMode.LENIENT);
		
	    /*STRICT will fail if we remove id frome expected file . i.e id will be present in actual response but 
		will not be there in expected file.
		STRICT will also fail if we change ordering on an array. i.e in courses we put statistics in front of 
		accounting in our txt file.*/
		
	//	JSONAssert.assertEquals(expected, actual,JSONCompareMode.STRICT);

		
		 /*STRICT_ORDER will pass if we remove id from expected file . i.e id will be present in actual response but 
		will not be there in expected file as STRICT_ORDERING has nothing to do with extensibility.
		BUt it will fail if we change ordering on an array. i.e in courses we put statistics in front of 
		accounting in our txt file.*/
		
	//	JSONAssert.assertEquals(expected, actual,JSONCompareMode.STRICT_ORDER);
		

		 /*NON_EXTENSIBLE will FAIL if we remove id from expected file . i.e id will be present in actual response but 
		will not be there in expected file as it is all about extensibility.
		BUt NON_EXTENSIBLE will pass if we change ordering on an array. i.e in courses we put statistics in front of 
		accounting in our txt file.*/
		
		JSONAssert.assertEquals(expected, actual,JSONCompareMode.NON_EXTENSIBLE);

		

		
		
		
	
		
		
		
	}


}
