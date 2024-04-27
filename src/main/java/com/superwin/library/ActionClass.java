package com.superwin.library;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
/*
 * @Author: Tejal Gavade.
 * @Since : November 2022
 * @Discription : This class contains different methods to perform mouse actions with the help of action class and JavascriptExecutor.
 */

public class ActionClass {

	public void jSForClick(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}

	public void jSForScroll(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}

	public void jSForScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1385, -571)", " ");
	}
	
	public void jsForScrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void selectIndex(WebElement ele, int i) {
		Select sel = new Select(ele);
		sel.selectByIndex(i);
	}

	public void selectValue(WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByValue(value);
	}

	public void selectVisibleText(WebElement ele, String text) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(text);
	}

	public void doubleClick_delete(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).sendKeys(Keys.DELETE).perform();
	}

	public void deleteall(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.keyDown(element, Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build().perform();
	}

	public void moveToElememt(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void clickandHold(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.clickAndHold(element).perform();
	}

	public void dragAndDrop(WebDriver driver, WebElement source, WebElement destination) {
		Actions act = new Actions(driver);
		act.dragAndDrop(source, destination);
	}

	public void moveToElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void contextClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

	public void click(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.click(element).perform();
	}
	
	public void paste(WebDriver driver) {
		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).build().perform();
		
	}
	
	private static final String String = null;

//	public void jSForClick(WebDriver driver, WebElement ele) { // used
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", ele);
//	}
//
//	public void jSForScroll(WebDriver driver, WebElement ele) { // used
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView();", ele);
//	}

	public void jSForScrollDown(WebDriver driver) { // Used
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,550)", "");
	}

	public void explicitWaitClickable(WebDriver driver, WebElement element) { // used
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
		ele.click();
	}
     
	public void dfghj(WebDriver driver, String xpath) {   
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}
	public void clearTextField(WebDriver driver, WebElement element) {
		Actions actObj = new Actions(driver);
//		actObj.keyDown(element, Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build().perform();
		actObj.doubleClick(element).sendKeys(Keys.DELETE).perform();
		actObj.moveToElement(element).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
	}

	public String verifyAccessToken(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		@SuppressWarnings("static-access")
		String localStorage = (String) jsExecutor
				.executeScript(String.format("return window.localStorage.getItem('%s');", "auth._token.customLocal"));
//		 String localStorage= (String) jsExecutor.executeScript(java.lang.String.format("return window.localStorage.getItem('%s');", "_grecaptcha"));
		return localStorage;
	}

	public void explicitVisiblility(WebDriver driver, WebElement element, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOf((element)));
	}

	public void explicitWaitElementClickable(WebDriver driver, WebElement element) { // used for bookmaker
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
		ele.click();
	}

	public void textToBePresentInElement(WebDriver driver, WebElement elem, String text, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.textToBePresentInElement(elem, text));
	}

	public void visibilityOf(WebDriver driver, WebElement elem, int timeout) { // Used
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(elem));
	}

	public void explicitWaitVisible(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("Element visible");
		} catch (Exception e) {
			System.out.println("Element not visible");
		}
	}

	public void explicitWaitAllElementVisibility(WebDriver driver, List<WebElement> element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
			System.out.println("");
		} catch (Exception e) {
			System.out.println("");
		}
	}

	public void fluentWait(WebDriver driver, WebElement element, int timeOut) {
		Wait<WebDriver> wait = null;
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(30))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void visibilityOfElementLocated(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		element.click();
	}

	public void elementToBeSelected(WebDriver driver, WebElement elem, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.elementToBeSelected(elem));
	}

	public void invisibilityOf(WebDriver driver, WebElement element, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void elementSelectionStateToBe(WebDriver driver, WebElement elem, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.elementSelectionStateToBe(elem, true));
	}

	public void textToBePresentInElementValue(WebDriver driver, WebElement elem, String text, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.textToBePresentInElementValue(elem, text));
	}
	
//
//	public static void gf() {
//		String str = "Tejal Gavade";
//		if (str.contains(" ")) {
//			str = str.replaceAll("\\s+", "");
//			System.out.println(str);
//		}
//	}

//	public static void main(String[] args) {
//		String str = "7249153969";
//		//String msg = String.join("+91"+str);
//		String op = "+91"+str;
//		System.out.println(op);
//	}

}
