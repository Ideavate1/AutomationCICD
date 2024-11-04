package rahulShettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahuallshettyacademy.pageObject.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landing;

	public WebDriver initializeChromeDriver() throws IOException {
		// Properties class read the global properties
		Properties prop = new Properties();
		/// file needs to be convert in InputStream
		// FileInputStream fis = new
		/// FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahualshettyacademy\\resources\\GlobalData.propertie");
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\nikhil\\eclipse-workspace\\SeleniumFramework\\src\\main\\java\\rahualshettyacademy\\resources\\GlobalData.properties");
		// loading the global file here
		prop.load(fis);
		//taking browser name from maven commnad if yes then execute code on that maven provided browser if not then execute at code level below is the ternary operator
		
		
		String BrowserName = System.getProperty("browser")!=null ? System.getProperty("browser") :  prop.getProperty("browser");

		if (BrowserName.contains("chrome")) {
			
			//headless chrome
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (BrowserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //optional
//
//			//head chrome
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();

		}

		else if (BrowserName.equalsIgnoreCase("firefox")) {
			// firefox
		}

		else if (BrowserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

	// run below method once standalone2.java will run
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeChromeDriver();
		landing = new LandingPage(driver);
		landing.goTo("https://rahulshettyacademy.com/client");
		return landing;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	// screeenshot code
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(Source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// Java one method FileUtils pkg
		// readJson to string

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// convert this string to hashmap
		// jackson databind library from maven repository

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

}
