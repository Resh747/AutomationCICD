package rahulshettyacademy.SeleniumFrameworkDesign;


import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.SeleniumFrameworkDesign.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.confirmationPage;

public class ErrorValidationTest extends BaseTest{

	@Test(groups= {"ErrorValidating"},retryAnalyzer=rahulshettyacademy.SeleniumFrameworkDesign.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	
		ProductCatalogue productCataloguePage=	landingPage.loginApplication("contact1@rahulshetty.com", "Mumba1234");
		//div[aria-label='Incorrect email or password.']
		//;
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
        String productName="ZARA COAT 3";
        String CountryName="India";
		
		
		ProductCatalogue productCataloguePage=	landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		
		List<WebElement> products=productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(productName);
		CartPage cartPage=productCataloguePage.goToCartPage();
		
		
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}
