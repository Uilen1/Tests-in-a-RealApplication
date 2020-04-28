package model.core;

import static model.core.DriverFactory.driver;
import static model.core.DriverFactory.getDriver;

import java.awt.Toolkit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.WinDef;

import model.utilities.Utils;

public class BasePage {

	protected static Utils utils;
	protected static String EMAIL = "uilenlelles@hotmail.com";
	protected static String SENHA = "72598757*";

	public BasePage() {
		utils = new Utils();
	}

	protected void initialScreen() {
		getDriver().manage().window().maximize();
		getDriver().get("https://seubarriga.wcaquino.me");
	}

	public void scrolltoElement(WebElement element) {
		boolean condition = true;
		WebDriverWait wait = new WebDriverWait(getDriver(), 3);
		while (condition) {
			if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {

				executarJavascript(
						"arguments[0].scrollIntoView({behavior: 'instant', block: 'center',inline: 'center'})",
						element);
				condition = false;
				break;
			}
		}

	}

	public static void refresh() {
		getDriver().navigate().refresh();
	}

	public static Object executarJavascript(String cmd, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, params);
	}

	public static Boolean isPresent(WebElement element) {

		switch (getLocatorFromWebElement(element)) {
		case "id":
			return driver.findElements(By.id(getValueFromWebElement(element))).size() > 0;
		case "className":
			return driver.findElements(By.className(getValueFromWebElement(element))).size() > 0;
		case "tagName":
			return driver.findElements(By.tagName(getValueFromWebElement(element))).size() > 0;
		case "xpath":
			return driver.findElements(By.xpath(getValueFromWebElement(element))).size() > 0;
		case "cssSelector":
			return driver.findElements(By.cssSelector(getValueFromWebElement(element))).size() > 0;
		case "linkText":
			return driver.findElements(By.linkText(getValueFromWebElement(element))).size() > 0;
		case "name":
			return driver.findElements(By.name(getValueFromWebElement(element))).size() > 0;
		case "partialLinkText":
			return driver.findElements(By.partialLinkText(getValueFromWebElement(element))).size() > 0;
		default:
			throw new IllegalStateException("locator : " + getLocatorFromWebElement(element) + " not found!!!");
		}

	}

	public static String getLocatorFromWebElement(WebElement element) {
		String[] content = element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "").trim().split(": ");
		return content[0];
	}

	public static String getValueFromWebElement(WebElement element) {
		String[] content = element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "").trim().split(": ");
		return content[1];
	}

	public static double getScreenScale() {
		WinDef.HDC hdc = GDI32.INSTANCE.CreateCompatibleDC(null);
		if (hdc != null) {
			float actual = GDI32.INSTANCE.GetDeviceCaps(hdc, 10 /* VERTRES */);
			float logical = GDI32.INSTANCE.GetDeviceCaps(hdc, 117 /* DESKTOPVERTRES */);
			GDI32.INSTANCE.DeleteDC(hdc);
			if (logical != 0 && logical / actual >= 1) {
				return logical / actual;
			}
		}
		return (Toolkit.getDefaultToolkit().getScreenResolution() / 96.0f);
	}

}
