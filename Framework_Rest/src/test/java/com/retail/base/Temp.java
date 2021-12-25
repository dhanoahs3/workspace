package com.retail.base;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Temp {
	
	SoftAssert sa;
	
	@BeforeTest
	public void init(){
		
		sa = new SoftAssert();
		
	}
	
	
	@Test
	public void allTest(){
		
		
		reportFailure("some error",false);
		reportFailure("some error",false);
		reportFailure("some error",false);
		reportFailure("some error",false);
		
		System.out.println("in the test");


	}


	
	

private void reportFailure(String errorMsg, boolean stop) {
	
	sa.fail(errorMsg);
	
	if(stop){
		sa.assertAll();
	}
		
  }

}
