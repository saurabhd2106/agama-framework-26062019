package in.amazon.test;

import org.testng.annotations.Test;

public class HomepageTest extends BaseTest {

	

	@Test
	public void verifySearchProduct() throws Exception {
		homepage.searchProduct("Apple Watch", "Electronics");
	}

	

}
