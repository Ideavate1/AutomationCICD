package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahuallshettyacademy.pageObject.Cartpage;
import rahuallshettyacademy.pageObject.OrderPage;

public class AbstractComponents {

	// parent class to reuse all code
	// this is called By locator "By.cssSelector(".mb-3")"

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf( findBy));
	}
	
	

	public Cartpage goToCartPage() {
		// click on cart
		cartHeader.click();
		return new Cartpage(driver);
	}
	

	public OrderPage goToOrdersPage() {
		// click on cart
		orderHeader.click();
		return new OrderPage(driver);
	}

	public void waitForElementToDisappear(By findBy) throws InterruptedException {
		// if below code is taking more time then use Thread.sleep(2); instead of below
		// code
		
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

}
