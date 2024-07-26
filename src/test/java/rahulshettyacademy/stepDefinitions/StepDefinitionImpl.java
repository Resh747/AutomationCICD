package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.SeleniumFrameworkDesign.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.confirmationPage;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingpage;
	public ProductCatalogue productCataloguePage;
	public confirmationPage Confirmationpage;
   @Given("I landed on Ecommerce page")
   public void I_landed_on_Ecommerce_page() throws IOException  {
	   //code
	   landingpage=  launchApplication();
   }
   
   @Given("^Logged in with username (.+) and password (.+)$")
   public void login_in_username_password(String username,String password){
	    productCataloguePage=	landingPage.loginApplication(username, password);
   }
   
   @When("^I add the product (.+) to cart$")
   public void i_add_the_product_to_cart(String productName) throws InterruptedException {
	   List<WebElement> products=productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(productName);
   }
   
   @And("checkout <productName> and submit the order")
   public void checkout_productname_and_submit_the_order(String productName) {
	   CartPage cartPage=productCataloguePage.goToCartPage();
		
		
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(true);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		
		checkoutPage.SelectCountry("India");
		 Confirmationpage =checkoutPage.SubmitOrder();
   }
   
   @Then("{string} message is displayed on Confirmation page")
   public void verify_message(String string){
	   String confirmMessage=Confirmationpage.ConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
   }
   
   @Then("{string} message is displayed$")
   public void something_message_is_displayed(String string){
	   Assert.assertEquals(string, landingPage.getErrorMessage());
   }
}
