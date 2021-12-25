package downloadvalidator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.File;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;


public class FileDownloadExample {
	
	
	@Test
	public void verifyDownloadTest(){
		
		File inputFile = new File(System.getProperty("user.dir")+File.separator+("DisableMonitor-G1.92.zip"));
		int expectedValue = (int)inputFile.length();
		
		System.out.println("The downloaded input file is "+inputFile);

		
		System.out.println("The size of downloaded input file is "+inputFile.length());
		
		 byte[] actualValue = given().get("https://github.com/Eun/DisableMonitor/releases/download/G1.92/DisableMonitor-G1.92.zip").then().extract().asByteArray();
		 
			
		    System.out.println("The expected file is "+ actualValue);

			System.out.println("The expected size of file is "+ actualValue.length);

			
			assertThat(expectedValue,equalTo(actualValue.length));

		
		
	}

}
