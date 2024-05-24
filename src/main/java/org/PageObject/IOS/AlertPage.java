package org.PageObject.IOS;

import org.Utils.IOSAction;
import org.openqa.selenium.WebElement;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertPage extends IOSAction {

	IOSDriver driver;
	
	public AlertPage(IOSDriver driver) {
			super(driver);
			this.driver = driver;
		}

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`label =='Text Entry'`]")
	private WebElement textEntryMenu;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementCell")
	private WebElement textBox;
	
	@iOSXCUITFindBy(accessibility="OK")
	private WebElement acceptPopUp;
	
	@iOSXCUITFindBy(iOSNsPredicate="label == 'Confirm'")
	private WebElement submit;
	
	@iOSXCUITFindBy(iOSNsPredicate="type == XCUIElementTypeStaticText AND value BEGINSWITH[c] 'Confirm'")
	private WebElement confirmMenuItem;
	
	@iOSXCUITFindBy(iOSNsPredicate="name BEGINSWith[c] 'A message'")
	private WebElement confirmMessage;
	
	
	public void fillTextLabel(String name) {
		textEntryMenu.click();
		textBox.sendKeys(name);
		acceptPopUp.click();
	}
	
	public String grabConfirmMessage() {
		confirmMenuItem.click();
		return confirmMessage.getText();
		
	}
}
