package com.retail.testcases;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.retail.base.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddProduct extends BaseTest{
	
	
@Test(dataProvider = "getData")

public void addProduct(Hashtable<String,String> data){
	
	ProductDetails prod = new ProductDetails();
	 
	String categoryName = data.get("CategoryName");
	String productName = data.get("ProductName");
	String price = data.get("Price");
	String quantity = data.get("Quantity");

    Product product = new Product();
	product.setName(productName);
	product.setQuantity(quantity);
	product.setPrice(price);
	
	
	CategoryCreator cc = new CategoryCreator();
	
	CategoryList catlist = cc.addCategory(categoryName);
	
	
  /*  Map<String,List<Product>> productdetailsMap  = catlist.addProduct(product);
	prod.setProductDetails(productdetailsMap);
	
    Response resp = given().contentType(ContentType.JSON).headers("sessionid",sessionId).log().body().body(prod).post();
    resp.prettyPrint();
*/
}

}
