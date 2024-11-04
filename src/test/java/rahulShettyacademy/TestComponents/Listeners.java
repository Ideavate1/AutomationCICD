package rahulShettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahualshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	
	ExtentReports extent= ExtentReporterNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//thread safe

	
	
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id (get for each class)->Test (map with test)
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		//test.log(Status.PASS, "Test Passed");
		//to get the set extentTest intead of test. we have to write extentTest.get()
		extentTest.get().log(Status.PASS, "Test passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		//if fail then throw error message
		//to get the set extentTest intead of test. we have to write extentTest.get()
				extentTest.get().fail(result.getThrowable());
		//if fail then 1. take screenshot 2. attached to report
		//how to get life to the "driver"
		
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	String filePath = null;
	try {
		filePath = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	

}
