package in.amazon.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class HomepageTest extends BaseTest {

	@Test
	public void verifySearchProduct() throws Exception {

		extentTest = extentReport.createTest("TC - 001 - Verify Search Product Functionality");

		String product = "Apple watch";
		String category = "Electronics";

		extentTest.log(Status.INFO, "Searching product with category - " + product + ", " + category);
		homepage.searchProduct(product, category);
	}

	@Test
	public void verifyTitleOfThePage() throws Exception {

		extentTest = extentReport.createTest("TC - 003 - Verify the title of homepage");
		
		String expectedTitle = "Amazon Home Page";

		String actualTitle = cmnDriver.getTitle();

		extentTest.log(Status.INFO, "Actual Title - "+ actualTitle + " and expected Title - "+ expectedTitle);
		Assert.assertEquals(actualTitle, expectedTitle);
	}

}
