package rahulshettyacademy.SeleniumFrameworkDesign;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import rahulshettyacademy.SeleniumFrameworkDesign.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderHistoryPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.confirmationPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest{
	//String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
        
        String CountryName="India";
		
		
		ProductCatalogue productCataloguePage=	landingPage.loginApplication(input.get("Email"), input.get("Password"));
		
		List<WebElement> products=productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(input.get("productName"));
		CartPage cartPage=productCataloguePage.goToCartPage();
		
		
		Boolean match=cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(true);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		
		checkoutPage.SelectCountry(CountryName);
		confirmationPage Confirmationpage =checkoutPage.SubmitOrder();
		
	
		
		String confirmMessage=Confirmationpage.ConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	// To verify ZARA COAT 3 is displaying in Orders Page
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCataloguePage=	landingPage.loginApplication("contact1@rahulshetty.com", "Mumbai@1234");
		OrderHistoryPage orderHistoryPage=productCataloguePage.goToOrderPage();
		Assert.assertTrue(orderHistoryPage.VerifyOrderDisplay("ZARA COAT 3"));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//HashMap<String, String> hashmap=new HashMap<String,String>();
		//hashmap.put("Email", "contact1@rahulshetty.com");
		//hashmap.put("Password", "Mumbai@1234");
		//hashmap.put("productName", "ZARA COAT 3");
		
		//HashMap<String,String> map=new HashMap<String,String>();
		//map.put("Email", "shetty@gmail.com");
		//map.put("Password", "Iamking@000");
	//	map.put("productName", "ADIDAS ORIGINAL");
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
