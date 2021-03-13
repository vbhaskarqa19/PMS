package pms.assignment.stepDefinition;



import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pms.assignment.pageObjects.AccountRegistrationPageObjects;


public class AccountRegistrationStepDefinition {

	
	
	private AccountRegistrationPageObjects arpo;	
	public AccountRegistrationStepDefinition() {
		arpo=new AccountRegistrationPageObjects();
	}
	
	@Given("^The web application is open$")
	public void the_web_application_is_open() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		arpo.gotoURL();
	}

	@Given("^I am on the registration page of the amazon application$")
	public void i_am_on_the_registration_page_of_the_amazon_application() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		arpo.clickOnCreateAccountButton();
	}

	@When("^I enter user name$")
	public void i_enter_user_name() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   arpo.enterUserName();
	}

	@When("^I enter password$")
	public void i_enter_password() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   arpo.enterPassword(); 
	}

	@Given("^I am on login page of the amazon application$")
	public void i_am_on_login_page_of_the_amazon_application() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   
	}
	
	@When("^I enter mobile number$")
	public void i_enter_mobile_number() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    arpo.enterMobileNumber();
	}

	@When("^I click on continue$")
	public void i_click_on_continue() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    arpo.clickOnContinue();
	}
	
	@Given("^I click on sign in on home page$")
	public void i_click_on_sign_in_on_home_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   arpo.clickOnSignInButton();
	}
	
	@When("^I enter mobile number on sign in page$")
	public void i_enter_mobile_numbe_on_sign_in_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    arpo.enterMobileNumberOnSignIn();
	}

	@Given("^I click on sign in$")
	public void i_click_on_sign_in() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   arpo.clickOnSignInSubmitButton();
	   
	}


}
