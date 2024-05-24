package org.PageObject.Android;

import java.time.Duration;
import java.util.List;

import org.Utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{
	

	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver) {
		
		super(driver);//it will called parent class constructor
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmountLbl;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
	private WebElement alertTitle;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement button1;
	
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement CheckBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement btnProceed;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	private WebElement toolBarTitle;
	
	public void WaitForCartPageToOpen() {
		
		waitForElementToAppear(toolBarTitle, "Cart",driver);
		//WebDriverWait Wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//Wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));
	}
	
	public List<WebElement> getProductList() {
		return productList;
	}

	public double getProductSum() {
		
		 int count = productList.size();
		 double totalSum =0;
		 
		 for(int i=0;i<count;i++) {
				String amountString=  productList.get(i).getText();
				Double price =FormatedAmmount(amountString);
			   totalSum= totalSum+price; 
			  
			 }
		 return totalSum;
			 
	}
	
	public Double getTotalAmountDisplayed() {
		String FinalAmountText = totalAmountLbl.getText();
		 Double FinalAmount = FormatedAmmount(FinalAmountText);
		 System.out.println(FinalAmount);
		
		 return FinalAmount ;
	}
	
	public void ClickOnTermsAndConditionText() throws InterruptedException {
		
		 LongPressAction(termsButton);
		 Thread.sleep(2000);
		 String AlertText =  alertTitle.getText();
		 Assert.assertEquals(AlertText, "Terms Of Conditions","Text Not Matched!");
	}
	
	public void ClickOnButton() {
		button1.click();
	}
	
	public void ClickOnCheckBox() {
		CheckBox.click();
	}
	
	public WebViewPage ClickOnProceedButton() throws InterruptedException {
		btnProceed.click();
			Thread.sleep(7000);
			return new WebViewPage(driver);
	}
	
	 
	 
	
	
}
