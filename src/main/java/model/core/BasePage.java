package model.core;

import static model.core.DriverFactory.getDriver;

import model.utilities.Utils;

public class BasePage {

	protected static Utils utils;
	protected static String EMAIL = "uilenlelles@hotmail.com";
	protected static String SENHA = "72598757*";

	public BasePage() {
		utils = new Utils();
	}
	
	protected void initialScreen() {
		getDriver().get("https://seubarriga.wcaquino.me");
	}
	

}
