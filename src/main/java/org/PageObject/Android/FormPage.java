package org.PageObject.Android;

import org.Utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		
		super(driver);//it will called parent class constructor
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelection;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	@AndroidFindBy(xpath="(//android.widget.Toast)[1]")
	private WebElement ToastMessageText;
	
	
	
	//driver.findElement(AppiumBy.id("android:id/text1")).click();
	//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));")).click();
	//driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
	//driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

	
	public void setActivity() {
		 
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		//	driver.startActivity(activity); Not Working Now
			((JavascriptExecutor)driver).executeScript("mobile:startActivity",
					ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
	}
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
		
	}
	
	public void setGenderOption(String gender) {
		if(gender.contains("female")) {
			femaleOption.click();
		}else
			maleOption.click();
		
	}
	public void setCountrySelection(String countryName) {
		countrySelection.click();
		ScrollToText(countryName);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
		
		
	}
	
	public ProductCtaloguePage SubmitForm() throws InterruptedException {
		shopButton.click();
		Thread.sleep(2000);
		return new ProductCtaloguePage(driver);
	}
	
	
	public void ToastMessageCheck(String name) {
		String ToastMessage = ToastMessageText.getAttribute(name);
		Assert.assertEquals(ToastMessage, "Please enter your name");
	}
	
	
	
	
	
	
}
