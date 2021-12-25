import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestProperty;
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.support.SoapUIException;

public class Runner {

	public static void main(String[] args) throws XmlException, IOException, SoapUIException {
		WsdlProject project = new WsdlProject("C:\\testing\\Soap projects\\Retail-App-SOAP-Protocol-soapui-project.xml");
		TestSuite suite = project.getTestSuiteByName("SessionId");
		TestCase test = suite.getTestCaseByName("LoginTest2");
		
		PropertiesMap map  = new PropertiesMap();
		map.put("username","user");
		map.put("password","whizdom");
		
		TestCaseRunner runner = test.run(map,false); //running the test case
		System.out.println(runner.getStatus());
		System.out.println(runner.getReason());
		
		System.out.println("=========================================================");
		
		List<TestStepResult> results  = runner.getResults();
		for(int i =0;i<results.size();i++){
			TestStepResult stepResult  = results.get(i);
			System.out.println(stepResult.getStatus());
		}
		
		
	System.out.println("After running tests "+test.getProperties().size());
	
	
	Map<String,TestProperty> prop = test.getProperties();
	Set<String> keys = prop.keySet();
	Iterator<String> iter = keys.iterator();
	
	while(iter.hasNext()){
		String key = iter.next();
		TestProperty p = prop.get(key);
		System.out.println(key + "-----------"+ p.getValue());
	}
	
	System.out.println("=========================================================");

	

		System.out.println("session  "+test.getPropertyValue("sessionid"));
		System.out.println("role  "+test.getPropertyValue("role"));
		System.out.println("loginstatus  "+test.getPropertyValue("loginstatus"));

		
	   
		

		
}

}
