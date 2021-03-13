package pms.assignment.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;
import pms.assignment.pageObjects.BasePage;

import java.util.logging.Logger;

public class CustomFormatter implements Formatter,Reporter {
	
	private static ExtentReports extent;
	private ExtentTest featureTest;
	private ExtentTest scenarioTest;
	private static int featureCount;
	private LinkedList<Step> testSteps = new LinkedList<Step>();
	
	
	private static final Logger log = Logger.getLogger(CustomFormatter.class.getName());
	
	
	public static ExtentReports getReportInstance() {
		return extent;
	}
	
	public static void initiateCustomFormatter(File filePath) {
		if (extent == null) {
			featureCount = 0;
			extent = new ExtentReports(filePath.getAbsolutePath(), true, DisplayOrder.OLDEST_FIRST);
			log.info("extent intiated");
		}
	}
	
   public static void loadConfig(File configFile) {
		extent.loadConfig(configFile);
		
	}
   
	public void result(Result result) {
		log.info("Result started");
		
		Step step = testSteps.poll();

		if ("passed".equals(result.getStatus())) {
			scenarioTest.log(LogStatus.PASS, step.getKeyword() + step.getName(), "PASSED");
		} else if ("failed".equals(result.getStatus())) {
			scenarioTest.log(LogStatus.FAIL, step.getKeyword() + step.getName(), result.getError());
			embedding("png", BasePage.takeScreenShot());
		} else if ("skipped".equals(result.getStatus())) {
			scenarioTest.log(LogStatus.SKIP, step.getKeyword() + step.getName(), "SKIPPED");
		} else if ("undefined".equals(result.getStatus())) {
			scenarioTest.log(LogStatus.UNKNOWN, step.getKeyword() + step.getName(), "UNDEFINED");
		}
	}

	@Override
	public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uri(String uri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feature(Feature feature) {
		// TODO Auto-generated method stub
		log.info("feature started");
		featureTest = extent.startTest("Feature: " + feature.getName());

		for (Tag tag : feature.getTags()) {
			featureTest.assignCategory(tag.getName());
		}
		featureCount++;
	}

	@Override
	public void scenarioOutline(ScenarioOutline scenarioOutline) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void examples(Examples examples) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startOfScenarioLifeCycle(Scenario scenario) {
		// TODO Auto-generated method stub
		log.info("Scenario started");
		scenarioTest=extent.startTest("Scenario :"+ scenario.getName());
		for (Tag tag : scenario.getTags()) {
			scenarioTest.assignCategory(tag.getName());
		}
		
	}

	@Override
	public void background(Background background) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scenario(Scenario scenario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step(Step step) {
		// TODO Auto-generated method stub
		testSteps.add(step);
		log.info("Step added " + step.getName());
	}

	@Override
	public void endOfScenarioLifeCycle(Scenario scenario) {
		// TODO Auto-generated method stub
		log.info("end of scenario");
		extent.endTest(scenarioTest);
		featureTest.appendChild(scenarioTest);
				
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eof() {
		// TODO Auto-generated method stub
		log.info("end of feature");
		extent.endTest(featureTest);
		extent.flush();
		featureCount--;
	}

	public static void addSystemInfo(String string, String property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void before(Match match, Result result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void after(Match match, Result result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void match(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void embedding(String mimeType, byte[] data) {
		// TODO Auto-generated method stub
		log.info("embedding started");
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.SCREENSHOT_SDF);
//		String extension = (String) MIME_TYPES_EXTENSIONS.get(s);
		String fileName = "Image-" + sdf.format(System.currentTimeMillis()) + "." + mimeType;

		try {
			File outputPth = new File(Variables.reportFolderName + "/ScreenShots");
			if (!outputPth.exists()) {
				outputPth.mkdir();
			}
			FileUtils.writeByteArrayToFile(new File(outputPth + "/" + fileName), data);
			scenarioTest.log(LogStatus.INFO, scenarioTest.addScreenCapture(outputPth + "/" + fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void write(String text) {
		// TODO Auto-generated method stub
		log.info(" scenario Write started");
		scenarioTest.log(LogStatus.INFO, text);
	}
	
	

}
