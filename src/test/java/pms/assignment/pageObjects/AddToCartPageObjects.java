package pms.assignment.pageObjects;



import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class AddToCartPageObjects extends BasePage {

	
	

	public AddToCartPageObjects() {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "twotabsearchtextbox")
	private WebElement SearchButton;
	

	public void searchForRequiredProduct() {
		typeIntoElement(SearchButton, CONFIG.getProperty("itemToBeSearched") + Keys.ENTER);
		wait.waitForPageToLoad();

	}

	public void clickOnRequiredProduct() {

		String xpath = String.format("//span[contains(text(),'%s')]/parent::a", CONFIG.getProperty("itemToBeSearched"));
		log.info("xpath for product is "+ xpath);
		WebElement RequiredProduct = webElementHelper.getWebElementByXpath(xpath);
		scrollIntoView(RequiredProduct);
		clickOnElement(RequiredProduct);
		switchToChildWindow();
		wait.waitForPageToLoad();
	}

	@FindBy(xpath = "//*[@value='Add to Cart']")
	private WebElement AddToCart;

	public void addToCart() {
		clickOnElement(AddToCart);
	}

}
