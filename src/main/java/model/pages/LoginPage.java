package model.pages;

import model.core.BasePage;
import model.map.LoginMap;
import model.utilities.GetScreenShoot;

public class LoginPage extends BasePage{
	
	private LoginMap loginMap = new LoginMap();

	public LoginPage() {
		
	}
	
	public void setLogin() {
		try {
			initialScreen();
			click("email", "clickEmail");
			write("email", EMAIL, "writeEmail");
			click("senha", "clickPassword");
			write("senha", SENHA, "writePassword");
			clickButton("Entrar", "enter");
		} catch (Exception e) {
			System.out.println("Não foi possível interagir com o elemento: " + e.getMessage());
		}
		
	}
	
	/************ Click ************/
	
	public void clickButton(String elementToBeClickable, String nameStep) {
		GetScreenShoot.getEvidenceElement(nameStep, loginMap.elementButton(elementToBeClickable));
		loginMap.elementButton(elementToBeClickable).click();

	}
	
	public void click(String elementToBeClickable) {
		loginMap.elementInput(elementToBeClickable).click();
	}

	public void click(String elementToBeClickable, String nameStep) {
		loginMap.elementInput(elementToBeClickable).click();
		GetScreenShoot.getEvidenceElement(nameStep, loginMap.elementInput(elementToBeClickable));

	}
	
	/************ Write ************/
	
	public void write(String element, String text,String nameStep) {
		loginMap.elementInput(element).sendKeys(text);
		GetScreenShoot.getEvidenceElement(nameStep, loginMap.elementInput(element));

	}
	
	
}
