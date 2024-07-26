package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent{
	
	WebDriver driver;
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".tr td:nth-child(3)")
	List<WebElement> OrderProductName;
	
	
	
	public Boolean VerifyOrderDisplay(String productName) {
		

		Boolean match=OrderProductName.stream().anyMatch(product->product.getText().equalsIgnoreCase("ZARA COAT 3"));
		//System.out.println(match);
		return match;
		
	}
	
	
	
}
