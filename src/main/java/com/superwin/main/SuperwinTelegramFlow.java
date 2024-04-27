package com.superwin.main;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.RandomizeData;

public class SuperwinTelegramFlow extends BaseClass {
	
	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "//div[@class = 'pos-abos Telegramicon']")
	WebElement telegramIconOnRegPage;
	
	@FindBy(xpath = "(//div[@class = 'pos-abos Telegramicon']//following::a)[1]")
	WebElement clickTelegramIconOnRegPage;
	
	@FindBy(xpath = "//button//following::span//div//following::span[contains(text(),'Telegram')]")
	WebElement telegramIconOnProfileSec;
	
	@FindBy(xpath = "//button//span[contains(text(),'Click Here')]")
	WebElement clickTelegramIconOnReg2Page;
	
	@SuppressWarnings("static-access")
	public SuperwinTelegramFlow(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean checkTelegramIconOnRegPage() {
		String parentWindowHandle = driver.getWindowHandle();
		try {
			Boolean data = telegramIconOnRegPage.isDisplayed();
			extentTest.info("TelegramIcon Is Displayed On Registration Page : " + data);
			if(data == true) {
				clickTelegramIconOnRegPage.click();
				try {
					Set<String> windowHandles = driver.getWindowHandles();
					for (String windowHandle : windowHandles) {
						if (!windowHandle.equals(parentWindowHandle)) {
							driver.switchTo().window(windowHandle);
							Thread.sleep(2000);
							extentTest.info("Page URL : " + driver.getCurrentUrl());
							Thread.sleep(1000);
						}
					}
				} catch (Exception e) {
					extentTest.info("Page didnt got redirected..!!!");
				} finally {
					String title = driver.getTitle();
					if (!title.equals("Online Betting Site | Sports Betting in India | Fairplay")) {
						extentTest.info("Page redirected to : " + title);
						driver.close();
						driver.switchTo().window(parentWindowHandle);
						Thread.sleep(1000);
					} else {
						int i = 0;
						do {
							break;
						} while (i == 0);
					}
				}
			}
			return true;
		} catch (Exception e) {
			extentTest.info("TelegramIcon Is Not Displayed On Registration Page...!!!");
			return false;
		}
	}
	
	public Boolean checkTelegramIconOnProfileSection() {
		String parentWindowHandle = driver.getWindowHandle();
		try {
			Boolean data = telegramIconOnProfileSec.isDisplayed();
			extentTest.info("Telegram Is Visible : " + data);
			if(data == true) {
				telegramIconOnProfileSec.click();
				try {
					Set<String> windowHandles = driver.getWindowHandles();
					for (String windowHandle : windowHandles) {
						if (!windowHandle.equals(parentWindowHandle)) {
							driver.switchTo().window(windowHandle);
							Thread.sleep(2000);
							extentTest.info("Page URL : " + driver.getCurrentUrl());
							Thread.sleep(1000);
						}
					}
				} catch (Exception e) {
					extentTest.info("Page didnt got redirected..!!!");
				} finally {
					String title = driver.getTitle();
					if (!title.equals("Online Betting Site | Sports Betting in India | Fairplay")) {
						extentTest.info("Page redirected to : " + title);
						driver.close();
						driver.switchTo().window(parentWindowHandle);
						Thread.sleep(1000);
					} else {
						int i = 0;
						do {
							break;
						} while (i == 0);
					}
				}
			}
			return true;
		} catch (Exception e) {
			extentTest.info("Telegram Is Not Visible...!!!");
			return false;
		}
	}
	
	public Boolean checkTelegramIconOnReg2Page() {
		String parentWindowHandle = driver.getWindowHandle();
		try {
			Boolean data = telegramIconOnRegPage.isDisplayed();
			extentTest.info("TelegramIcon Is Displayed On Registration Page : " + data);
			if(data == true) {
				clickTelegramIconOnReg2Page.click();
				try {
					Set<String> windowHandles = driver.getWindowHandles();
					for (String windowHandle : windowHandles) {
						if (!windowHandle.equals(parentWindowHandle)) {
							driver.switchTo().window(windowHandle);
							Thread.sleep(2000);
							extentTest.info("Page URL : " + driver.getCurrentUrl());
							Thread.sleep(1000);
						}
					}
				} catch (Exception e) {
					extentTest.info("Page didnt got redirected..!!!");
				} finally {
					String title = driver.getTitle();
					if (!title.equals("Online Betting Site | Sports Betting in India | Fairplay")) {
						extentTest.info("Page redirected to : " + title);
						driver.close();
						driver.switchTo().window(parentWindowHandle);
						Thread.sleep(1000);
					} else {
						int i = 0;
						do {
							break;
						} while (i == 0);
					}
				}
			}
			return true;
		} catch (Exception e) {
			extentTest.info("TelegramIcon Is Not Displayed On Registration Page...!!!");
			return false;
		}
	}

}
