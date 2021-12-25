package com.soapuitutorial.script;

import java.io.IOException;
import java.util.List;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.support.SoapUIException;

public class Driver {

	
	WsdlProject project = null;
	
	public Driver(String projectXmlPath){
		try {
			project = new WsdlProject(projectXmlPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//get all Test suites inside project

	public List<TestSuite> getAllTestSuites(){
		 return project.getTestSuiteList();
	}
	
	//get all Test cases inside a particular test suite
	public List<TestCase> getAllTestCases(String testSuiteName){
		TestSuite testSuite  =  project.getTestSuiteByName(testSuiteName);
		return testSuite.getTestCaseList();
	}
	
	
	
	
}

