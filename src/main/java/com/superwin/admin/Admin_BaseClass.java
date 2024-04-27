package com.superwin.admin;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Admin_BaseClass {
	
	public static WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	protected AdminLogin adminlogin;
	protected AdminPanelTelegramSetting telegram;
	protected AdminPanelDigiTelegramSetting digiTelegram;
	protected AdminPanelWhatsAppSetting whatsApp;
	protected AdminPanelApproveDeposit approveDeposit;
	protected AdminPanelReferralSetting referralBonus;
	protected AdminLogout adminlogout;
}
