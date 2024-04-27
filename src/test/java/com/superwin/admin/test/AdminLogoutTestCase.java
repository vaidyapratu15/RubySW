package com.superwin.admin.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.superwin.library.Utilities;

public class AdminLogoutTestCase extends Admin_BaseTest{
	
	@Test
	public void verifyLogoutPositiveTc() throws IOException, InterruptedException {
		adminlogout.adminProfile();
		
		adminlogout.logout();
		
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Logout : " + afterlogout);
		org.testng.Assert.assertEquals(afterlogout, null);
	}
	


}
