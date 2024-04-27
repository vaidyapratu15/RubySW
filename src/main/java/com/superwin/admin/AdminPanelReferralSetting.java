package com.superwin.admin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.RandomizeData;

public class AdminPanelReferralSetting extends Admin_BaseClass{

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();
	
	
	@FindBy(xpath = "//div[@class = 'card-header dropdown-setting border-0 d-flex']//h3[contains(text(),'Bonus Settings')]")
	WebElement bonusSetting;
	
	@FindBy(xpath = "//div//div//span[contains(text(),'pre referral bonus')]")
	WebElement preReferralBonus;
	
	@FindBy(xpath = "(//div[@class = 'bonus-type'])[19]")
	WebElement preBonusType;
	
	@FindBy(xpath = "(//div[@class = 'bonus-type'])[19]//select//option")
	List<WebElement> preBonusOption;
	
	@FindBy(xpath = "(//div[@class = 'form-group']//input[@class = 'form-control  text-capitalize'])[55]")
	WebElement prePercentage;
	
	@FindBy(xpath = "(//div//div//span[contains(text(),'referral bonus')])[2]")
	WebElement referralBonus;
	
	@FindBy(xpath = "(//div[@class = 'bonus-type'])[20]")
	WebElement referralBonusType;
	
	@FindBy(xpath = "(//div[@class = 'bonus-type'])[20]//select//option")
	List<WebElement> referralBonusOption;
	
	@FindBy(xpath = "(//div[@class = 'form-group']//input[@class = 'form-control  text-capitalize'])[58]")
	WebElement referralPercentage;
	
	@SuppressWarnings("static-access")
	public AdminPanelReferralSetting(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnBonusSettings() {
		try {
			bonusSetting.click();
			extentTest.info("Bonus Setting Is Clickable...!!!");
		}catch (Exception e) {
			extentTest.info("Bonus Setting Is Not Clickable...!!!");
		}
	}
	
	public void scrollTillPreReferralBonus() {
		try {
			obj.jSForScroll(driver, preReferralBonus);
			extentTest.info("Pre-ReferralBonus Is Visible...!!!");
		}catch (Exception e) {
			extentTest.info("Pre-ReferralBonus Is Not Visible...!!!");
		}
	}
	
	public void selectPreReferralBonusType(String bonusOption) {
		preBonusType.click();
		for(WebElement option : preBonusOption) {
			if(option.getText().equals(bonusOption)) {
				option.click();
			}
		}
	}
	
	public void enterPreReferralPercentageValue(String value) {
		try {
			prePercentage.sendKeys(value);
			extentTest.info("Per Referral Bonus Percentage Value is : " + value);
		}catch (Exception e) {
			extentTest.info("Not Able To Change Per Referral Bonus Percentage Value...!!!");
		}
	}
	
	public void scrollTillReferralBonus() {
		try {
			obj.jSForScroll(driver, preReferralBonus);
			extentTest.info("Pre-ReferralBonus Is Visible...!!!");
		}catch (Exception e) {
			extentTest.info("Pre-ReferralBonus Is Not Visible...!!!");
		}
	}
	
	public void selectReferralBonusType(String bonusOption) {
		referralBonusType.click();
		for(WebElement option : referralBonusOption) {
			if(option.getText().equals(bonusOption)) {
				option.click();
			}
		}
	}
	
	public void enterReferralPercentageValue(String value) {
		try {
			referralPercentage.sendKeys(value);
			extentTest.info("Referral Bonus Percentage Value is : " + value);
		}catch (Exception e) {
			extentTest.info("Not Able To Change Referral Bonus Percentage Value...!!!");
		}
	}	
	
}
