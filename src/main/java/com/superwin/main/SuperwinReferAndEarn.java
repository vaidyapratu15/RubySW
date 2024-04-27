package com.superwin.main;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.RandomizeData;
import com.superwin.library.gson_Model.GetGsonTestData;

public class SuperwinReferAndEarn extends BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "//span[contains(text(),'REFER AND EARN')]")
	WebElement referAndEarn;

	@FindBy(xpath = "//div[@class = 'text-center col col-10']")
	WebElement copyLink;

	@FindBy(xpath = "//div[@class = 'col col-10']")
	WebElement copyCode;

	@FindBy(xpath = "//div[@class = 'd-md-flex icon']")
	WebElement myReferrals;
	
	@FindBy(xpath = "//div[@class = 'row justify-center']//div[@class = 'pt-0 col col-12']")
	List<WebElement> referredUserDetails;
	
	@SuppressWarnings("static-access")
	public SuperwinReferAndEarn(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String referalLink;
	public String referalCode;

	
	public void clickOnReferAndEarn() {
		try {
			obj.jSForScroll(driver, referAndEarn);
			referAndEarn.click();
		} catch (Exception e) {
			extentTest.info("ReferAndEarn Is Not Clickable..!!!");
		}
	}

	public void copyReferalLink() {
		try {
			this.referalLink = copyLink.getText();
			GetGsonTestData.writeReferalData(referalCode, referalLink);
		} catch (Exception e) {
			extentTest.info("Not Able To Get Referal Link..!!!");
		}
	}

	public void copyReferalCode() {
		try {
			this.referalCode = copyCode.getText();
			GetGsonTestData.writeReferalData(referalCode, referalLink);
		} catch (Exception e) {
			extentTest.info("Not Able To Get Referal Code..!!!");
		}
	}
	
	public void getReferredUserDetails() {
		extentTest.info(myReferrals.getText());
		for(WebElement userDetail : referredUserDetails ) {
			extentTest.info(userDetail.getText());
		}
	}
	
}
