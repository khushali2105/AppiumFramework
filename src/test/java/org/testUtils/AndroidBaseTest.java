package org.testUtils;


import java.io.FileInputStream;
import java.io.IOException;

import java.time.Duration;
import java.util.Properties;

import org.PageObject.Android.FormPage;
import org.Utils.AppiumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;




public class AndroidBaseTest extends AppiumUtils {



	public AndroidDriver driver;
	public FormPage formPage;
	@BeforeClass(alwaysRun=true)
		public void ConfigureAppium() throws InterruptedException, IOException {
			
		
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\resources\\data.properties");
			prop.load(fis);
			
			//ternary Operator 
			//if we send runtime parameter using maven command
			//mvn test -PRegression -DipAddress=192.168.40.2
			
			String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
			//String ipAddress = prop.getProperty("ipAddress");
			String port = prop.getProperty("portNumber");
			
			service = startAppiumServer(ipAddress,Integer.parseInt(port));
			 
			 Thread.sleep(2000);
			 
			 UiAutomator2Options option = new UiAutomator2Options();
			// option.setDeviceName("SapnaEmulator");
			 option.setDeviceName(prop.getProperty("AndroidDeviceName"));
			 //For real device automation you need to enable developer options and usb debugging on in your mobile
			 //after that in cmd use adb devices command it shows device is connected to computer or not
			// option.setDeviceName("Android Device"); //For Real Devive
			 option.setChromedriverExecutable(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\chromedriver.exe");
			 option.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\General-Store.apk");
			//URL url = new URL("http://192.168.40.2:4723");
			// driver = new AndroidDriver(url, option);
			   
			 driver = new AndroidDriver(service.getUrl(), option);
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 formPage = new FormPage(driver);
		}
	
	
	
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}
	
	
	
	}


