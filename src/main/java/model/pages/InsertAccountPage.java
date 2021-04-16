package model.pages;

import static org.junit.Assert.assertEquals;

import io.qameta.allure.Step;
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
		WebElement element = insertAccountMap.elementInput(elementToBeClickable);
		try {
			element.click();
		} catch (Exception e) {

			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}
	}

	public void click(String elementToBeClickable, String nameStep) throws Exception {
		WebElement element = insertAccountMap.elementInput(elementToBeClickable);
		try {
			element.click();
			GetScreenShoot.getEvidenceElement(nameStep, element);
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}

	public void clickButton(String elementToBeClickable, String nameStep) throws Exception {
		WebElement element = insertAccountMap.elementButton(elementToBeClickable);
		try {
			GetScreenShoot.getEvidenceElement(nameStep, element);
			element.click();
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}

	/************ Write ************/

	public void write(String element, String text, String nameStep) throws Exception {
		WebElement elements = insertAccountMap.elementInput(element);

		try {
			elements.sendKeys(text);
			GetScreenShoot.getEvidenceElement(nameStep, elements);
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}

	/************ Obtained_Texts ************/

	public String getAlertText(String classAlert, String nameStep) throws Exception {
		WebElement element = insertAccountMap.elementAlert(classAlert);
		try {
			GetScreenShoot.getEvidenceElement(nameStep, element);
			return element.getText();

		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}

}
