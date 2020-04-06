package model.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public static WebDriver driver;

	
	public DriverFactory() {
	
	}

	public static WebDriver getDriver() {
		
		if (driver == null) {
			switch (Properties.brownser) {
			case FIREFOX:
			driver = new FirefoxDriver();
			break;
			
			case CHROME:
			driver = new ChromeDriver();
			break;
			}
			
		}
		return driver;

	}

	public static void killDriver() {

		if (driver != null) {
			 driver.close();
	         driver.quit();
	         driver = null;
		}			

	}

}
