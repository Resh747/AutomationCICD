package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="input[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By resultvisible=By.cssSelector(".ta-results");
	

	
public void SelectCountry(String CountryName) {
	Actions a=new Actions(driver);
	a.sendKeys(Country, CountryName).build().perform();
	waitElementToAppear(resultvisible);
	selectCountry.click();
	
	
}

public confirmationPage SubmitOrder() {
	submit.click();
	
	return new confirmationPage(driver);
}


}
