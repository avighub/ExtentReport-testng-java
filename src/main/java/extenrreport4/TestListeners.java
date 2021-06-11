package extenrreport4;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

//import com.extentReport.ExtentReportTest;
import config.Testbase;

public class TestListeners extends Testbase implements ITestListener {

	public static ExtentReports extentReport = null;

	// This will be executed before Any test, but once
	public void onStart(ITestContext context) {
		extentReport = ExtentReportManager.createInstance();
	}

	// This will be executed before each Test start
	public void onTestStart(ITestResult result) {
		// For each test method , it will generate Class name + method name
		ExtentTest test = extentReport
				.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
		extentTest.set(test);
	}

	// This will be executed for each Test if only Test is PASSED
	public void onTestSuccess(ITestResult result) {
		if (result.getParameters().length > 0) {
			String paramName = Arrays.asList(result.getParameters()).toString();
			extentTest.get().log(Status.INFO, "Test Data: " + paramName);
			extentTest.get().log(Status.PASS, MarkupHelper
					.createLabel(result.getMethod().getMethodName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			extentTest.get().log(Status.PASS, MarkupHelper
					.createLabel(result.getMethod().getMethodName() + " Test Case PASSED", ExtentColor.GREEN));
		}

	}

	// This will be executed for each Test if only Test is FAILED
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		// String exceptionMessage =
		// Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().log(Status.FAIL,
				MarkupHelper.createLabel(methodName + " - Test Case FAILED", ExtentColor.RED));
		extentTest.get().fail("Click on the log below to expand...");
		extentTest.get().log(Status.FAIL, result.getThrowable());

		String screenshotpath = null;
		try {
			screenshotpath = ExtentReportManager.takeScreenshot(driver, methodName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		extentTest.get().fail("Test Case Failed. Click below screenshot to view.");
		try {
			extentTest.get().addScreenCaptureFromPath(screenshotpath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, MarkupHelper
				.createLabel(result.getMethod().getMethodName() + " Test Case SKIPPED", ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext context) {
		if (extentReport != null) {
			extentReport.flush();
		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

}
