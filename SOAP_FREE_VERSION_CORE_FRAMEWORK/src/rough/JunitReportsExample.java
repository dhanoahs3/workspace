package rough;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.eviware.soapui.report.JUnitReport;

public class JunitReportsExample {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		JUnitReport report = new JUnitReport();
		double x= getCurrentTime();
		Thread.sleep(5000);
		double y = getCurrentTime();
		report.setTestSuiteName("SuiteA");
		HashMap <String,String> testProperties =  new HashMap<String,String>();
		report.addTestCase("A", (y-x)/1000, testProperties);
		report.addTestCaseWithError("B", 1369627187.0, "Some error", null,testProperties);
		report.addTestCaseWithFailure("C", 1369627187.0, "Some failure", null,testProperties);
		
		report.save(new File("C:\\testing\\Soap reporting\\TEST-ResultsA.xml"));
		report.finishReport();
		
		// new Suite
		report = new JUnitReport();
		
		report.setTestSuiteName("SuiteB");
		report.addTestCase("B", 1369627187.0, testProperties);
		report.save(new File("C:\\testing\\Soap reporting\\TEST-ResultsB.xml"));
		report.finishReport();
		
	  //  new Suite
		report = new JUnitReport();
		report.setTestSuiteName("SuiteC");
		report.addTestCase("C", 1369627187.0,testProperties);
		report.save(new File("C:\\testing\\Soap reporting\\TEST-ResultsC.xml"));
		report.finishReport();
		
		
	

	}
	
	public static long getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis(); // ms since 1970
	}

}
