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

public class AdminPanelDigiTelegramSetting extends Admin_BaseClass {


	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();
	
	@FindBy(xpath = "(//label[@class = 'custom-toggle checked'])[1]")
	WebElement custumToggleOn;

	@FindBy(xpath = "(//label[@class = 'custom-toggle unchecked'])[1]")
	WebElement custumToggleOff;

	@FindBy(xpath = "(//label[@class = 'custom-toggle unchecked'])[2]")
	WebElement custumToggleOffDigi;
	
	@FindBy(xpath = "(//label[@class = 'custom-toggle checked'])[2]")
	WebElement custumToggleOnDigi;
	
	@FindBy(xpath = "(//div//input[@class = 'form-control form-control-alternative'])[3]")
	WebElement enterTelegramLink;

	@FindBy(xpath = "(//div[@class = 'mt-2 text-danger validation-text'])[5]")
	WebElement telegramLinkIsReq;

	@FindBy(xpath = "(//div[@class = 'mt-2 text-danger validation-text'])[6]")
	WebElement invalidTelegramLink;

	@FindBy(xpath = "(//div[@class = 'd-flex align-items-center']//button[@type ='button'])[2]")
	WebElement addLinkBtn;

	@FindBy(xpath = "(//div[@class = 'col-12 col-md-12 py-1 d-flex align-items-center link-height']//span//span//span)[1]")
	WebElement addedLink;

	@FindBy(xpath = "(//div[@class = 'col-12 col-md-12 py-1 d-flex align-items-center link-height']//span//span//span)[2]")
	WebElement cancleAddedLink;

	@FindBy(xpath = "(//div[@class = 'text-center']//button[@type ='button'])[2]")
	WebElement updateBtn;

	@FindBy(xpath = "(//div[@class = 'col-12 col-md-12 d-flex py-1 bg min-height'])[2]//span[@class = 'badge text-lowercase m-1 p-1.5 d-flex align-items-center badge-success']")
	List<WebElement> updatedLinks;

	@FindBy(xpath = "//div[@class = 'v-toaster']")
	WebElement toasterMsg;

	private String telegramLink;
	
	@SuppressWarnings("static-access")
	public AdminPanelDigiTelegramSetting(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void turnedOFF_DigiTelegram() { //considering btn is already ON
		try {
			Boolean btn = custumToggleOn.isDisplayed();
			if (btn == true) {
				extentTest.info("Dg Telegram Is On...!!!");

				custumToggleOnDigi.click();

				extentTest.info("Turned Off Dg Telegram...!!!");
			}else {
				extentTest.info("Dg Telegram Is On...!!!");

				custumToggleOn.click();

				extentTest.info("Turned Off Dg Telegram...!!!");
			}
		} catch (Exception e) {
			extentTest.info("Dg Telegram Toggle Is Not Clickable.");
		}
	}
	
	public void turnedON_DigiTelegram() {
		try {
			Boolean btn = custumToggleOff.isDisplayed();
			if (btn == true) {
				extentTest.info("Dg Telegram Is Off...!!!");

				custumToggleOffDigi.click();

				extentTest.info("Turned ON Dg Telegram...!!!");
			}else {
				extentTest.info("Dg Telegram Is Off...!!!");

				custumToggleOff.click();

				extentTest.info(" Turned ON Dg Telegram...!!!");
			}
		} catch (Exception e) {
			extentTest.info("Dg Telegram Toggle Is Not Clickable.");
		}
	}
	

	public String getTelegramLink() {
		telegramLink = rand.randomizeTelegramLink();
		return telegramLink;
	}

	@SuppressWarnings("finally")
	public String setTelegramLink(String telegramLink) {
		this.telegramLink = telegramLink;
		try {
			enterTelegramLink.sendKeys(telegramLink);
			extentTest.info("Newly Added Telegram Link Is : " + telegramLink);
			try {
				String errorMsg = telegramLinkIsReq.getText();
				extentTest.info("Error Message : " + errorMsg);
				return errorMsg;
			} finally {
				return "Telegram Link Added Suceesfully...!!!";
			}
		} catch (Exception e) {
			try {
				String errorMsg = telegramLinkIsReq.getText();
				extentTest.info("Error Message : " + errorMsg);
				return errorMsg;
			} finally {
				return "Not Able To Add Telegram Link...!!!";
			}
		}
	}

	public Boolean clickOnAddLink() {
		Boolean btn = addLinkBtn.isEnabled();
		if (btn == true) {
			addLinkBtn.click();
			extentTest.info("Add Link Button Is Enabled...!!!");
		} else {
			extentTest.info("Add Link Button Is Disabled...!!!");
		}
		return btn;
	}

	public String verifyAddedLink() {
		String link = addedLink.getText();
		if (link.equals(telegramLink)) {
			extentTest.info("Link Added Successfully...!!!");
			return "Link Added Successfully...!!!";
		} else {
			extentTest.info("Link Din't Added Successfully...!!!");
			return "Link Din't Added Successfully...!!!";
		}
	}

	public String verifyCancelAddedLink() {
		try {
			cancleAddedLink.click();
			extentTest.info("Able To Cancle Added Link..!!!");
			return "Able To Cancle Added Link..!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Cancle Added Link..!!!");
			return "Not Able To Cancle Added Link..!!!";
		}
	}

	public String update() {
		try {
			updateBtn.click();
			extentTest.info("Update Button Is Clickable..!!!");
			return "Update Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("Update Button Is Not Clickable..!!!");
			return "Update Button Is Not Clickable..!!!";
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public void verifyUpdatedLink() {
		try {
			int updatedLinksCount = updatedLinks.size();
			extentTest.info("Link Count : " + updatedLinksCount);

			for (WebElement link : updatedLinks) {
				extentTest.info("Telegram Link Details : " + link.getText());
				if (link.equals(telegramLink)) {
					extentTest.info("Telegram Link Updated...!!!");
				}
			}
		} catch (Exception e) {
			extentTest.info("Telegram Link Din't Updated...!!!");
		}
	}

	@SuppressWarnings("finally")
	public String getToasterMsg() {
		String toasterMessage = toasterMsg.getText();
		try {
			extentTest.info("Toaster Message Is : " + toasterMessage);
		} catch (Exception e) {
			extentTest.info("Not Able To Get Toaster Message...!!!");
		} finally {
			return toasterMessage;
		}

	}

	
}
