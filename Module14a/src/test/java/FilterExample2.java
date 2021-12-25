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
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;

public class FilterExample2 {
	
	
	public static StringWriter errorWriter;
	public static PrintStream errorCapture;
	
	
	@BeforeClass
	
	public void beforeEachMethod(){
		
		errorWriter = new StringWriter();
		errorCapture = new PrintStream(new WriterOutputStream(errorWriter),true);
	}
	
	
	
	
@Test
	
	public void getStudentData() throws JSONException{
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		
     given().filter(new ErrorLoggingFilter(errorCapture)).when().get("/100");
      String request = errorWriter.toString();
      
      System.out.println("--------------------------------------------------------------------");
		System.err.println(request);
		
	 }	

}
