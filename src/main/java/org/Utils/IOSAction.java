package org.Utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.ios.IOSDriver;

public class IOSAction extends AppiumUtils{

	IOSDriver driver;
	
	public IOSAction(IOSDriver driver) {
		//super(driver);
		this.driver = driver;
	}
	
	public void ScrollToWebElement(WebElement ele) {
		Map<String,Object>params = new HashMap<>();
		params.put("direction", "down");
		params.put("element", ((RemoteWebElement)ele).getId());

		driver.executeScript("mobile:Scroll", params);
	}
	
	public void longPressGesture(WebElement ele) {
	    Map<String,Object>params = new HashMap<>();
	    params.put("element", ((RemoteWebElement)ele).getId());
	    params.put("duration", 5);
	    driver.executeScript("mobile:touchAndHold", params);
    }
	
	public void slideAction() {
		Map<String,String>paramsSlide = new HashMap<String,String>();
		//BundleId you get from developer or from Google for inbuiltApp
		paramsSlide.put("bundleId", "com.app.mobileSlideShow");
		driver.executeScript("mobile", paramsSlide);
	}
	public void swipeAction(String direction) {
		Map <String,Object>paramsSwipe = new HashMap<>();
		paramsSwipe.put("direction", direction);
		driver.executeScript("mobile:Swipe", paramsSwipe);
	}
}

