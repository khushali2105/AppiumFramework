package org.Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {
		//super(driver);
		this.driver = driver;
	}
	
	public void LongPressAction(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of( "elementId", ((RemoteWebElement) ele).getId(),
                "duration",5000));
	}
	
	
	
	public void ScrollToEndAction() {
		boolean canScrollMore;
		do
		{
			canScrollMore = (Boolean) ((JavascriptExecutor)driver).executeScript("mobile:scrollGesture",
					ImmutableMap.of( "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 1.0));
		}while(canScrollMore);
	}
	
	public void ScrollToText(String textname) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+textname+"\"));"));
	}
	
	public void SwipeAction(WebElement ele,String direction) {
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
					"direction",direction,
					"percent",0.75
						));
	}
	
	
		

}
