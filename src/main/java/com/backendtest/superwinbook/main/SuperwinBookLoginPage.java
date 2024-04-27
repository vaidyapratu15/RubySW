package com.backendtest.superwinbook.main;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.RandomizeData;
import com.superwin.library.gson_Model.GetGsonSportsTestData;

public class SuperwinBookLoginPage extends BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "//div//input[@id='username']")
	WebElement username;

	@FindBy(xpath = "//div//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//div//button[@id='sign-in']")
	WebElement signIn;

	@FindBy(xpath = "//div[@class = 'user-status col-sm-8 ng-scope']")
	WebElement balance;

	@FindBy(xpath = "//button[@class='btn btn-link icon-header pr-sm-2 menu-collapse navbar-toggle menu-options']")
	WebElement leftSlider;
	
	@FindBy(xpath = "//a[@id='logout']")
	WebElement logout;

	@SuppressWarnings("static-access")
	public SuperwinBookLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private String userId;
	private String pwd;

	public String getUsername() throws FileNotFoundException {
		userId = GetGsonSportsTestData.getBackendSportsConfigData().getUsername();
		return userId;
	}

	public void setUsername(String userId) {
		this.userId = userId;
		username.sendKeys(userId);
		extentTest.info("Backend Fairplay Sports Username : " + userId);
	}

	public String getPassword() throws FileNotFoundException {
		pwd = GetGsonSportsTestData.getBackendSportsConfigData().getPassword();
		return pwd;
	}

	public void setPassword(String pwd) {
		this.pwd = pwd;
		password.sendKeys(pwd);
		extentTest.info("Backend Fairplay Sports Password : " + pwd);
	}

	public void signIn() {
		try {
			signIn.click();
			fwaits.visibilityOf(driver, leftSlider, 20);
		} catch (Exception e) {
			extentTest.info("SignIn Button Is Not Clickable..!!!");
		}
	}

	public void clickonLeftSlider() {
		try {
			leftSlider.click();
		} catch (Exception e) {
			extentTest.info("Left Slider Button Is Not Clickable..!!!");
		}
	}
	
	public void logout() {
		try {
			obj.jSForScroll(driver, logout);
			logout.click();
			extentTest.info(driver.getTitle());
		}catch (Exception e) {
			extentTest.info("Logout Is Not Clickable..!!!");
		}
	}
	
	

}
