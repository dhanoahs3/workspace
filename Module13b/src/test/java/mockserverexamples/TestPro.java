package mockserverexamples;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestPro {
	
	@Test
	public void test001() {

		RestAssured.baseURI = "https://private-c36bc-testapii1.apiary-mock.com";
		RestAssured.basePath = "/search";
		
		Response resp = given().get();
	//	resp.prettyPrint();
		
		String str   = given().get().then().extract().path("destination_addresses[0]");
		
		 System.out.println(str);	

		System.out.println("---------------------running test 1------------------------------");
		 
		 String text   = given().get().then().extract().path("rows[0].elements[0].distance.text");
			
		 System.out.println(str);	

			System.out.println("---------------------+++++++++++------------------------------");
		 
		 
		HashMap<String,String> map   = given().get().then().extract().path("rows[0].elements[0].distance");
		
			System.out.println("The map is ");

			System.out.println(map);
			System.out.println("Done printing map ");


	}	
	
	
	@Test
	public void test002() {

		RestAssured.baseURI = "https://private-c36bc-testapii1.apiary-mock.com";
		RestAssured.basePath = "/search";
		
		Response resp = given().get();
		//resp.prettyPrint();
		System.out.println("Running test number 2");
		int noOfElements   = given().get().then().extract().path("rows[0].elements.size");
		
		System.out.println("---------------------------------------------------");
		 System.out.println("The no of elements are "+noOfElements);	
		 
		 
		
	}
	
	
	@Test
	public void test003() {

		RestAssured.baseURI = "https://private-c36bc-testapii1.apiary-mock.com";
		RestAssured.basePath = "/search";
		
		Response resp = given().get();
	//	resp.prettyPrint();
		
		List<HashMap<String,Object>> listOfItems = given().get().then().extract().path("rows[0].elements.duration.findAll{it.value='6227'}");
		
		System.out.println("---------------------------------------------------");
		 System.out.println("all items for text = 6227 are " +listOfItems);	
		 
		 
		
	}
	
	

}
