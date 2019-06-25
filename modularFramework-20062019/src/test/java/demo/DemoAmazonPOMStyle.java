package demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementations.CommonDriver;
import designPattern.AmazonHomepage;

public class DemoAmazonPOMStyle {

	CommonDriver cmnDriver;
	String url = "https://www.amazon.in/";

	AmazonHomepage homepage;

	WebDriver driver;

	@BeforeClass
	public void setup() throws Exception {

		cmnDriver = new CommonDriver("chrome");
		cmnDriver.setPageloadTimeout(30);
		cmnDriver.setElementDetectionTimeout(10);

		cmnDriver.navigateToFirstUrl(url);

		driver = cmnDriver.getDriver();
		homepage = new AmazonHomepage(driver);
	}

	@Test
	public void verifySearchProduct() throws Exception {
		homepage.searchProduct("Apple Watch", "Electronics");
	}

	@AfterClass
	public void closeBrowser() throws Exception {
		cmnDriver.closeAllBrowsers();
	}

}
