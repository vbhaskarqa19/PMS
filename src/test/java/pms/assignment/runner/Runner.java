package pms.assignment.runner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import pms.assignment.factory.DriverFactory;
import pms.assignment.util.*;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;





@RunWith(Cucumber.class)
@CucumberOptions(

		features = {

				"classpath:featureFiles/AmazonUITest.feature",			
		}, 
		tags = { "~@Registration"},
		glue = { "pms.assignment.stepDefinition" },
		dryRun=false,
		plugin = { "pms.assignment.util.CustomFormatter"}    //,"pretty"//, "json:target/Cucumber.json"} // "com.cucumber.listener.ExtentCucumberFormatter:", 
	)


public class Runner {
	
	private static final Logger log = Logger.getLogger(Runner.class.getName());
	
	@BeforeClass
	public static void setUp() {
	
		log.info("Before class started");
		Date d = new Date();
		String today = new SimpleDateFormat(Constants.SCREENSHOT_SDF).format(d);
		String reportName = String.format("Report_%s%s", today, Constants.HTML_EXTENSION);
		
		File dir = new File(today);
		dir = new File(Constants.REPORT_PATH + dir);

		if (!dir.exists()) {
			dir.mkdir();
			Variables.reportFolderName = dir;
		}

		File reportPath = new File(dir + "/" + reportName);
//		File folderPath = new File(dir + "/");

		CustomFormatter.initiateCustomFormatter(reportPath);
		File extentConfig = new File(Constants.CONFIG_FILES_URI + Constants.EXTENT_FILE);
		CustomFormatter.loadConfig(extentConfig);
		CustomFormatter.addSystemInfo("user", System.getProperty("user.name"));
		CustomFormatter.addSystemInfo("os", System.getProperty("os.name"));
		
	}
	
	@AfterClass
	public static void tearDown() {
		DriverFactory.closeDriver();
		
	}

}

