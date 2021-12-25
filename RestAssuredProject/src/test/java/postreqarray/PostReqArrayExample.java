package postreqarray;

/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
*/
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostReqArrayExample {

	@Test
	
	public void createJsonArray(){
	/*	
		JSONObject jsonObject  =  new JSONObject();
		jsonObject.put("name","Hs");
		jsonObject.put("age","33");
		System.out.println(jsonObject);
		
		JSONArray jsonArray = new JSONArray();
		
		jsonArray.add("a");
		jsonArray.add("b");
		jsonArray.add("c");
		
	//	System.out.println(jsonArray);

		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		

		
		JSONObject jsonObject  =  new JSONObject();
		jsonObject.put("firstName","Hssiddngh");
		jsonObject.put("lastName","Dddhanoa");
		jsonObject.put("email","hsdddd@gmail.com");
		jsonObject.put("programme","CS");
		
        JSONArray jsonArray = new JSONArray();
		
		jsonArray.add("Math");
		jsonArray.add("Java");
		jsonArray.add("Python");
		
		jsonObject.put("courses",jsonArray);
		
		Response resp =  given().contentType(ContentType.JSON).log().body().body(jsonObject.toString()).post();
        resp.prettyPrint();
*/
	}
}


