package api.utilities;

import java.util.Date;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.*;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReport;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext context) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
		Date date = new Date();
		String timeStamp = dateformat.format(date);

		repName = "Test-Report-" + timeStamp + ".html";

		sparkReport = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + repName);
		sparkReport.config().setDocumentTitle("API Automation Testing");
		sparkReport.config().setReportName("Post and Delete User Testing");
		sparkReport.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		extent.setSystemInfo("Application", "Pet Swagger");
		extent.setSystemInfo("Module", "User");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.log(Status.PASS, result.getName() + " got successfully executed");

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		//The below code once report generated open that in browser
				/*
				String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
				File extentReport = new File(pathOfExtentReport);
				
				try {
					Desktop.getDesktop().browse(extentReport.toURI());
				} catch (IOException e) {
					e.printStackTrace();
				}
		*/
	}
	
}

	
		
