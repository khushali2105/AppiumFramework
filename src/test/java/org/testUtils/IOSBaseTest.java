package org.testUtils;


import java.io.FileInputStream;
import java.io.IOException;

import java.time.Duration;
import java.util.Properties;

import org.PageObject.IOS.HomePage;
import org.Utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;


public class IOSBaseTest extends AppiumUtils{
	
	//public AppiumDriverLocalService service;
	public IOSDriver driver;
	public HomePage homePage;
	
	@BeforeClass()
		public void ConfigureAppium() throws InterruptedException, IOException {
		
			// service = new AppiumServiceBuilder()
					//.withAppiumJS(new File("C:\\Users\\VIRAL\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					//.withIPAddress("192.168.40.2").usingPort(4723).build();
			// service.start();
		
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\resources\\data.properties");
			prop.load(fis);
			
			String ipAddress = prop.getProperty("ipAddress");
			String port = prop.getProperty("portNumber");
		
		     service = startAppiumServer(ipAddress,Integer.parseInt(port));
			 
			 Thread.sleep(2000);
			 //For Android
			 //UiAutomator2Options option = new UiAutomator2Options();
			 //option.setDeviceName("SapnaEmulator");
			 //option.setChromedriverExecutable("E:\\Sapna\\Automation\\AppiumDownload\\chromedriver.exe");
			 //option.setCapability("browserName", "Chrome");
			 
			 //For IOS
			 
			 XCUITestOptions options= new XCUITestOptions();
			// options.setDeviceName("iPhone 13 Pro");//Device name you have to set it in xcode
			 options.setDeviceName("IOSDeviceName");
			 options.setApp("E:\\Sapna\\Automation\\AppiumDownload\\ios-uicatalog-master\\UIKitCatalog\\UIKitCatalog-iphonesimulator.app");
			 
			 //Capabilities we need to add for real ios devices
			 //To get all the details follow steps return in IOS+Real+Device+Automation.docx in resources folder
			 
			 options.setCapability("XcodeOrgId", "XXXXXX");//you get from your team
			 options.setCapability("XcodeSigningId", "iPhone Developer");
			 options.setCapability("Udid", "XXXX");//you get from device you test
			 options.setCapability("UpdateWDABundleId", "XXXXX");//you get from your team
			 
			 
			 //For IOSSliderTest Class use this app
			 //options.setApp("E:\\Sapna\\Automation\\AppiumDownload\\ios-uicatalog-master\\UIKitCatalog\\TestApp 3.app");
			 
			 options.setPlatformVersion("15.5");//PlatformVersion you get from xcode
			 
			 //Appium can not directly talk with ios app
			 //First it install WebDriver agent in your phone
			 //and from their using WebDriver Agent and XCUITestOptions it will able to automate ios
			 
			 options.setWdaLaunchTimeout(Duration.ofSeconds(20));
			 
				//URL url = new URL("http://192.168.40.2:4723");
			   // driver = new IOSDriver(url, options);
			 
			    driver = new IOSDriver(service.getUrl(), options);
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			    homePage = new HomePage(driver);
		
	}	
	
		@AfterClass()
		public void tearDown() {
			driver.quit();
			service.stop();
		}


}
