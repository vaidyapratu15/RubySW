package com.backendtest.superwinbook.main;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.RandomizeData;

public class SuperwinSportsCreateNewUser extends BaseClass{
	
	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();
	
	@FindBy(xpath = "(//button[normalize-space()='Create New'])[1]")
	WebElement createNew;

	@FindBy(xpath = "//div[@class = 'col-md-4']//input[@name='username']")
	WebElement enterUsername;

	@FindBy(xpath = "//div//div[@class = 'form-group']//input[@id='password']")
	WebElement enterPassword;
	
	@FindBy(xpath = "//div//div[@class = 'form-group']//input[@id='cpassword']")
	WebElement confirmPassword;

	@FindBy(xpath = "//div//div[@class = 'form-group']//input[@id='mobileno']")
	WebElement enterMobileNo;
	
	@FindBy(xpath = "//button[@id='memeberSubmit']")
	WebElement save;
	
	@FindBy(xpath = "//div[@class = 'd-flex align-items-center justify-content-between']//input[@placeholder='Search']")
	WebElement searchUser;
	
	@FindBy(xpath = "(//table//tr[@ng-repeat = 'item in items track by $index']//td)[3]")
	WebElement userIdentification;

	@SuppressWarnings("static-access")
	public SuperwinSportsCreateNewUser(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private String username;
	private String password;
	private String mobileNo;
	
	public void createNewUser() {
		createNew.click();
	}

	public String getUsername() {
		username = rand.randomizeName();
		//getUsername = "Automation";
		return username;
	}

	public void setUsername(String getUsername) {
		this.username = getUsername;
		enterUsername.sendKeys(getUsername);
		extentTest.info("New User Id : " + getUsername);
	}

	public String getPassword() {
		password = rand.randomizeStrongPassword();
		return password;
	}

	public void setPassword(String getPassword) {
		this.password = getPassword;
		enterPassword.sendKeys(getPassword);
		extentTest.info("New User Password : " + getPassword);
	}

	public void setConfirmPassword(String getPassword) {
		this.password = getPassword;
		confirmPassword.sendKeys(getPassword);
		extentTest.info("New User Confirm Password : " + getPassword);
	}

	public String getMobileNo() {
		mobileNo = rand.randomizeNumber();
		return mobileNo;
	}

	public void setMobileNo(String getMobileNo) {
		this.mobileNo = getMobileNo;
		enterMobileNo.sendKeys(getMobileNo);
		extentTest.info("New User Mobile No : " + getMobileNo);
	}
	
	public void save() throws InterruptedException {
		obj.jSForScroll(driver, save);
		Thread.sleep(1000);
		obj.jSForClick(driver, save);
	}
	
	public void searchNewCreatedUser(String getUsername) {
		this.username = getUsername;
		searchUser.sendKeys(getUsername, Keys.ENTER);
		try {
			String userId = userIdentification.getText();
			if(userId.equals(getUsername)) {
				extentTest.info("User Created Successfully..!!!");
			}
			else {
				extentTest.info(" ");
			}
		}catch (Exception e) {
			extentTest.info("Not Able To Search User..!!!");
		}
	}


}
