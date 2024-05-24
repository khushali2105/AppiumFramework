package org.Utils;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumUtils {
	
	public AppiumDriverLocalService service;
	//AppiumDriver driver;
	
	//public AppiumUtils(AppiumDriver driver) {
	//	this.driver = driver;
	//}

	
	public AppiumDriverLocalService startAppiumServer(String ipAddress,int port) {
		 service = new AppiumServiceBuilder()
					.withAppiumJS(new File("C:\\Users\\VIRAL\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					//.withIPAddress("192.168.40.2").usingPort(4723).build();
					.withIPAddress(ipAddress).usingPort(port).build();
		 service.start();
		 return service;
	}
	//String to Double conversion
	public Double FormatedAmmount(String amountText) {
		Double Price = Double.parseDouble(amountText.substring(1));
		return Price;
	}

	public void waitForElementToAppear(WebElement ele, String value,AppiumDriver driver) {
		WebDriverWait Wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		Wait.until(ExpectedConditions.attributeContains(ele,"text",value));
		
		
	}
	
	//Read data from Json File
	// We need commons.io AND jackson-databind package from maven repository
	
	public List<HashMap<String, String>> getJsonData(String JsonFilePath) throws IOException {
		
		//System.getProperty("user.dir")+"\\src\\test\\java\\org\\ecommerce\\testData\\ecommerceTestData.json"
		
		String jsonContent = FileUtils.readFileToString(new File(JsonFilePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
			
		List<HashMap <String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
	}
	
	public String getScreenShotPath(String testCaseName,AppiumDriver driver) throws IOException {
		
		//1.capture and place in folder  //2. extent report pick file and attach in report
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports"+ testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}
}
