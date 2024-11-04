package rahuallshettyacademy.Test;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahuallshettyacademy.pageObject.Cartpage;
import rahuallshettyacademy.pageObject.CheckOut;
import rahuallshettyacademy.pageObject.ConfirmationPage;
import rahuallshettyacademy.pageObject.LandingPage;
import rahuallshettyacademy.pageObject.OrderPage;
import rahuallshettyacademy.pageObject.Productcatelogue;
import rahulShettyacademy.TestComponents.BaseTest;

public class Standalone2 extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test (dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
	
	
		String countryname ="India";
		//launch application and go to landing page via @beforeMethod code inside the BaseTest.java
		
		// create object of Landing]page class
		Productcatelogue productcalte = landing.loginApp(input.get("email"), input.get("password"));
		List<WebElement> products = productcalte.getProductList();
		productcalte.addProductToCart(input.get("productName"));
		Cartpage cartpage = productcalte.goToCartPage();
		Boolean match = cartpage.verifyProductsDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOut checkout = cartpage.ClickOnCheckOutButton();
		checkout.selectCountry(countryname);
		ConfirmationPage confimationpage=checkout.placeOrder();
		String conmfirmMessage=confimationpage.getConfirmationText();
		// check text for validation
		Assert.assertTrue(conmfirmMessage.equalsIgnoreCase("Thankyou for the order."));

	

}
	
	//To verify ZARA COAT 3 is displaying in order page
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest()
	{
		//ZARA COAT 3
		Productcatelogue productcalte = landing.loginApp("Ideavate@gmail.com", "Ideavate@123");
	OrderPage orderPage=	productcalte.goToOrdersPage();
Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	

	
	
//	@DataProvider
//	public Object[][] getData()
//	{  //running test with 2 differeent data sets
//		return new Object[][] {{"Ideavate@gmail.com","Ideavate@123","ZARA COAT 3"},{"Ideavate2@gmail.com","IamKing@000","ADIDAS ORIGINAL"}};
//	}
	
	//above data provider method is very long to use like we have to send email, pass, product in method argument if argument increased then above code is not looks code then we use hash Map
	//Hash Map 
	
	@DataProvider
	public Object[][] getData() throws IOException
{  //running test with 2 differeent data sets
		
		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "Ideavate@gmail.com");
//		map.put("password", "Ideavate@123");
//		map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "Ideavate2@gmail.com");
//	map1.put("password", "IamKing@000");
//	map1.put("productName", "ADIDAS ORIGINAL");
//		return new Object[][] {{map},{map1}};
		
//	List<HashMap<String,String>> data= 	getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ralhulshettyacademy//Data//PuurchaseOrder.Json");
		List<HashMap<String,String> > data = getJsonDataToMap("C:\\Users\\nikhil\\eclipse-workspace\\SeleniumFramework\\src\\test\\java\\ralhulshettyacademy\\Data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
