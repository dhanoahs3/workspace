package com.soapuitutorial.script;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.SoapUI;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.report.JUnitReport;

import com.soapuitutorial.util.Xls_Reader;
import com.soapuitutorial.util.ZipAndMail;
import com.soapuitutorial.util.XLUtil;

public class ProjectTestRunner {
	
	public static void main(String[]args) throws Exception{
		
		//Driver soapAppp = new Driver("C:\\testing\\Soap projects\\Retail-App-SOAP-Protocol-soapui-project.xml");
        
		Xls_Reader xls = new Xls_Reader(Constants.PROJECT_PATH+"\\Test Data.xlsx");
		
		Driver soapApp  = new Driver(Constants.PROJECT_XML_PATH);
		
		String resultFilePathFolder = Constants.PROJECT_PATH+"\\SoapReporting\\xlsreports\\";
		
		
		
		
		List<TestSuite> allTestSuite = soapApp.getAllTestSuites();
		JUnitReport report  = null;
		
		String testSuiteName = null;
		String testCaseName  = null;
		List<TestCase> allTestCases = null;
		TestCaseRunner tcRunner = null;
		
		
	    HashMap<String,String> testProperties = new HashMap<String,String>();

		//prepare the result sheet 
	    
	XLUtil.prepareResultSheet("C:\\testing\\Soap_Protocol\\SoapReporting\\Test Data.xlsx",resultFilePathFolder);
		
		
		double startTime=0;
		double endTime=0;

		String result = null;
		for(int tsId=0; tsId<allTestSuite.size();tsId++){
			
			testSuiteName  =  allTestSuite.get(tsId).getName();
			report = new JUnitReport();
			report.setTestSuiteName(testSuiteName);
            allTestCases  = soapApp.getAllTestCases(testSuiteName);

			
			System.out.println("*************************"+testSuiteName+"******************");
			
			for(int tcId=0;tcId<allTestCases.size();tcId++){
			
				testCaseName = allTestCases.get(tcId).getName();
				
				List<PropertiesMap> testDataSets = XLUtil.getTestData(testSuiteName, testCaseName, xls);
				

				if(XLUtil.isExectuable(testCaseName, xls,"TestCases")){
 				
				for(int tRepeat = 0;tRepeat<testDataSets.size();tRepeat++)
				
				
				{
					
				if(testDataSets.get(tRepeat).get("Runmode").equals("Y")){
					startTime = getCurrentTime();

				//Then we can run the test case as well by doing the following.
				tcRunner  = allTestCases.get(tcId).run(testDataSets.get(tRepeat),false);
				System.out.println("---->>");
     System.out.println(testCaseName + "----"+ tcRunner.getStatus() + "-----" + allTestCases.get(tcId).getPropertyValue("result"));
    				
                 testDataSets.get(tRepeat).put("Result",allTestCases.get(tcId).getPropertyValue("result"));
                 
                 endTime =  getCurrentTime();

                result = allTestCases.get(tcId).getPropertyValue("result");
					
                    if(result.equalsIgnoreCase("Pass")){
						report.addTestCase(testCaseName+"(Iteration - "+(tRepeat+1)+")" , (endTime-startTime),testProperties);
					}else{
						report.addTestCaseWithFailure(testCaseName+"(Iteration - "+(tRepeat+1)+")",(endTime-startTime), result,null,testProperties);
					}

			}
				
				else{
					
					System.out.println("--??>>");

					System.out.println(testCaseName + " skipped");
					testDataSets.get(tRepeat).put("Result","skipped");
					report.addTestCase(testCaseName+"(Iteration - "+(tRepeat+1)+")" , (endTime-startTime)/1000,testProperties);

					
				}
				

			}
		}
				else{
					report.addTestCase(testCaseName , (endTime-startTime)/1000,testProperties);
					for(int i=0;i<testDataSets.size();i++){
						testDataSets.get(i).put("Result","skipped");

						}
}
				
				XLUtil.generateReport(testSuiteName,allTestCases.get(tcId).getName(),testDataSets);

			}
			
			
			
				report.save(new File("C:\\testing\\Soap reporting\\"+"TEST-"+testSuiteName+"-Results.xml"));
			    report.finishReport();
		}
		
		XLUtil.reportXls.close();
	//	ZipAndMail.zipAndMail();;
		System.exit(0);		
		 
  }
	
	public static long getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis(); // ms since 1970
	}

 
}	


