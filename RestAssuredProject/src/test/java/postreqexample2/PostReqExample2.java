package postreqexample2;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class PostReqExample2{
	

	@Test
	public void postreq()
	{
		
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student";
		
		
		Student st = new Student();
		st.setFirstName("HS");
		st.setLastName("Dsdsf");
		st.setEmail("asdbdd@gmail.com");
		st.setProgramme("computer");
		
		Course course = new Course();
		course.setName("new course");
		course.setScore("1100");
		
		List<Course> allCourses = new ArrayList<Course>();
		allCourses.add(course);
		
		st.setCourses(allCourses);
		//st.addCourse("C++","70");
		
	//	System.out.println(st.toString());
		
		ObjectMapper obj = new ObjectMapper();
		String b = null;
		try {
		  b = 	obj.writeValueAsString(st);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Response resp = given().header("Content-Type", "application/json").contentType(ContentType.JSON).log().body()
				.body(b).post();
		
		System.out.println("-----------RESPONSE---------------");
		resp.prettyPrint();
		
		System.out.println("-----------RESPONSE---------------");

		
		System.out.println(resp.getBody().jsonPath().prettify());
		
		
	}

}
