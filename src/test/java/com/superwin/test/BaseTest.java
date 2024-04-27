package com.superwin.test;

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
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.superwin.library.gson_Model.GetGsonTestData;
import com.superwin.main.BaseClass;
import com.superwin.main.Signup;
import com.superwin.main.SuperwinChangeMobileNumberPage;
import com.superwin.main.SuperwinDeposit;
import com.superwin.main.SuperwinEditProfile;
import com.superwin.main.SuperwinForgotPassword;
import com.superwin.main.SuperwinInstantWithdraw;
import com.superwin.main.SuperwinLoginPage;
import com.superwin.main.SuperwinProfileSectionPage;
import com.superwin.main.SuperwinReferAndEarn;
import com.superwin.main.SuperwinResetPassword;
import com.superwin.main.SuperwinSignupRegistration;
import com.superwin.main.SuperwinTelegramFlow;
import com.superwin.main.SuperwinWithdraw;
import com.superwin.main.Turnover;
import com.superwin.sports.BookmakerPage;
import com.superwin.sports.CricketPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends BaseClass {

	

	@BeforeSuite
	public void initializeBrowser() throws IOException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		option.addArguments("--disable-notifications");
		//option.addArguments("--headless");  
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
				
		long startTime = System.currentTimeMillis();
		driver.manage().timeouts().getPageLoadTimeout();
		driver.get(GetGsonTestData.getConfigData().getBaseUrl());
	//	driver.get("https://m.superwin.co/");
	//	driver.get("https://365.kheljaa.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Time takeing to launch the browser was " + diff + " milliseconds");
		
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("Superwin.html");
		sparkReporter_all.config().setReportName("Superwin report");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all);
	}

	@BeforeClass
	public void pageObjects() {
		loginpage = new SuperwinLoginPage(driver);
		signuppage = new Signup(driver);
		signuppageReg = new SuperwinSignupRegistration(driver);
		resetpage = new SuperwinResetPassword(driver);
		editprofile = new SuperwinEditProfile(driver);
		withdrawalpage = new SuperwinWithdraw(driver);
		instantwithdrawalpage = new SuperwinInstantWithdraw(driver);
		profilesection = new SuperwinProfileSectionPage(driver);
		forgotpassword = new SuperwinForgotPassword(driver);
		deposit = new SuperwinDeposit(driver);
		changepass = new SuperwinChangeMobileNumberPage(driver);
		telegramIcon = new SuperwinTelegramFlow(driver);
		referAndEarn = new SuperwinReferAndEarn(driver);
		turnover = new Turnover(driver);
		cricketPage = new CricketPage(driver); 
		fancyPage = new BookmakerPage(driver);
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
		Desktop.getDesktop().browse(new File("Superwin.html").toURI());
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
