package com.backendtest.superwinbook.test;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.backendtest.superwinbook.main.BaseClass;
import com.backendtest.superwinbook.main.SuperwinSportsCreateNewUser;
import com.backendtest.superwinbook.main.SuperwinBookLoginPage;
import com.superwin.library.gson_Model.GetGsonSportsTestData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SuperwinBook_BaseTest extends BaseClass{

	@SuppressWarnings("deprecation")
	@BeforeSuite
	public static void initializeBrowser() throws IOException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		option.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(GetGsonSportsTestData.getBackendSportsConfigData().getBaseUrl());	
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("SuperwinBook_Reports.html");
		sparkReporter_all.config().setReportName("SuperwinBook_Reports");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all);
	}


	@BeforeClass
	public void pageObjects() {
         loginpage = new SuperwinBookLoginPage(driver);
         createNewUser = new SuperwinSportsCreateNewUser(driver);
	}
	
	@BeforeMethod
	public void createExtentTest(Method m) {
		extentTest = extentReports.createTest(m.getName());
	}

	@AfterMethod(alwaysRun = true)
	public void checkStatus(Method m, ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(m.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName());
		}
	}

	@AfterSuite
	public void generateExtentReports() throws Exception {
		extentReports.flush();
		Desktop.getDesktop().browse(new File("SuperwinBook_Reports.html").toURI());
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
	
}
