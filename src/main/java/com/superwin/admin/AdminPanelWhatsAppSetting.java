package com.superwin.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.RandomizeData;

public class AdminPanelWhatsAppSetting extends Admin_BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "//li//button[@class = 'btn dropdown-toggle']")
	WebElement system;

	@FindBy(xpath = "//li//ul[@class = 'dropdown-menu show']//a")
	WebElement systemConfig;

	@FindBy(xpath = "(//div//h3[contains(text(),'WhatsApp Setting')])")
	WebElement whatsAppSetting;

	@FindBy(xpath = "//label[@class = 'custom-toggle checked']")
	WebElement custumToggleOn;

	@FindBy(xpath = "//label[@class = 'custom-toggle unchecked']")
	WebElement custumToggleOff;

	@SuppressWarnings("static-access")
	public AdminPanelWhatsAppSetting(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String clickOnSystem() {
		try {
			system.click();
			extentTest.info("System Button Is Clickable..!!!");
			return "System Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("System Button Is Not Clickable..!!!");
			return "System Button Is Not Clickable..!!!";
		}
	}

	public String clickOnSystemConfig() {
		try {
			systemConfig.click();
			extentTest.info("System Config Button Is Clickable..!!!");
			return "System Config Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("System Config Button Is Not Clickable..!!!");
			return "System Config Button Is Not Clickable..!!!";
		}
	}

	public String clickOnwhatsAppSetting() {
		try {
			whatsAppSetting.click();
			extentTest.info("whatsAppSetting Button Is Clickable..!!!");
			return "whatsAppSetting Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("whatsAppSetting Button Is Not Clickable..!!!");
			return "whatsAppSetting Button Is Not Not Clickable..!!!";
		}
	}

	public void verifyCustumToggleOn() {
		try {
			Boolean btn = custumToggleOn.isDisplayed();
			if (btn == true) {
				extentTest.info("Whatsapp Is On");

				custumToggleOn.click();

				extentTest.info("Turned Off WhatsApp");

			}
		} catch (Exception e) {
			extentTest.info("WhatsApp Is Not Clickable.");
		}
	}

	public void verifyCustumToggleOff() {
		try {
			Boolean btn = custumToggleOff.isDisplayed();
			if (btn == true) {
				extentTest.info("Whatsapp Is Off");

				custumToggleOff.click();

				extentTest.info("Turned ON WhatsApp");
			}
		} catch (Exception e) {
			extentTest.info("WhatsApp Is Not Clickable.");
		}
	}

}
