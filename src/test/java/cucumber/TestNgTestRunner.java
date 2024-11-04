package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\cucumber\\",glue = "rahulShettyacademy.stepDefinition",monochrome =  
true ,tags = "@ErrorValidation",plugin = {"html:target\\Cucumber.html"})

public class TestNgTestRunner extends AbstractTestNGCucumberTests{

}
