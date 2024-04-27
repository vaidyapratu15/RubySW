package com.superwin.admin.test;

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
import com.superwin.admin.AdminLogin;
import com.superwin.admin.AdminLogout;
import com.superwin.admin.AdminPanelApproveDeposit;
import com.superwin.admin.AdminPanelDigiTelegramSetting;
import com.superwin.admin.AdminPanelReferralSetting;
import com.superwin.admin.AdminPanelTelegramSetting;
import com.superwin.admin.AdminPanelWhatsAppSetting;
import com.superwin.admin.Admin_BaseClass;
import com.superwin.library.gson_Model.GetGsonTestData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Admin_BaseTest extends Admin_BaseClass {

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
		driver.get(GetGsonTestData.getConfigData().getAdminPanelUrl());
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("SuperwinAdmin.html");
		sparkReporter_all.config().setReportName("SuperwinAdmin report");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all);
	}

	@BeforeClass
	public void pageObjects() {
		adminlogin = new AdminLogin(driver);
		telegram = new AdminPanelTelegramSetting(driver);
		digiTelegram = new AdminPanelDigiTelegramSetting(driver);
		whatsApp = new AdminPanelWhatsAppSetting(driver);
		approveDeposit = new AdminPanelApproveDeposit(driver);
		referralBonus = new AdminPanelReferralSetting(driver);
		adminlogout = new AdminLogout(driver);
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
		Desktop.getDesktop().browse(new File("SuperwinAdmin.html").toURI());
	}

	//@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
