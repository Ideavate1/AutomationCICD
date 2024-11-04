package rahulShettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry  implements IRetryAnalyzer{
	
	int count=0;
	int maxTry=1;

			

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		//whenever test case failed it will come here after Listners onTestfailure .
		//it will come here and check and ask do I need to re-run and how many times?
		
		if(count<maxTry)
		{
			count++;
			return true;
		}
		
		return false;
	}

}
