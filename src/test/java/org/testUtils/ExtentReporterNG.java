package org.testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	static ExtentReports extent;
	
	public static ExtentReports  getReporterObject() {
		//ExtentSparkReporter:	
		//ExtentReports:
				
			String path = System.getProperty("user.dir")+"\\reports\\index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				
			reporter.config().setReportName("Web automation Result");
			reporter.config().setDocumentTitle("Test Result");
				
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Sapna");
			return extent;
	}
}
