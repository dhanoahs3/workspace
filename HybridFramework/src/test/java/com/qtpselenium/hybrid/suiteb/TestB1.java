package com.qtpselenium.hybrid.suiteb;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qtpselenium.hybrid.base.BaseTest;
import com.qtpselenium.hybrid.util.Constants;
import com.qtpselenium.hybrid.util.DataUtil;

public class TestB1 extends BaseTest{

	@Test()
	
	
	public void testB1Test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		test.log(Status.INFO,"Starting test B1");

       System.out.println("In the Test B1");
		
		test.log(Status.PASS,"Test paseed");

	}

}

