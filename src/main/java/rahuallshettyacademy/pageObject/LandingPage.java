package rahuallshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	//for Driver we have to create a paramerterized constuctor: this will be called at the time of object creation
	WebDriver driver;
	
	public LandingPage(WebDriver driver)

	{  //this.driver is a local class variable 
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail= driver.findElement(By.id("userEmail"));

	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement Userpassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//Actions method
	
	public Productcatelogue loginApp(String email,String password)
	{
		userEmail.sendKeys(email);
		Userpassword.sendKeys(password);
		submit.click();
		//creating object of the productcatelogue class here only so no need to create in main class
		Productcatelogue productcalte=new Productcatelogue(driver);
		return productcalte;
	}
	
	
	//get me the error message
	
	public String getErrorMessage(){
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	//method to go to landing page
	
	public void goTo(String Url)
	{
		driver.get(Url);
	}
	
}