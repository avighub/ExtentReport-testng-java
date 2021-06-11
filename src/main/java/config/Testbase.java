package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;

import extenrreport4.TestListeners;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({ TestListeners.class })
public class Testbase {

	public WebDriver driver;
	public static String environment = null;
	public static String browser = null;
	// Making ExtentTest threadLocal to be able to use in parallel testing
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@BeforeClass
	public void setup() {
		environment = "SQE";
	}

	@BeforeMethod
	public void setupBeforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com/");
	}

	@AfterMethod
	public void cleanUpAfterMethod() {
		driver.quit();
	}
}
