package in.amazon.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import commonLibs.implementations.CommonDriver;
import in.amazon.pages.Homepage;
import in.amazon.pages.Resultspage;

public class BaseTest {

	CommonDriver cmnDriver;
	String url;

	Homepage homepage;
	Resultspage resultPage;

	WebDriver driver;

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {

		invokeBrowserAndNavigateToBaseUrl();

		initializingPages();

	}

	@AfterClass(alwaysRun = true)
	public void cleanUp() throws Exception {
		
		
		closingBrowsers();
		
	}

	private void closingBrowsers() throws Exception {
		cmnDriver.closeAllBrowsers();

	}

	private void invokeBrowserAndNavigateToBaseUrl() throws Exception {
		url = "https://www.amazon.in/";

		cmnDriver = new CommonDriver("chrome");

		cmnDriver.setPageloadTimeout(30);
		cmnDriver.setElementDetectionTimeout(10);

		cmnDriver.navigateToFirstUrl(url);
		driver = cmnDriver.getDriver();
	}

	private void initializingPages() {
		homepage = new Homepage(driver);

		resultPage = new Resultspage(driver);

	}
}
