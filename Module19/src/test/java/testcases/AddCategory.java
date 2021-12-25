package testcases;

import org.testng.annotations.Test;

import data.Category;
import data.Session;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class AddCategory {
	
	
@Test
	
	public void addCategory(){
	
	
	RestAssured.baseURI = "http://localhost:8080";
	RestAssured.basePath = "Retail_App_Rest/retail/admin/category/add";
	
	
	Category c = new Category();
	
	c.setCategoryname("skating boards");
	
	Response resp = given().contentType(ContentType.JSON).headers("sessionid","AMt4vRKgYH").log().body().body(c).post();
	
	resp.prettyPrint();
	
	
	
}
	

}
