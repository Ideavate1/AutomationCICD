package rahuallshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	// for Driver we have to create a paramerterized constuctor: this will be called
	// at the time of object creation
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);

		// this.driver is a local class variable
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// validate item is successfully added in the cart
	// gathering all the items
//	List<WebElement> cartProducts= driver.findElements(By.cssSelector(""));

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderProducts;



	// Action method

	// matching the cart product name with the actual product name that we have
	// added in the cart
	public boolean verifyOrderDisplay(String ProductName) {
		Boolean match = orderProducts.stream()
				.anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}



}
