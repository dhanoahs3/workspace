package com.qtpselenium.core.ddf.testcases;

import org.testng.annotations.Test;

public class DummyTestA {
	
	@Test(priority=1)
	public void testA1(){
		System.out.println("Test A1");
	}
	
	@Test(priority=1,dependsOnMethods={"testA1"})
	public void testA2(){
		System.out.println("Test A2");
	}

	
	@Test(priority=1,dependsOnMethods={"testA2"})
	public void testA3(){
		System.out.println("Test A3");
	}


}
