package com.superwin.test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.MongoDatabase;
import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class WithdrawalBankFlowFor3Users extends BaseTest {

	SoftAssert soft = new SoftAssert();

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
	
	@Test(priority = 1, invocationCount = 2)
	public void addBankUser1() throws Exception {
		loginpage.setUsername(GetGsonTestData.getUserData().getUsername());
		loginpage.setPassword(GetGsonTestData.getUserData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);

		loginpage.profileClick();
		withdrawalpage.withdrawNow();

		withdrawalpage.addBankDetails();
		withdrawalpage.otpPopup();
		String otp = withdrawalpage.getOtp();
		withdrawalpage.setOtp(otp);

		withdrawalpage.setAccountNumber("576767676761");
		withdrawalpage.confirmAccountNumber("576767676761");
		withdrawalpage.setIfscCode("YESB0000014");
		withdrawalpage.setAccountHolderName("Tejal");
		String actualvalue = withdrawalpage.addAccount();
		String expextedvalue = GetGsonTestData.getConfigData().getBankAdded();
		soft.assertEquals(actualvalue, expextedvalue);

		loginpage.profileClick();
		loginpage.logout();

		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();

	}

	// @Test(priority = 2)
	public void addBankUser2() throws Exception {
		loginpage.setUsername(GetGsonTestData.getUserData().getUsername1());
		loginpage.setPassword(GetGsonTestData.getUserData().getPassword1());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);

		loginpage.profileClick();
		withdrawalpage.withdrawNow();

		withdrawalpage.addBankDetails();
		withdrawalpage.otpPopup();
		String otp = withdrawalpage.getOtp();
		withdrawalpage.setOtp(otp);

		withdrawalpage.setAccountNumber("576767676761");
		withdrawalpage.confirmAccountNumber("576767676761");
		withdrawalpage.setIfscCode("YESB0000014");
		withdrawalpage.setAccountHolderName("Tejal");
		String actualvalue = withdrawalpage.addAccount();
		String expextedvalue = GetGsonTestData.getConfigData().getBankAdded();
		soft.assertEquals(actualvalue, expextedvalue);

		loginpage.profileClick();
		loginpage.logout();

		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();

	}

	// @Test(priority = 3)
	public void addBankUser3() throws Exception {
		loginpage.setUsername(GetGsonTestData.getUserData().getUsername2());
		loginpage.setPassword(GetGsonTestData.getUserData().getPassword2());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);

		loginpage.profileClick();
		withdrawalpage.withdrawNow();

		withdrawalpage.addBankDetails();
		withdrawalpage.otpPopup();
		String otp = withdrawalpage.getOtp();
		withdrawalpage.setOtp(otp);

		withdrawalpage.setAccountNumber("576767676761");
		withdrawalpage.confirmAccountNumber("576767676761");
		withdrawalpage.setIfscCode("YESB0000014");
		withdrawalpage.setAccountHolderName("Tejal");
		String actualvalue = withdrawalpage.addAccount();
		String expextedvalue = GetGsonTestData.getConfigData().getExistingBank();
		soft.assertEquals(actualvalue, expextedvalue);

		loginpage.profileClick();
		loginpage.logout();

		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();

	}

	// @Test(priority = 4)
	public void checkSuspectedUser() throws IOException {
		Boolean expextedvalue = MongoDatabase.getSuspectedField(GetGsonTestData.getDBData().getFpDatabase(),
				GetGsonTestData.getDBData().getCollection(), GetGsonTestData.getDBData().getKey(),
				"+91".concat(GetGsonTestData.getUserData().getUsername()),
				GetGsonTestData.getDBData().getFpField());
		org.testng.Assert.assertEquals(true, expextedvalue);
		extentTest.info("Suspected User1 : " + expextedvalue);

		Boolean expextedvalue1 = MongoDatabase.getSuspectedField(GetGsonTestData.getDBData().getFpDatabase(),
				GetGsonTestData.getDBData().getCollection(), GetGsonTestData.getDBData().getKey(),
				"+91".concat(GetGsonTestData.getUserData().getUsername1()),
				GetGsonTestData.getDBData().getFpField());
		org.testng.Assert.assertEquals(true, expextedvalue1);
		extentTest.info("Suspected User2 : " + expextedvalue1);

		Boolean expextedvalue2 = MongoDatabase.getSuspectedField(GetGsonTestData.getDBData().getFpDatabase(),
				GetGsonTestData.getDBData().getCollection(), GetGsonTestData.getDBData().getKey(),
				"+91".concat(GetGsonTestData.getUserData().getUsername2()),
				GetGsonTestData.getDBData().getFpField());
		org.testng.Assert.assertEquals(false, expextedvalue2);
		extentTest.info("Suspected User3 : " + expextedvalue2);

	}

}
