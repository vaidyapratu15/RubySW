package com.backendtest.superwinbook.main;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	protected SuperwinBookLoginPage loginpage;
	protected SuperwinSportsCreateNewUser createNewUser;
	
}
