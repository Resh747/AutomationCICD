package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;


import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReporterObject() {
		String path=System.getProperty("/Users/reshma/eclipse-workspace/ExtentReports")+"/reports/index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("We Automation Result");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports	extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Reshma");
		extent.createTest(path);
		return extent;
	}
}
