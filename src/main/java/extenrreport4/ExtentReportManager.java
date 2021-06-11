package extenrreport4;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import config.Testbase;

public class ExtentReportManager extends Testbase {
	
	public static ExtentReports extentReport;
	public static ExtentSparkReporter htmlReporter;

	public static ExtentReports createInstance() {
//		String filename = getReportName();
//		String directory = System.getProperty("user.dir") + "\\test-output\\ExtentReport\\";
//		new File(directory).mkdirs();
//		String path = directory + filename;
//		System.out.println(path);
		
		// Create report name with datestamp
		String date = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "\\test-output\\ExtentReport\\AutomationReport_" + date
				+ ".html";

		// location of the extent report
		htmlReporter = new ExtentSparkReporter(reportPath);
		// Title of the report
		htmlReporter.config().setDocumentTitle("Automation Report");
		// Name of the report
		htmlReporter.config().setReportName("Extent Report V4");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extentReport = new ExtentReports(); // create object of ExtentReports
		extentReport.attachReporter(htmlReporter);

		// General information releated to application
		extentReport.setSystemInfo("Application Name", "Google Test");
		extentReport.setSystemInfo("User Name", "Avishek behera");
		extentReport.setSystemInfo("Envirnoment", "Production");

		return extentReport;
	}

//	public static String getReportName() {
//		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		String fileName = "AutomationReport_" + dateName;
//		System.out.println(fileName);
//		return fileName;
//	}

	public static String takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "\\test-output\\ExtentReport\\Screenshots\\Screenshot_"
				+ screenshotName + "_" + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
