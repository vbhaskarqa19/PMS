package pms.assignment.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementHelper {
	
	private static WebDriver driver;
	
	
	public WebElementHelper(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement getWebElementByXpath(String xpath) {
		
		return driver.findElement(By.xpath(xpath));
	}

}
