package in.amazon.pages;

import org.openqa.selenium.WebDriver;

import commonLibs.implementations.DropdownControl;
import commonLibs.implementations.ElementControl;
import commonLibs.implementations.IFrameControl;
import commonLibs.implementations.JavascriptControl;

public class Basepage {
	
	ElementControl elementControl;

	DropdownControl dropdownControl;
	
	JavascriptControl jsControl;
	
	IFrameControl iframeControl;
	
	public Basepage(WebDriver driver) {
		elementControl = new ElementControl();

		dropdownControl = new DropdownControl();
		
		jsControl = new JavascriptControl();
		
		iframeControl = new IFrameControl(driver);
	}

}
