package in.amazon.test;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonLibs.implementations.CommonDriver;
import in.amazon.pages.Homepage;
import in.amazon.pages.Resultspage;

public class BaseTest {

	CommonDriver cmnDriver;
	String url;

	Homepage homepage;
	Resultspage resultPage;

	WebDriver driver;

	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReport;
	ExtentTest extentTest;

	static String htmlReportFilename;
	static String currentWorkingDirectory;
	static String testExecutionStartTime;

	static {
		htmlReportFilename = "AmazonTestReport";
		Date date = new Date();
		testExecutionStartTime = String.valueOf(date.getTime());
		currentWorkingDirectory = System.getProperty("user.dir");
	}

	@BeforeSuite
	public void preSetup() {

		String htmlReportPath = String.format("%s/reports/%s_%s.html", currentWorkingDirectory, htmlReportFilename,
				testExecutionStartTime);
		
		htmlReporter = new ExtentHtmlReporter(htmlReportPath);
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);

	}

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		
		extentTest = extentReport.createTest("Set up - Setting up the pre requisites for test cases");

		invokeBrowserAndNavigateToBaseUrl();

		initializingPages();

	}

	@AfterClass(alwaysRun = true)
	public void cleanUp() throws Exception {

		closingBrowsers();
		
		extentReport.flush();

	}

	private void closingBrowsers() throws Exception {
		cmnDriver.closeAllBrowsers();

	}

	private void invokeBrowserAndNavigateToBaseUrl() throws Exception {
		
		url = "https://www.amazon.in/";
		extentTest.log(Status.INFO, "Base URL - "+ url);
		
		String browserType = "chrome";
		cmnDriver = new CommonDriver(browserType);
		extentTest.log(Status.INFO, "Browser Invoked - "+ browserType);
		
		int pageloadTimeout = 30;
		cmnDriver.setPageloadTimeout(pageloadTimeout);
		extentTest.log(Status.INFO, "Page load timeout - "+ pageloadTimeout);
		
		cmnDriver.setElementDetectionTimeout(10);

		cmnDriver.navigateToFirstUrl(url);
		extentTest.log(Status.INFO, "Navigating to the URL - "+ url);
		
		driver = cmnDriver.getDriver();
	}

	private void initializingPages() {
		homepage = new Homepage(driver);
		extentTest.log(Status.INFO, "Initailizing pages ");
		resultPage = new Resultspage(driver);

	}
}
