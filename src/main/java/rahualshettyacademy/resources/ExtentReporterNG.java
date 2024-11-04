package rahualshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
 public static ExtentReports getReporterObject()
	
	{
		//ExtentReport, ExtentSparkReporter thees are 2 classes
		//ExtentSparkReporter= responsible for creating the report 
		//for thet we have to give path 
		String Path= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(Path);
		//set configuration with above object
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Results");
		
		//create object of other class , this is main 
		ExtentReports  extent=new ExtentReports();
		 extent.attachReporter(reporter);
		 extent.setSystemInfo("Tester", "Prachi Parmar");
		 return extent;
	}

}
