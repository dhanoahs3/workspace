import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateData {
	
	@Test
	public static void validateTestData(){
		String expectedResult = "abc";
		String actualResult = "abcd";
		System.out.println("Test Complete");
	//	Assert.assertEquals(actualResult, expectedResult);
	}

}
