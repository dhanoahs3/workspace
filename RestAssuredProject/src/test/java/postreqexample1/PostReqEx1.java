package postreqexample1;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import postreqexample1.Student;

public class PostReqEx1 {
	
	
	@Test
	public void test001() {
		System.out.println("fff");

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";

     Student student = new Student("harry","singhh","dsdddsssrrfiid@gmail.com","Computer Science");
    student.setCourse(new Course("Java","34"));

   
		Response resp = given().contentType(ContentType.JSON).log().body().body(student).post();
		System.out.println("-----------RESPONSE---------------");
		resp.prettyPrint();

	}

}
