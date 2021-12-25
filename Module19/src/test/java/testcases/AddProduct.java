package testcases;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import data.CategoryCreator;
import data.CategoryList;
import data.Product;
import data.ProductDetails;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class AddProduct {
	
	
@Test
	
	public void addProduct(){
	
	
	RestAssured.baseURI = "http://localhost:8080";
	RestAssured.basePath = "Retail_App_Rest/retail/admin/product/add";
	
	ProductDetails prod = new ProductDetails();
	
	Product puma = new Product();
	
	puma.setName("puma  ferrari");
	puma.setQuantity("10");
	puma.setPrice("90");
	
	
	Product reebok = new Product();
	
	reebok.setName("skecther kids");
	reebok.setQuantity("120");
	reebok.setPrice("55");
	
	
	CategoryCreator cc = new CategoryCreator();
	
	CategoryList catlist = cc.addCategory("shoes");
	
	//catlist.addProduct(nike);
	
    Map<String,List<Product>> productdetailsMap  = catlist.addProduct(puma);
	prod.setProductDetails(productdetailsMap);
	

	productdetailsMap  = catlist.addProduct(reebok);
	prod.setProductDetails(productdetailsMap); 
	Product book1 = new Product();
	
	book1.setName("scala");
	book1.setQuantity("50");
	book1.setPrice("30");
	
	
	

	
	
	 catlist = cc.addCategory("books");
	
	productdetailsMap  = catlist.addProduct(book1);
	prod.setProductDetails(productdetailsMap);
	
	/*
	productdetails  = catlist.addProduct(book2);
	prod.setProductDetails(productdetails);*/
	
	
	Response resp = given().contentType(ContentType.JSON).headers("sessionid","AMt4vRKgYH").log().body().body(prod).post();
    resp.prettyPrint();

}

}
