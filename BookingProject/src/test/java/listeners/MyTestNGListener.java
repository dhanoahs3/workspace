package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class MyTestNGListener implements ITestListener{

	public void onTestFailure(ITestResult result) {
		System.out.println("***************************Test Failed"+result.getName());
		/*System.out.println(result.getThrowable().getMessage());
		ExtentTest test = (ExtentTest)result.getTestContext().getAttribute("test");
		//Reporter.getCurrentTestResult().getTestContext().setAttribute("criticalFailure", "Y");
		test.log(Status.FAIL, result.getThrowable().getMessage());*/
	}
	
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("***************************Test success "+result.getName());
		}
	
	public void onTestSkipped(ITestResult result) {
		
		System.out.println("***************************Test Skipped "+result.getName());

	
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
