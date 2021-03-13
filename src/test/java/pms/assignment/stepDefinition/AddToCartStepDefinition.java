package pms.assignment.stepDefinition;

import cucumber.api.java.en.When;
import pms.assignment.pageObjects.AddToCartPageObjects;

public class AddToCartStepDefinition {
	
	private AddToCartPageObjects acpo;
	
	public AddToCartStepDefinition() {
		acpo=new AddToCartPageObjects();
	}
	
	@When("^I search for required product$")
	public void i_search_for_required_product() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   acpo.searchForRequiredProduct();
	}

	@When("^I click on required product$")
	public void i_click_on_required_product() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   acpo.clickOnRequiredProduct();
	}

	@When("^I add required product to cart$")
	public void i_add_required_product_to_cart() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   acpo.addToCart();
	}

}
