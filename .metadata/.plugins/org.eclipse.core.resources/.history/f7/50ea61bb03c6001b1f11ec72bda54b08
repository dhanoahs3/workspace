package com.retail.testcases;
import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.Status;
import com.retail.base.BaseTest;
import com.retail.base.Category;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddCategory extends BaseTest {
	
	@Test(dataProvider = "getData")
	
	public void addCategory(Hashtable<String,String> data){
		
			
		
		
		String categoryName = data.get("CategoryName");
		
		Category c = new  Category();
		
		
		c.setCategoryname(categoryName);
		
		System.out.println("The session id to be added is "+sessionId);
		
		
Response resp = given().filter(new RequestLoggingFilter(requestCapture)).contentType(ContentType.JSON).headers("sessionid",sessionId).log().all().when().body(c).post();


//.log().all().when().body(s).post();

System.out.println("-----------------------------Response--------------------------------------------------------");
		
		resp.prettyPrint();
	    
	    test.log(Status.INFO,resp.prettyPrint());
	    
	    
      //     addRequestToLink(this.getClass().getSimpleName()+" Request",this.getClass().getSimpleName()+" Request-"+iteration,requestWriter.toString());
	    
		
		
		
	}

}
