package postreqarray;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostReqMapExample {
	
	
	@Test
	public void test001() {

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", "Harry");
		map.put("lastName", "Singh");
		map.put("email", "sdfsfddhh@gmail.com");
		map.put("programme", "Computer science");

		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("c");
		list.add("c++");

		map.put("courses", list);

		
		
		Response resp = given().contentType(ContentType.JSON).log().body().body(map).post();
		System.out.println("-----------RESPONSE---------------");
		resp.prettyPrint();

	}

}
