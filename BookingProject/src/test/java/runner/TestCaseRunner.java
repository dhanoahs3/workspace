package runner;

import java.util.ArrayList;
import java.util.List;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.beust.testng.TestNG;

public class TestCaseRunner {

	public static void main(String[] args) {
		
		
		TestNG testng = new TestNG();
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("My Suite");
		
		List<XmlSuite> allSuites = new ArrayList<XmlSuite>();
		
		allSuites.add(mySuite);
		
		testng.setXmlSuites(allSuites);
		
		XmlTest bookingTest = new XmlTest(mySuite);
		
		bookingTest.setName("BookingSearch");
		
      
		
		List<XmlClass> allClasses = new ArrayList<XmlClass>();
		allClasses.add(new XmlClass("testcases.BookingSearch"));

		bookingTest.setXmlClasses(allClasses);

		testng.run();
		
		
		
		
		
		

	}

}
