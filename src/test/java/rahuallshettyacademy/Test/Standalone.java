package rahuallshettyacademy.Test;

import static org.testng.Assert.assertTrue;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahuallshettyacademy.pageObject.LandingPage;



public class Standalone {
	
	public static void main(String [] args)
	{
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		//create object of Landing]page class 
		LandingPage landing=new LandingPage(driver);
		//login
		driver.findElement(By.id("userEmail")).sendKeys("Ideavate@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ideavate@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//grabbing all products
		
List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
//filter the products
WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

//clicking on "add to cart"

prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
//explicit wait untill toast message is showing up

wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//loding icon class name = "ng-animating"//taking time 
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("ng-animating"))));

//click on cart
driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

//validate item is successfully added in the cart
//gathering all the items
List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
//matching the cart product name with the actual product name that we have added in the cart
Boolean match =cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));

Assert.assertTrue(match);

//click on checkout
driver.findElement(By.cssSelector(".totalRow button")).click();

//Select country
Actions a=new Actions(driver);
a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
//wait to load the filter of select country
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//click on India
driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//click on place order
driver.findElement(By.cssSelector(".action__submit")).click();
//check text for validation
String conmfirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
Assert.assertTrue(conmfirmMessage.equalsIgnoreCase("Thankyou for the order."));



	}

}
