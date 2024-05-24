package org.PageObject.IOS;

import org.Utils.IOSAction;
import org.openqa.selenium.WebElement;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends IOSAction{
	
	IOSDriver driver;
	
	public HomePage(IOSDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@iOSXCUITFindBy(accessibility="Alert Views")
	private WebElement alertViews;
	
	
	
	
	public AlertPage selectAlertViews() {
		alertViews.click();
		return new AlertPage(driver);
	}

	
}
