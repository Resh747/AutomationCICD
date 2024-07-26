package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".cartSection h3")
	List<WebElement> CartProductName;
	
	@FindBy(css=".totalRow button")
	WebElement CheckoutButton;
	
	public Boolean VerifyProductDisplay(String productName) {
		

		Boolean match=CartProductName.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		CheckoutButton.click();
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		return checkoutPage;
	}
	

	

	
}
