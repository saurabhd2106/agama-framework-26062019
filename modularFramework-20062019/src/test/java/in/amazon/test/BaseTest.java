package in.amazon.test;

import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonLibs.implementations.CommonDriver;
import commonLibs.implementations.ScreenshotControl;
import commonLibs.utils.ConfigFileReader;
import in.amazon.pages.Homepage;
import in.amazon.pages.Resultspage;

public class BaseTest {

	CommonDriver cmnDriver;
	String url;

	Homepage homepage;
	Resultspage resultPage;

	WebDriver driver;

	static String configFilename;
	static Properties configProperties;

	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReport;
	ExtentTest extentTest;

	static String htmlReportFilename;
	static String currentWorkingDirectory;
	static String testExecutionStartTime;

	static ScreenshotControl screenshotControl;
	static String screenshotName;

	static {
		htmlReportFilename = "AmazonTestReport";
		Date date = new Date();
		testExecutionStartTime = String.valueOf(date.getTime());
		currentWorkingDirectory = System.getProperty("user.dir");
		configFilename = String.format("%s/config/config.properties", currentWorkingDirectory);

	}

	@BeforeSuite
	public void preSetup() throws Exception {
		configProperties = ConfigFileReader.configPropertyFileReader(configFilename);
		setupReports();
	}

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {

		extentTest = extentReport.createTest("Set up - Setting up the pre requisites for test cases");

		invokeBrowserAndNavigateToBaseUrl();

		initializingScreenshotVariable();

		initializingPages();

	}

	@AfterMethod
	public void afterAMethod(ITestResult result) throws Exception {
		String testcaseName = result.getName();
		

		if (result.getStatus() == ITestResult.SUCCESS) {

			extentTest.pass("Test case passed : " + testcaseName);
		} else if (result.getStatus() == ITestResult.FAILURE) {

			extentTest.fail("Test case Failed : " + testcaseName);

			screenshotName = String.format("%s/screenshots/%s_%s.jpeg", currentWorkingDirectory, testcaseName,
					testExecutionStartTime);
			screenshotControl.saveAndCaptureScreenshot(screenshotName);
			extentTest.addScreenCaptureFromPath(screenshotName);

		} else {
			extentTest.skip("Test case skipped : " + testcaseName);
		}

	}

	@AfterClass(alwaysRun = true)
	public void cleanUp() throws Exception {

		closingBrowsers();

		extentReport.flush();

	}

	private void setupReports() {
		String htmlReportPath = String.format("%s/reports/%s_%s.html", currentWorkingDirectory, htmlReportFilename,
				testExecutionStartTime);

		htmlReporter = new ExtentHtmlReporter(htmlReportPath);
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);

	}

	private void closingBrowsers() throws Exception {
		cmnDriver.closeAllBrowsers();

	}

	private void invokeBrowserAndNavigateToBaseUrl() throws Exception {

		url = configProperties.getProperty("baseUrl");
		extentTest.log(Status.INFO, "Base URL - " + url);

		String browserType = configProperties.getProperty("browserType");
		cmnDriver = new CommonDriver(browserType);
		extentTest.log(Status.INFO, "Browser Invoked - " + browserType);

		int pageloadTimeout = Integer.parseInt(configProperties.getProperty("pageloadTimeout"));
		cmnDriver.setPageloadTimeout(pageloadTimeout);
		extentTest.log(Status.INFO, "Page load timeout - " + pageloadTimeout);

		int elementDetectionTimeout = Integer.parseInt(configProperties.getProperty("elementDetectionTimeout"));
		cmnDriver.setElementDetectionTimeout(elementDetectionTimeout);

		cmnDriver.navigateToFirstUrl(url);
		extentTest.log(Status.INFO, "Navigating to the URL - " + url);

		driver = cmnDriver.getDriver();
	}

	private void initializingScreenshotVariable() {
		screenshotControl = new ScreenshotControl(driver);

	}

	private void initializingPages() {
		homepage = new Homepage(driver);
		extentTest.log(Status.INFO, "Initailizing pages ");
		resultPage = new Resultspage(driver);

	}
}
