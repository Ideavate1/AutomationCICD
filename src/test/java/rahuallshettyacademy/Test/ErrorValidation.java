package rahuallshettyacademy.Test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahuallshettyacademy.pageObject.Cartpage;
import rahuallshettyacademy.pageObject.CheckOut;
import rahuallshettyacademy.pageObject.ConfirmationPage;
import rahuallshettyacademy.pageObject.LandingPage;
import rahuallshettyacademy.pageObject.Productcatelogue;
import rahulShettyacademy.TestComponents.BaseTest;
import rahulShettyacademy.TestComponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		String countryname = "India";
		// launch application and go to landing page via @beforeMethod code inside the
		// BaseTest.java

		// create object of Landing]page class
		Productcatelogue productcalte = landing.loginApp("abcd@gmail.com", "Idvate@123");
		Assert.assertEquals("Incorrect email or password.", landing.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		String countryname = "India";
		// launch application and go to landing page via @beforeMethod code inside the
		// BaseTest.java

		// create object of Landing]page class
		Productcatelogue productcalte = landing.loginApp("Ideavate@gmail.com", "Ideavate@123");
		List<WebElement> products = productcalte.getProductList();
		productcalte.addProductToCart(productName);
		Cartpage cartpage = productcalte.goToCartPage();
		Boolean match = cartpage.verifyProductsDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}