package pms.assignment.util;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;



public class WaitHelper {
	
	private static Logger log=Logger.getLogger(WaitHelper.class.getName());
	 WebDriverWait wait;
	 FluentWait<WebDriver> fluentWait;
	 JavascriptExecutor js;
	 
	
	public WaitHelper(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);	
		js= (JavascriptExecutor) driver;
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.pollingEvery(250, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(2, TimeUnit.MINUTES);
		fluentWait.ignoring(NoSuchElementException.class);
	}
	
	public void WaitForElementVisible(WebElement element) {
		log.info("Waiting for the element to be visible : " + element.toString());
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public void WaitForElementEnabled(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForTextToBePresentInElement(WebElement element, String text) {
		wait.until(ExpectedConditions.or(ExpectedConditions.textToBePresentInElement(element, text),
				ExpectedConditions.textToBePresentInElementValue(element, text)));
	}
	
	public void waitForPageToLoad() {
				
		fluentWait.until(new Predicate<WebDriver>() {
			
			@Override
			public boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return js.executeScript("return document.readyState").equals("complete");
			}
		});
		
	}
}
