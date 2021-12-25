package testcases;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class BookingSearch {
	
	
	@Test
	public void searchHotel(){
		System.out.println("--------------Searching for a hotel------------");
	}

	
	@Test
	public void selectHotel(ITestContext context){
		System.out.println("--------------Selecting  a hotel------------");
		String hotelName = "Marriot";
		context.setAttribute("hotel",hotelName);
	}
	
	
	@Test
	public void selectRoom(){
		System.out.println("--------------Selecting room in the hotel------------");
	}
}
