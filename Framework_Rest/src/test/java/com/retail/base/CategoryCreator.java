package com.retail.base;

public class CategoryCreator {
	
	
	
public CategoryList categoriesList;
	

	

	
	
	public CategoryList addCategory(String catName){
		
		if(categoriesList==null){
			System.out.println("list is empty");
			categoriesList = new CategoryList();
			}
		
		categoriesList.createNewList(catName);
		
		
		return categoriesList;
		
	}

}
