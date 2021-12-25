import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertExample {

	
	@Test(priority=1)
	 public void search(){
		
		System.out.println("Before assert");
		Assert.assertEquals("a","a");
		System.out.println("After assert");
		
		/*SoftAssert sa = new SoftAssert();
		sa.assertEquals("a","b");
		System.out.println("After soft assert");
        sa.assertAll();*/
    }
	
	
	@Test(priority=2,dependsOnMethods="search")
	public void AddToCart(){
		
		System.out.println("In add to cart method");
		
    }
	
	
	@BeforeTest
    public void beforeT(){
		
		System.out.println("In before Test");
	}
	
	@AfterTest
    public void afterT(){
		
		System.out.println("In after Test");
	}
	
	@BeforeMethod
    public void beforeM(){
		
		System.out.println("In before Method");
	}
	
	@AfterMethod
    public void afterM(){
		
		System.out.println("In after Method");
	}
}
