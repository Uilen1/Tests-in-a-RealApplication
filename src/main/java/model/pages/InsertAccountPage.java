package model.pages;

import static org.junit.Assert.assertEquals;

import io.qameta.allure.Step;
import model.core.Constants;
import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.map.InsertAccountMap;
import model.utilities.screenshoot.GetScreenShoot;

public class InsertAccountPage extends BasePage {

	private MenuPage menuPage = new MenuPage();
	private InsertAccountMap insertAccountMap = new InsertAccountMap();

	@Step("Insert an account: {account}")
	public void insertAccount(String account) throws Exception {
		try {
			menuPage.setAccount();
			click("nome", "clickInputAccount");
			write("nome", account, "writeAccount");
			clickButton("Salvar", "save");
			assertEquals("Conta adicionada com sucesso!", utils.getAlertText("alert alert-success", "alertMessage"));

		} catch (Exception e) {
			throw new Exception("Erro ao inserir uma conta: \n" + e.getMessage());
		}

	}

	@Step("Check an account: {account}")
	public void checkExceptions(String account) throws Exception {
		try {

			menuPage.setAccount();
			click("nome", "clickInputAccount");
			write("nome", account, "writeAccount");
			clickButton("Salvar", "save");
			assertEquals("Já existe uma conta com esse nome!", getAlertText("alert alert-danger", "alertMessage"));

		} catch (Exception e) {
			throw new Exception("Erro ao checar o alerta: \n" + e.getMessage());
		}
	}

	/************ Click ************/

	public void click(String elementToBeClickable) throws Exception {
		try {
			WebElement element = insertAccountMap.elementInput(elementToBeClickable);
			element.click();
		} catch (Exception e) {

			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}
	}

	public void click(String elementToBeClickable, String nameStep) throws Exception {
		try {
			WebElement element = insertAccountMap.elementInput(elementToBeClickable);
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, element);
			}
			element.click();
		} catch (Exception e) {
			if(Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep);
			}
			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}

	}

	public void clickButton(String elementToBeClickable, String nameStep) throws Exception {
		try {
			WebElement element = insertAccountMap.elementButton(elementToBeClickable);
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, element);
			}
			element.click();
		} catch (Exception e) {
			if(Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep);
			}
			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}

	}

	/************ Write ************/

	public void write(String element, String text, String nameStep) throws Exception {

		try {
			WebElement elements = insertAccountMap.elementInput(element);
			elements.sendKeys(text);
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, elements);
			}
		} catch (Exception e) {
			if(Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep);
			}
			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}

	}

	/************ Obtained_Texts ************/

	public String getAlertText(String classAlert, String nameStep) throws Exception {
		try {
			WebElement element = insertAccountMap.elementAlert(classAlert);
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, element);
			}
			return element.getText();

		} catch (Exception e) {
			if(Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep);
			}
			throw new Exception("Não foi possível interagir com o elemento: \n");
		}

	}

}
