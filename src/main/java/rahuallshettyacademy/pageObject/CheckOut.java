package rahuallshettyacademy.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckOut extends AbstractComponents {

	WebDriver driver;

	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
//adding encapsulation via using "private" access modifier
	
	@FindBy(css = "[placeholder='Select Country']")
private	WebElement selectIndia;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	private WebElement clickIndia;

	@FindBy(css = ".action__submit")
	private 	WebElement SubmitButton;

	

	private By loadFilter = By.cssSelector(".ta-results");

	// Select country
	public void selectCountry(String CountryName) {
		Actions a = new Actions(driver);
		a.sendKeys(selectIndia, CountryName).build().perform();
		// wait to load the filter of select country
		waitForElementToAppear(loadFilter);
		// click on India
		clickIndia.click();
	}

	public ConfirmationPage placeOrder() { // place order by click on button
		SubmitButton.click();

		return new ConfirmationPage(driver);
	}

}
