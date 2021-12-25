package runner.custom;

import java.util.ArrayList;
import java.util.List;

public class Runner {

	public static void main(String[] args) {
		CustomTestNGRunner testNG = new CustomTestNGRunner(1);
		testNG.createSuite("Booking Suite", false);
		//testNG.addListener("listener.MyTestNGListener");// later
		testNG.addTest("Booking Test -Full");
		testNG.addTestParameter("action", "pay@hotel");
		List<String> includedMethods = new ArrayList<String>();
		includedMethods.add("searchHotel");
		includedMethods.add("selectHotel");
		includedMethods.add("selectRoom");
        testNG.addTestClass("testcases.BookingSearch", includedMethods);
        
		includedMethods = new ArrayList<String>();
		includedMethods.add("makeCCPayment");
		includedMethods.add("makeCashPayment");
		testNG.addTestClass("testcases.PaymentManager", includedMethods);
		
        testNG.run();
		//testNG.createSuite("Stock Management", false);
		
		
	}

}

