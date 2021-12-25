package testcases;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class PaymentManager {
	
	@Test
	public void makeCCPayment(ITestContext context){
		String hotelBooked = (String) context.getAttribute("hotel");
		System.out.println("--------------Making payment by credit card to book hotel "+hotelBooked+" --------------");
	}
	
	
	@Test
	public void makeCashPayment(){
		System.out.println("--------------Making cash payment to book hotel------------");
	}

}
