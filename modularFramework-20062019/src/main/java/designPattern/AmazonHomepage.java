package designPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementations.DropdownControl;
import commonLibs.implementations.ElementControl;

public class AmazonHomepage {

	private WebElement searchCategory;

	private WebElement searchBox;

	private WebElement searchButton;

	private WebElement resultSpan;

	private ElementControl elementControl;

	private DropdownControl dropdownControl;

	public AmazonHomepage(WebDriver driver) {

		searchCategory = driver.findElement(By.id("searchDropdownBox"));

		searchBox = driver.findElement(By.id("twotabsearchtextbox"));

		searchButton = driver.findElement(By.xpath("//input[@value='Go']"));

		resultSpan = driver.findElement(By.xpath("(//span[@data-component-type='s-result-info-bar']//span)[1]"));

		elementControl = new ElementControl();

		dropdownControl = new DropdownControl();

	}

	public void searchProduct(String product, String category) throws Exception {
		elementControl.setText(searchBox, product);

		dropdownControl.selectViaVisibleText(searchCategory, category);

		elementControl.clickElement(searchButton);

		String result = elementControl.getText(resultSpan);

		System.out.println(result);
	}
}
