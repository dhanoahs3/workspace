import static io.restassured.RestAssured.given;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FiltersExample {
	
	public static StringWriter requestWriter;
	public static PrintStream requestCapture;
	
	
	public static StringWriter responseWriter;
	public static PrintStream responseCapture;
	
	
	
	
	@BeforeClass
	
	public void beforeEachMethod(){
		
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter),true);
		
		responseWriter = new StringWriter();
		responseCapture = new PrintStream(new WriterOutputStream(responseWriter),true);
		
	}
	
	/*@Test
	
	public void getAllStudentsData(){
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		given().filter(new RequestLoggingFilter(requestCapture)).filter(new ResponseLoggingFilter(responseCapture)).when().get("/10");
		String request = requestWriter.toString();
		System.out.println(request);
		
		System.out.println("-------------------------------------------------------------------------");
		
		String response = responseWriter.toString();
	 	System.out.println(response);
		
		System.out.println("-------------------------------------------------------------------------");

		
	
	}*/

	
	@Test
	
	public void getStudentData() throws JSONException{
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		
		
		JSONObject obj = new JSONObject();
		obj.put("firstName","Abthinav");
		obj.put("latsName", "Kaesushik");
		obj.put("email", "aerbdertfgdgd@gmail.com");
		obj.put("programme", "CS");
		JSONArray arr = new JSONArray();
		arr.put("Java");
		arr.put("c");
		arr.put("c++");
		obj.put("courses", arr);
		
		
      given().filter(new RequestLoggingFilter(requestCapture)).filter(new ResponseLoggingFilter(responseCapture)).body(obj.toString()).when().post();
      String request = requestWriter.toString();
	  System.out.println(request);

	  System.out.println("-------------------------------------------------------------------------");
		
	 String response = responseWriter.toString();
	 System.out.println(response);
		
	System.out.println("-------------------------------------------------------------------------");

		
		
	 }	
		

}