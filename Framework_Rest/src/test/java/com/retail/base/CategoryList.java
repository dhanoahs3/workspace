package com.retail.base;
import java.util.*;

public class CategoryList {
	
	
	public Map<String,List<Product>>  productMap;
	public List<Product> productList;
	public String categoryName;
	

	public CategoryList(){
		if(productMap == null){
			productMap  = new HashMap<String,List<Product>>();
		}
	};
	
	public void createNewList(String categoryName){
		System.out.println("adding key to map"+categoryName);
		this.categoryName = categoryName;
		productList = new ArrayList<Product>();
		productMap.put(categoryName,productList);
		
	}
	
	
	
	


	
	public Map<String,List<Product>> addProduct(Product p ){
		productList.add(p);
		return productMap;
		
	}
}
