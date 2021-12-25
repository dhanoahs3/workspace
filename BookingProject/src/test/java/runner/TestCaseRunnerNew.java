package runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite.ParallelMode;

import com.beust.testng.TestNG;

public class TestCaseRunnerNew {
	
	
public static void main(String[] args) {
		
		
		TestNG testng = new TestNG();
		XmlSuite mySuite = new XmlSuite();
		mySuite.setParallel(ParallelMode.TESTS);
		mySuite.setName("Booking Suite");
		
		List<XmlSuite> allSuites = new ArrayList<XmlSuite>();
		
		allSuites.add(mySuite);
		
		testng.setXmlSuites(allSuites);
		
		XmlTest bookingTest = new XmlTest(mySuite);
		
		bookingTest.setName("BookingSearch");
		
		Map<String,String> parameters =  new HashMap<String,String>();
		parameters.put("action","pay@hotel");
		bookingTest.setParameters(parameters);

		List<XmlClass> testClasses  = new ArrayList<XmlClass>();
		
		XmlClass testClass = new XmlClass("testcases.BookingSearch");
		
		List<XmlInclude> methods  = new ArrayList<XmlInclude>();
		
		methods.add(new XmlInclude("searchHotel"));
		methods.add(new XmlInclude("selectHotel"));
        methods.add(new XmlInclude("selectRoom"));
        
        testClass.setIncludedMethods(methods);
        
        testClasses.add(testClass);
        
        bookingTest.setXmlClasses(testClasses);
        
  /*-------------------------------------------Second class  --------------------------------------------------*/
        
       testClass = new XmlClass("testcases.PaymentManager");
		
	   methods  = new ArrayList<XmlInclude>();
	   
		methods.add(new XmlInclude("makeCCPayment"));
        methods.add(new XmlInclude("makeCashPayment"));
		
        
        testClass.setIncludedMethods(methods);
        
        testClasses.add(testClass);
        
        bookingTest.setXmlClasses(testClasses);
        
//        testng.run();

        
        
        /*-------------------Second scenarios(only run Payment Manager for pay with cash------------------------*/
        
        System.out.println("------------>Second scenarios(only run Payment Manager for pay with cash");

        
        XmlTest bookingTestCashPayment = new XmlTest(mySuite);
		
		bookingTestCashPayment.setName("BookingSearch -Cash payment only");
		
		Map<String,String> parametersCashPayment =  new HashMap<String,String>();
		parametersCashPayment.put("action","pay@hotel");
		bookingTestCashPayment.setParameters(parameters);

		List<XmlClass> testClassesCashPayment = new ArrayList<XmlClass>();
		
		XmlClass testClassCashPayment = new XmlClass("testcases.BookingSearch");
		
		List<XmlInclude> methodsCashPayment  = new ArrayList<XmlInclude>();
		
		methodsCashPayment.add(new XmlInclude("searchHotel"));
		methodsCashPayment.add(new XmlInclude("selectHotel"));
		methodsCashPayment.add(new XmlInclude("selectRoom"));
        
        testClassCashPayment.setIncludedMethods(methodsCashPayment);
        
        testClassesCashPayment.add(testClassCashPayment);
        
        bookingTestCashPayment.setXmlClasses(testClassesCashPayment);
        
  /*-------------------------------------------Second class  --------------------------------------------------*/
        
       testClassCashPayment = new XmlClass("testcases.PaymentManager");
		
	    methodsCashPayment  = new ArrayList<XmlInclude>();
	   
	    methodsCashPayment.add(new XmlInclude("makeCashPayment"));
		
        
        testClassCashPayment.setIncludedMethods(methodsCashPayment);
        
        testClassesCashPayment.add(testClassCashPayment);
        
        bookingTestCashPayment.setXmlClasses(testClassesCashPayment);
        
        testng.run();



		
		
		
		
		
  }

}
