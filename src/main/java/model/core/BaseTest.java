package model.core;

import static model.core.DriverFactory.driver;
import static model.core.DriverFactory.getDriver;
import static model.core.DriverFactory.killDriver;
import static model.core.Properties.CLOSE_BROWNSER;

import java.awt.List;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.serialization.ClassNameMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.WinDef;

import model.pages.LoginPage;

public class BaseTest {

	
	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy--HH_mm_ss SSS");
	protected static Date timeStamps;
	protected static String executionTestName;
	protected static String evidencePath;
	protected static int evidenceCount;
	protected static String[] className;
	protected static String tableName;
	protected static String folderName;
	private LoginPage login;
	
	public BaseTest() {
		
	}
	
	public BaseTest(String executionTestName){
		BaseTest.executionTestName = executionTestName;

	}
	
	public static Date getTimeStamps() {
		timeStamps = new Date();
		return timeStamps;
	}
	
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "//driver//chromedriver.exe");
		evidencePath = System.getProperty("user.dir")+"/outPut/"+sdf.format(getTimeStamps());
		this.login = new LoginPage();
		login.setLogin();
		
	}
	
	@After
	public void afterTest() throws IOException, InterruptedException {
		
		evidenceCount = 0;
		
		if(CLOSE_BROWNSER) {
			killDriver();
		}
		
	}
	
	public static List<Object> loadData(){
		className = new Throwable().getStackTrace()[1].getClassName().toString().split("\\W");
		tableName = className[className.length-1];
		folderName = className[className.length - 2];
		return;
	}
	
	protected static void scrolltoElement(WebElement element) {
		executarJavascript("arguments[0].scrollIntoViewIfNeeded({behavior: 'instant', block: 'center',inline: 'center'})", element);
	}
	
	public static void refresh() {
		getDriver().navigate().refresh();
	}
	
	protected static Object executarJavascript(String cmd, Object... params) {
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
