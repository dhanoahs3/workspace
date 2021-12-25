package postreqex3;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.Test;



public class StudentPostReq {
	
	

	@Test
	public void postreq()
	{
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		
		Student st = new Student("Abhinav","Kaushik","CSE","ahwuffdh@gmail.com");
		
		
		Course ob1 =st.addCourse("Java","70");
		ob1.addTopics("opps");
		ob1.addTopics("Genrics");
		ob1.addTopics("collections");
		
		
		
		Course ob2 =st.addCourse("C++","70");
		ob2.addTopics("opps");
		ob2.addTopics("Genrics");
		ob2.addTopics("collections");
		
		
		Response resp = given().contentType(ContentType.JSON).log().body()
				.body(st).post();
		
		System.out.println("-----------RESPONSE---------------");
		resp.prettyPrint();
		
		
	}

}
