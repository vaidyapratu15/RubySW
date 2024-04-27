package com.backendtest.superwinbook.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.superwin.library.gson_Model.GetGsonTestData;
import com.superwin.main.BaseClass;
import com.superwin.main.SuperwinChangeMobileNumberPage;
import com.superwin.main.SuperwinDeposit;
import com.superwin.main.SuperwinEditProfile;
import com.superwin.main.SuperwinForgotPassword;
import com.superwin.main.SuperwinInstantWithdraw;
import com.superwin.main.SuperwinLoginPage;
import com.superwin.main.SuperwinProfileSectionPage;
import com.superwin.main.SuperwinResetPassword;
import com.superwin.main.SuperwinWithdraw;
import com.superwin.main.Turnover;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Superwin_BaseTest extends BaseClass {

	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void initializeBrowser() throws IOException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		option.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(GetGsonTestData.getConfigData().getBaseUrl());
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("m.SuperwinUserLoginFromSuperwin.html");
		sparkReporter_all.config().setReportName("m.SuperwinUserLoginFromSuperwin report");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all);
	}

	@BeforeClass
	public void pageObjects() {
		loginpage = new SuperwinLoginPage(driver);
	//	signuppage = new SuperwinSignup(driver);
		resetpage = new SuperwinResetPassword(driver);
		editprofile = new SuperwinEditProfile(driver);
		withdrawalpage = new SuperwinWithdraw(driver);
		instantwithdrawalpage = new SuperwinInstantWithdraw(driver);
		profilesection = new SuperwinProfileSectionPage(driver);
		forgotpassword = new SuperwinForgotPassword(driver);
		deposit = new SuperwinDeposit(driver);
		changepass = new SuperwinChangeMobileNumberPage(driver);
		turnover = new Turnover(driver);
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
		Desktop.getDesktop().browse(new File("m.SuperwinUserLoginFromSuperwin.html").toURI());
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
