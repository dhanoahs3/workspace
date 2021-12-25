package hamcrestexample;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

public class HemCrestEx2 {
	
	
	@BeforeClass
	public void test00() {
		RestAssured.baseURI = "https://private-ef40f7-stu4.apiary-mock.com";
		RestAssured.basePath = "";
		
		
		/*RestAssured.baseURI = "https://private-c36bc-testapii1.apiary-mock.com";
		RestAssured.basePath = "";*/
	}
	
	
	//To check if body has ipoD in the response somewhere or not.
	/*@Test
	public void test001(){
		
		given().get("/search").then().body("query", equalToIgnoringCase("IPoD"));
		
	}
	
	//To check if no of numitems = 10
	
	@Test
	public void test001a(){
		
		given().get("/search").then().body("numItems", equalTo(10));
		
	}
	
	//Below method will check all maps named items and look for a key name that has a value as given below
	//If one of the maps named items has key as name and value as below it will pass.
	
	@Test
	public void test002(){
		
		given().get("/search").then().body("items.name",hasItem("Apple iPlod touch 16GB"));
	
	}
	
	
	//Below method will check all maps named items and look for a key name that has a values as given below
	//If few  maps named items has key as name and values  as the values from below it will pass.
	
	@Test
	public void test003(){
		
		given().get("/search").then().body("items.name",hasItems("Apple iPod touch 16GB","Refurbished Apple iPod Touch 32GB, 5th Generation, Blue","Apple iPod Touch 6th Generation 32GB Refurbished"));
	
	}*/
	

	//Used to check if a map named giftOptions in items no 0 has a key as allowGiftWrap and value as false
/*	@Test
	public void test004(){
	
	  given().get("/search").then().body("items[0].giftOptions", hasEntry ("allowGiftWrap",false));
		
	}
	
	/*
	
		//Used to check if a map named giftOptions in items no 0 has a key as allowGiftWrap

	@Test
	public void test005(){
		
	given().get("/search").then().body("items[0].giftOptions",hasKey("allowGiftWrap"));

		}*/
	
	@SuppressWarnings("unchecked")
	@Test
	public void test006(){
given().get("/search").then().body("items.findAll{it.name=='Apple iPod touch 128GB'}",hasItems("itemId"));
	
//given().get("/search").then().body("items.findAll{it.name=='Apple iPod touch 128GB'}", hasItems(hasEntry("name","Apple iPod touch 128GB")));
		
	}
	
	
	
	
	


}