package model.pages;

import static model.core.Constants.EMAIL;
import static model.core.Constants.SENHA;

import io.qameta.allure.Step;
import model.core.Constants;
import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.map.LoginMap;
import model.utilities.screenshoot.GetScreenShoot;

public class LoginPage extends BasePage {

	private LoginMap loginMap = new LoginMap();

	public LoginPage() {

	}
	@Step("Perform the login in the platform")
	public void setLogin() throws Exception {
		try {
			initialScreen();
			click("email", "clickEmail");
			write("email", EMAIL, "writeEmail");
			click("senha", "clickPassword");
			write("senha", SENHA, "writePassword");
			clickButton("Entrar", "enter");
		} catch (Exception e) {
			throw new Exception("Erro ao realizar o login: \n" + e.getMessage());
		}

	}

	/************ Click ************/

	public void clickButton(String elementToBeClickable, String nameStep) throws Exception {
		WebElement element = loginMap.elementButton(elementToBeClickable);
		try {
			element.click();
		} catch (Exception e) {
			GetScreenShoot.getEvidenceElement(nameStep, element);
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
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, element);
			}
			element.click();
		} catch (Exception e) {
			if(Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, element);
			}
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}

	/************ Write ************/

	public void write(String element, String text, String nameStep) throws Exception {
		WebElement elements = loginMap.elementInput(element);

		try {
			elements.sendKeys(text);
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, elements);
			}
		} catch (Exception e) {
			if(Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, elements);
			}
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}
}
