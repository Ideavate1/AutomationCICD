package rahulShettyacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahuallshettyacademy.pageObject.Cartpage;
import rahuallshettyacademy.pageObject.CheckOut;
import rahuallshettyacademy.pageObject.ConfirmationPage;
import rahuallshettyacademy.pageObject.LandingPage;
import rahuallshettyacademy.pageObject.Productcatelogue;
import rahulShettyacademy.TestComponents.BaseTest;

public class StepDefinitionImple extends BaseTest {
	
	public LandingPage landingpage;
	public Productcatelogue productcalte ;
	private String countryname ="India";
	public ConfirmationPage confimationpage;
	public String conmfirmMessage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		//below method will return landingPage object so declear this globally
		landingpage=launchApplication();
	}
	// Given Logged in with username Ideavate@gmail.com and Password Ideavate@123 (.+) is regural expression
	@Given ("^Logged in with username (.+) and Password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productcalte = landing.loginApp(username,password);
	}

	
	//@When ("^add the product (.+) to cart$") 
	
	@When ("^I add the product (.+) to cart$") 
	public void i_add_product_to_cart(String productname) throws InterruptedException
	{
		List<WebElement> products = productcalte.getProductList();
		productcalte.addProductToCart(productname);
		
	}
	
	@When ("^Checkout (.+) and submit the order.$")
	public void checkout_and_sublit_the_order(String productname)
	{
		Cartpage cartpage = productcalte.goToCartPage();
		Boolean match = cartpage.verifyProductsDisplay(productname);
		Assert.assertTrue(match);
		CheckOut checkout = cartpage.ClickOnCheckOutButton();
		checkout.selectCountry(countryname);
		confimationpage=checkout.placeOrder();
	}
	
	
//	"Thankyou for the order." displayed on the confirmationPage 
	@Then("{string} displayed on the confirmationPage")
	public void message_displayed_on_confimationPage(String string)
	{
		 conmfirmMessage=confimationpage.getConfirmationText();
		// check text for validation
		Assert.assertTrue(conmfirmMessage.equalsIgnoreCase(string));

	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
public void incorrect_message_is_displayed(String message)
{
		Assert.assertEquals("Incorrect email or password.", landing.getErrorMessage());
		driver.close();
}



}



