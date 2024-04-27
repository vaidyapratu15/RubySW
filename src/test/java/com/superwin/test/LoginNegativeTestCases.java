package com.superwin.test;

import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import java.io.IOException;
import java.util.Map;

import com.framework.testlibrary.ExcelTestData_Utility;
import com.superwin.library.Utilities;

public class LoginNegativeTestCases extends BaseTest {

	@Test
	public void getBrowserLink() {
		extentTest.info("Link : " + driver.getCurrentUrl());
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        long loadTiming = (Long) js.executeScript(
            "return performance.timing.loadEventEnd - performance.timing.navigationStart;"
        );
        // Print the page load timing
        extentTest.info("Page Load Time: " + loadTiming + " milliseconds");
	}

	@Test(priority = 1, dataProvider = "data", dataProviderClass = ExcelTestData_Utility.class, testName = "LoginNegavtiveTc")
	public void verifyLoginNegavtiveTc(Map<String, String> map) throws IOException, InterruptedException {
		String username = map.get("Username");
		loginpage.setUsername(username);

		String password = map.get("Password");
		loginpage.setPassword(password);

		Boolean btn = loginpage.login();
		org.testng.Assert.assertEquals(btn, true);
	}

	@AfterMethod
	public void onTestFailure(ITestResult result) throws InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String testcasename = result.getMethod().getMethodName();
			Utilities.captureScreenshot(driver, testcasename + ".jpg");
		}
	}

	@AfterMethod(dependsOnMethods = "onTestFailure")
	public void refresh_page() {
		driver.navigate().refresh();
	}

}
