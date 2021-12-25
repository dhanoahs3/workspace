package basics;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllStudentsData {
	
	@Test
	
	public void asas(){
		
		//In any rest assured program we first define baseURl ,port and basePAth
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/student/list";
		
		//Then we make a req object . to make a given object need this line in our important 
		//import static io.restassured.RestAssured.given;



		RequestSpecification req  = given();
		
		//Then we can define content type for that req
		req = req.contentType(ContentType.JSON);
		
		//Then we can add headers
		req = req.header("X","Y");
		
		//logging-We can log the req to see how our request actually looks like
		System.out.println("---------------Getting req logs---------------------");
		req = req.log().all();
		
		//Then we create a response object that gets the response from the get method we applied on our req object.
	    Response response = req.get();
		
	    
	    System.out.println("--------getting content type--------------------");
 
	    
	    //Then from response object we can get contentType , headers , response code etc.
		//Getting the content type
		
		System.out.println(response.getContentType());
		
		  
	    System.out.println("-------------getting headers-------------------");

		//Getting the header
		

	   System.out.println(response.getHeaders());
	   
	   
	    System.out.println("------------get time--------------");

		//Getting the time

		 System.out.println(response.getTime());
		 
		
		 System.out.println("-------------get status code -------------");

	   
		//Getting the status code
		
	   System.out.println(response.getStatusCode());
	   
	   
	   
		
		
	//We can print the entire response	
		response.print();
		
		//Or we can print the response in a nice format.
		
		response.prettyPrint();
		
		
		
		
	}

	

}
