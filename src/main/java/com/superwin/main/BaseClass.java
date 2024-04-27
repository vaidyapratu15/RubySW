
package com.superwin.main;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.superwin.sports.BookmakerPage;
import com.superwin.sports.CricketPage;
import com.superwin365.com.BonusWalletDetails;
import com.superwin365.com.NormalWalletDetails;
import com.superwin365.com.SportsWalletDetails;

public class BaseClass  {
	public static WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	protected SuperwinLoginPage loginpage;
	protected Signup signuppage;
	protected SuperwinSignupRegistration signuppageReg;
	protected SuperwinResetPassword resetpage;
	protected SuperwinEditProfile editprofile;
	protected SuperwinWithdraw withdrawalpage;
	protected SuperwinInstantWithdraw instantwithdrawalpage;
	protected SuperwinProfileSectionPage profilesection;
	protected SuperwinForgotPassword forgotpassword;
	protected SuperwinDeposit deposit;
	protected SuperwinChangeMobileNumberPage changepass;
	protected SuperwinTelegramFlow telegramIcon;
	protected SuperwinReferAndEarn referAndEarn;
	protected Turnover turnover;
	protected CricketPage cricketPage;
	protected BookmakerPage fancyPage;
	protected NormalWalletDetails tripleWallet;
	protected SportsWalletDetails spWallet;
	protected BonusWalletDetails bonusWallet;
	protected AffiliateCode aff;

}

 