package pms.assignment.pageObjects;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pms.assignment.util.Constants;
import pms.assignment.util.LoadProperties;

public class AccountRegistrationPageObjects extends BasePage {

	private static Properties CONFIG = LoadProperties.getConfig(Constants.CONFIG_PROPERTY);
	
	public AccountRegistrationPageObjects() {
		super();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//*[contains(text(),'Hello, Sign in')]/ancestor::a") 
	private WebElement HelloSignInBtn;
	
	@FindBy (xpath="//*[contains(text(),'Sign in')]/ancestor::a")
	private WebElement SignInBtn;
	
	public void clickOnSignInButton() {
		clickOnElement(SignInBtn);
		wait.waitForPageToLoad();
	}
	
	@FindBy (id="createAccountSubmit")
	private WebElement CreateAccountBtn;
	
	public void clickOnCreateAccountButton() {
		clickOnElement(HelloSignInBtn);
		clickOnElement(SignInBtn);
		wait.waitForPageToLoad();
		clickOnElement(CreateAccountBtn);
	}
	
	@FindBy (xpath="//input[@name='customerName']")
	private WebElement CustomerName;
	
	public void enterUserName() {
		typeIntoElement(CustomerName, CONFIG.getProperty("username"));
	}
	
	@FindBy (xpath="//input[@type='tel']")
	private WebElement Telephone;
	
	public void enterMobileNumber() {
		typeIntoElement(Telephone, CONFIG.getProperty("mobile"));
	}
	
	@FindBy (xpath="//input[@type='email']")
	private WebElement email;
	
	public void enterMobileNumberOnSignIn() {
		typeIntoElement(email, CONFIG.getProperty("mobile"));
	}
	
	@FindBy (xpath="//input[@type='password']")
	private WebElement password;
	
	public void enterPassword() {
		typeIntoElement(password, CONFIG.getProperty("password"));
	}
	
	@FindBy (id="continue")
	private WebElement Continue;
	
	public void clickOnContinue() {
		clickOnElement(Continue);
	}
	
	@FindBy (id="signInSubmit")
	private WebElement SignInSubmit;
	
	public void clickOnSignInSubmitButton() {
		clickOnElement(SignInSubmit);
		wait.waitForPageToLoad();
	}
	
}
