package org.androidApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.PageObject.Android.CartPage;
import org.PageObject.Android.FormPage;
import org.PageObject.Android.ProductCtaloguePage;
import org.PageObject.Android.WebViewPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testUtils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;


public class eCommerce_TC_1 extends AndroidBaseTest{
	//FormPage formPage;
	//ProductCtaloguePage productCtaloguePage;
	//CartPage cartPage;
	//WebViewPage webViewPage;
	
	@BeforeMethod(alwaysRun=true)
	public void preSetUp() {
		formPage.setActivity();
	}
	
	
	
	@DataProvider
	
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\ecommerce\\testData\\ecommerceTestData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		//return new Object[][] {{"Sapna","female","Canada"},{"Viral","Male","India"}};
	}
	
	
	
	@Test(priority=1,dataProvider="getData")
	public void FillFormWithoutNameField(HashMap<String,String>input) throws InterruptedException {
		
		
		formPage.setNameField(input.get(""));
		formPage.setGenderOption(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		formPage.SubmitForm();
		
		//How To Handle PopUp Toast Message in appium
		formPage.ToastMessageCheck("name");
		
		Thread.sleep(3000);
	}
	

	@Test(priority=2,dataProvider="getData",groups= {"smoke"})
	public void FillFormWithNameField(HashMap<String,String>input) throws InterruptedException {
		
		formPage.setCountrySelection(input.get("country"));
		formPage.setNameField(input.get("name"));
		formPage.setGenderOption(input.get("gender"));
		ProductCtaloguePage productCtaloguePage = formPage.SubmitForm();
		
		
		//To select first two item in page
		
		//driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		//driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		
		
		productCtaloguePage.AddFirstItemInCart(input.get("product1"));
		productCtaloguePage.AddSecondItemInCart(input.get("product2"));
		CartPage cartPage = productCtaloguePage.goToCartPage();
		cartPage.WaitForCartPageToOpen();
		double totalSum = cartPage.getProductSum();
		System.out.println(totalSum);
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		System.out.println(displayFormattedSum);
		Assert.assertEquals(totalSum, displayFormattedSum,"Not Match");
		
		cartPage.ClickOnTermsAndConditionText();
		cartPage.ClickOnButton();
		cartPage.ClickOnCheckBox();
		WebViewPage webViewPage = cartPage.ClickOnProceedButton();
		
		
	//	String ProductNameInsideCart = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
		//Assert.assertEquals(ProductNameInsideCart, "Nike SFB Jungle","Product Not Matched!");
		
		//Get ContextName in hybrid app
		webViewPage.GetContext();
	
		//NATIVE_APP
	    //WEBVIEW_com.androidsample.generalstore
		
		webViewPage.SearchInSearchBar();
	    
	}
	
	
	
	

}
