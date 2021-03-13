package pms.assignment.factory;


import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import pms.assignment.util.Constants;
import pms.assignment.util.LoadProperties;



public class DriverFactory {

	private static WebDriver driver = null;
	private static Properties CONFIG = LoadProperties.getConfig(Constants.CONFIG_PROPERTY);

	public static WebDriver getDriver() {

		if (driver == null) {
			String browser = CONFIG.getProperty("browser");
			DesiredCapabilities dr=null;

			String geckoDriverPath = Constants.DRIVERS_URI + CONFIG.getProperty("gecko.driver.path");
			String chromeDriverPath = Constants.DRIVERS_URI + CONFIG.getProperty("chrome.driver.path");
			String EdgeDriverPath = Constants.DRIVERS_URI + CONFIG.getProperty("Edge.driver.path");

			if (browser.equalsIgnoreCase(Constants.BROWSER_FF)) {
				System.setProperty("webdriver.gecko.driver", geckoDriverPath);
//				FirefoxOptions options = new FirefoxOptions();
//				options.addPreference("-enablePassThrough", true);
			    dr=DesiredCapabilities.firefox();
				dr.setBrowserName("firefox");
				driver=new FirefoxDriver(dr);
			
			} else if (browser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {

				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//				String downloadFilepath = Constants.Download_PATH;

				ChromeOptions options = new ChromeOptions();
//				options.addArguments("--test-type");
//				options.addArguments("disable-infobars");
//				options.addArguments("--headless");

//				HashMap<String, Object> prefs = new HashMap<String, Object>();
//				prefs.put("download.default_directory", downloadFilepath);
//				prefs.put("plugins.always_open_pdf_externally", true);
//				prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
//
//				prefs.put("download.prompt_for_download", false);

//				options.setExperimentalOption("prefs", prefs);

				dr = DesiredCapabilities.chrome();

				dr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				dr.setCapability(ChromeOptions.CAPABILITY, options);

		    	driver = new ChromeDriver(dr);
//				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dr);
				
			} else if (browser.equalsIgnoreCase(Constants.BROWSER_IE)) {
				System.setProperty("webdriver.edge.driver", EdgeDriverPath);
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase(Constants.BROWSER_SAFARI)) {
//				driver = new SafariDriver();
				 dr=DesiredCapabilities.safari();
					
			        dr.setBrowserName("safari");
	             driver=new EdgeDriver(dr);
			}

			driver.manage().window().maximize();
//			Dimension dim = new Dimension(1366, 768);
//			driver.manage().window().setSize(dim);
			driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();

		}
		return driver;
	}
	
	public static void closeDriver() {
		driver.quit();
	}
}
