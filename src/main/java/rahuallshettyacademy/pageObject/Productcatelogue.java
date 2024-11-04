 package rahuallshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class Productcatelogue extends AbstractComponents {
	
	//for Driver we have to create a paramerterized constuctor: this will be called at the time of object creation
	

	
	
	
	WebDriver driver;
	
	public Productcatelogue(WebDriver driver)
	{   
		super(driver);
		
		//this.driver is a local class variable 
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By Toastmsg=By.cssSelector("#toast-container");
	By loadingIcon=By.cssSelector(".ng-animating");
 
	//Action method
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	
	public WebElement getProductByNames(String ProductName)
	{
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prod;
	}
	
	//adding product to the cart
	
	public void addProductToCart(String ProductName) throws InterruptedException
	{

		WebElement prod= getProductByNames(ProductName);
		prod.findElement(addToCart).click();
		
//explicit wait untill toast message is showing up
  waitForElementToAppear(Toastmsg);
//loding icon class name = "ng-animating"//taking time 
  waitForElementToDisappear(loadingIcon);
//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("ng-animating"))));
  	
		
	}

	
	
}
	
