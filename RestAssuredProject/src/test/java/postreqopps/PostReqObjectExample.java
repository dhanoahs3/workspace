package postreqopps;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostReqObjectExample {
	
	@Test
	public void test001() {

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";

	
     Student student = new Student("hsdfds","dhffn","ddhhdhd@gmail.com","Computer Science");
     
    student.setCourses("Java");
    student.setCourses("Angular");
    student.setCourses("Mongo db");
		
		Response resp = given().contentType(ContentType.JSON).log().body()
				.body(student).post();
		System.out.println("-----------RESPONSE---------------");
		resp.prettyPrint();

	}

}



