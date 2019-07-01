package commonLibs.implementations;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import commonLibs.contracts.IDriver;

public class CommonDriver implements IDriver {

	private WebDriver driver;
	private int pageloadTimeout;
	private int elementDetectionTimeout;

	public WebDriver getDriver() {
		return driver;
	}

	public void setPageloadTimeout(int pageloadTimeout) {
		this.pageloadTimeout = pageloadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}

	public CommonDriver(String browserType) throws Exception {

		pageloadTimeout = 20;

		elementDetectionTimeout = 10;

		browserType = browserType.trim();

		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/Saurabh Dhingra/workspace/libs/chromedriver-04052019/chromedriver.exe");

			driver = new ChromeDriver();

		} else if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:/Users/Saurabh Dhingra/workspace/libs/geckodriver-v0.20.1-win64/geckodriver.exe");

			driver = new FirefoxDriver();
		} else if (browserType.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:/Users/Saurabh Dhingra/workspace/libs/MicrosoftWebDriver.exe");

			driver = new EdgeDriver();

		} else if (browserType.equalsIgnoreCase("remote-chrome")) {

			ChromeOptions options = new ChromeOptions();

			options.addArguments("platform=any");

			URL hubUrl = new URL("http://192.168.1.9:12212/wd/hub");

			driver = new RemoteWebDriver(hubUrl, options);
		} else if (browserType.equalsIgnoreCase("chrome-headless")) {
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/Saurabh Dhingra/workspace/libs/chromedriver-04052019/chromedriver.exe");

			ChromeOptions options = new ChromeOptions();

			options.addArguments("--headless");

			driver = new ChromeDriver(options);

		}

		else {
			throw new Exception("Invalid Browser type : " + browserType);
		}

	}

	@Override
	public void navigateToFirstUrl(String url) throws Exception {

		url = url.trim();

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(pageloadTimeout, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);

		driver.get(url);

	}

	@Override
	public String getTitle() throws Exception {

		return driver.getTitle();
	}

	@Override
	public String getCurrentUrl() throws Exception {

		return driver.getCurrentUrl();
	}

	@Override
	public String getPageSource() throws Exception {

		return driver.getPageSource();
	}

	@Override
	public void navigateToUrl(String url) throws Exception {

		url = url.trim();
		driver.navigate().to(url);
	}

	@Override
	public void navigateForward() throws Exception {
		driver.navigate().forward();
	}

	@Override
	public void navigateBackward() throws Exception {
		driver.navigate().back();

	}

	@Override
	public void refresh() throws Exception {
		driver.navigate().refresh();

	}

	@Override
	public void closeBrowser() throws Exception {

		if (driver != null) {
			driver.close();
		}

	}

	@Override
	public void closeAllBrowsers() throws Exception {

		if (driver != null) {
			driver.quit();
		}

	}

}
