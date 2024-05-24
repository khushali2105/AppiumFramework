package org.PageObject.Android;

import java.util.List;

import org.Utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCtaloguePage extends AndroidActions{

	
AndroidDriver driver;
	
	public ProductCtaloguePage(AndroidDriver driver) {
		
		super(driver);//it will called parent class constructor
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private List<WebElement> ProductsName;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> AddProductIntoCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement CartButton;
	
	public void AddFirstItemInCart(String itemName) throws InterruptedException {
		
		ScrollToText(itemName);
		int productCount1 = ProductsName.size();
		
		for(int i=0;i<productCount1;i++) {
			String ProductName = ProductsName.get(i).getText();
			
			if(ProductName.equalsIgnoreCase(itemName)) {
				AddProductIntoCart.get(i).click();
			}
		}
		Thread.sleep(2000);
	}
	public void AddSecondItemInCart(String itemName) throws InterruptedException {	
		
		ScrollToText(itemName);
		int productCount2 = ProductsName.size();
		
		for(int i=0;i<productCount2;i++) {
			String ProductName = ProductsName.get(i).getText();
			
			if(ProductName.equalsIgnoreCase(itemName)) {
				AddProductIntoCart.get(i).click();
			}
		}
		Thread.sleep(2000);
	}
	
	public CartPage goToCartPage() throws InterruptedException {	
		CartButton.click();
		Thread.sleep(3000);
		return new CartPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
}
