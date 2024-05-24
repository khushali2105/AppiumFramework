package org.iosApp;

import org.PageObject.IOS.AlertPage;
import org.PageObject.IOS.HomePage;
import org.testUtils.IOSBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSBasics extends IOSBaseTest{
	
	@Test 
	public void IosBasicTest() {
		
		//Xpath,Id,calssname,AccessibilityId,IosClassChain,IOS,iOSNsPredicateString
		AlertPage alertPage = homePage.selectAlertViews();
		alertPage.fillTextLabel("Hello word");
		String actualMessage = alertPage.grabConfirmMessage();
		Assert.assertEquals(actualMessage, "A message should be a short, complete sentence.");
		
		
		
	}

}
