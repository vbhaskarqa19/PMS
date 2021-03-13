package pms.assignment.pageObjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.*;

import pms.assignment.factory.DriverFactory;

import pms.assignment.util.Constants;
import pms.assignment.util.LoadProperties;
import pms.assignment.util.WaitHelper;
import pms.assignment.util.WebElementHelper;

public class BasePage {

	protected static WebDriver driver;
	protected static WaitHelper wait;
	protected static WebElementHelper webElementHelper;
	JavascriptExecutor js;
	protected static String parent;
	// protected static String child_window;
	protected static Properties CONFIG = LoadProperties.getConfig(Constants.CONFIG_PROPERTY);
	protected static final Logger log = Logger.getLogger(Object.class.getName());
//	static String screenshotName;

	public BasePage() {

		driver = DriverFactory.getDriver();
		wait = new WaitHelper(driver);
		webElementHelper= new WebElementHelper(driver);
		js = (JavascriptExecutor) driver;
	}

	public void gotoURL() {
		driver.get(CONFIG.getProperty("application.url"));
		wait.waitForPageToLoad();

	}

	public void clickOnElement(WebElement element) {
		wait.WaitForElementEnabled(element);
		element.click();

	}

	public void typeIntoElement(WebElement element, String text) {
		// wait.WaitForElementEnabled(element);
		element.sendKeys(text);
	}

	public void scrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void switchToChildWindow() {

		parent = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();

		while (iterator.hasNext()) {
			String child_window = iterator.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
			}
		}
	}

	public void switchToParentWindow() {
		driver.switchTo().window(parent);
	}
	
	public static byte[] takeScreenShot() {

		Date d = new Date();
		String today = new SimpleDateFormat(Constants.SCREENSHOT_SDF).format(d);
//		screenshotName = String.format("Image_%s%s", today, Constants.IMAGE_EXTENSION);

		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		return screenshot;
	}
	


}
