package model.pages;

import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.map.LoginMap;
import model.utilities.GetScreenShoot;

public class LoginPage extends BasePage {

	private LoginMap loginMap = new LoginMap();

	public LoginPage() {

	}

	public void setLogin() throws Exception {
		try {
			initialScreen();
			click("email", "clickEmail");
			write("email", EMAIL, "writeEmail");
			click("senha", "clickPassword");
			write("senha", SENHA, "writePassword");
			clickButton("Entrar", "enter");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/************ Click ************/

	public void clickButton(String elementToBeClickable, String nameStep) throws Exception {
		WebElement element = loginMap.elementButton(elementToBeClickable);
		try {
			GetScreenShoot.getEvidenceElement(nameStep, element);
			element.click();
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}
	}

	public void click(String elementToBeClickable) throws Exception {
		WebElement element = loginMap.elementInput(elementToBeClickable);
		try {
			element.click();
		} catch (Exception e) {

			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}
	}

	public void click(String elementToBeClickable, String nameStep) throws Exception {
		WebElement element = loginMap.elementInput(elementToBeClickable);
		try {
			element.click();
			GetScreenShoot.getEvidenceElement(nameStep, element);
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}

	/************ Write ************/

	public void write(String element, String text, String nameStep) throws Exception {
		WebElement elements = loginMap.elementInput(element);

		try {
			elements.sendKeys(text);
			GetScreenShoot.getEvidenceElement(nameStep, elements);
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}
}
