package demo;

import org.testng.annotations.Test;

import utils.TestDataProvider;

public class DemoTestData {

	@Test(dataProvider = "getData", dataProviderClass = TestDataProvider.class)
	public void printTestData(String firstname, String lastname) {
		System.out.println("Firstname - " + firstname);

		System.out.println("Lastname - " + lastname);
	}

}
