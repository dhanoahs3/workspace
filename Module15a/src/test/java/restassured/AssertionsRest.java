package restassured;

import org.testng.annotations.Test;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class AssertionsRest {
	
	
	@Test
	public void test001() throws JSONException {
		
		
	    String actualValue = "{id:1,name:\"John\",course:\"Math\",values=[\"x\",\"y\"]}";
		
		String expectedValue = "{id:1,name:\"John\",values=[\"x\",\"y\"]}";
		
		
		/*Lenient mode allows extra items to be added as well. So if course is present in first String and not 
		in second test will still pass.
		Also if order of array is not same like in values y comes before x in actualValue but in expectedValue 
		x comes before y even then the lenient test will pass.
		*/
		
		
	//	JSONAssert.assertEquals(expectedValue, actualValue,JSONCompareMode.LENIENT);

		/*STRICT is reverse of lenient. i.e it will fail if extra items like course is added 
		and it will also fail if ordering of items in values is not same i.e x comes before y or vice versa.*/
		
	//	JSONAssert.assertEquals(expectedValue, actualValue,JSONCompareMode.STRICT);
		
		/*We have two more assertions 
	    STRICT_ORDER will fail is x comes before y in values array but it will allow extensibility .
	    i.e if courses are added in actualValue but not present in expected_value then STRICT_ORDER will still pass
		*/
       /* String actualvalue1 ="{id:1,name:\"John\",course:\"CSE\", values=[\"x\",\"y\"]}";
		
		String expectedvalue1 = "{id:1,name:\"John\",values=[\"x\",\"y\"]}";
		
		JSONAssert.assertEquals(expectedvalue1, actualvalue1, JSONCompareMode.STRICT_ORDER);*/
		
		/*NON_EXTENSIBLE as names suggests will not allow 
		 strings to be extended so it will fail  if courses are added in
		 actualValue but not present in expected_value but it has nothing to do with ordering 
		 i.e if  x comes before y in values array it will pass*/
		
        String actualvalue2 ="{id:1,name:\"John\",values=[\"x\",\"y\"]}";
		
		String expectedvalue2 = "{id:1,name:\"John\",values=[\"y\",\"x\"]}";
		
		JSONAssert.assertEquals(expectedvalue2, actualvalue2, JSONCompareMode.NON_EXTENSIBLE);
		
			
		
	


	}
	

}
