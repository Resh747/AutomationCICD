package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	//Page Factory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public ProductCatalogue loginApplication(String Email,String Password1) {
		userEmail.sendKeys(Email);
		Password.sendKeys(Password1);
		submit.click();
		ProductCatalogue productCataloguePage=new ProductCatalogue(driver);
		return productCataloguePage;
	}
	
	public String getErrorMessage() {
		waitWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
}
