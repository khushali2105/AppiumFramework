package org.PageObject.Android;

import java.util.Set;

import org.Utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WebViewPage extends AndroidActions {
	
AndroidDriver driver;
	
	public WebViewPage(AndroidDriver driver) {
		
		super(driver);//it will called parent class constructor
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(name="q")
	private WebElement SearchBox;
	
	public void GetContext() throws InterruptedException {
		//Get ContextName in hybrid app
		Set<String>contexts = driver.getContextHandles();
	    for(String conTextName:contexts) {
	    System.out.println(conTextName);
	    }
	    
	    driver.context("WEBVIEW_com.androidsample.generalstore");
	    Thread.sleep(5000);
	}
	
	public void SearchInSearchBar() throws InterruptedException {
		SearchBox.sendKeys("Java");
		SearchBox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}
	
   

}
