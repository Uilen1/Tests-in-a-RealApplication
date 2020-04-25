package model.pages;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import model.core.BasePage;
import model.map.MovementMap;
import model.utilities.GetScreenShoot;

public class MovementPage extends BasePage{

	private MenuPage menuPage = new MenuPage();
	private MovementMap movementMap = new MovementMap();
	

	
	public void toCreateMovement(String account) throws Exception{
		String actualDate = utils.obtainedDateFormated(new Date());
		String futureDate = utils.obtainedDateFormated(utils.obtainedDateWithDifferenceOfDays(10));
		try {
			menuPage.movementAccounts();
			selectItemCombo("Receita", "tipo", "selectType");
			click("data_transacao", "clickTransactionDate");
			write("data_transacao",actualDate , "writeTransactionDate");
			click("data_pagamento", "clickPaymentDate");
			write("data_pagamento",futureDate, "writePaymentDate");
			click("descricao", "clickDescription");
			write("descricao","Movement Account", "writeTransactionDate");
			click("interessado", "clickInterest");
			write("interessado","Interest", "writeTransactionDate");
			click("valor", "clickValue");
			write("valor","500", "writeTransactionDate");
			selectItemCombo(account, "conta", "selectAccount");
			clickButton("Salvar", "saveMovement");
			assertEquals("Movimentação adicionada com sucesso!",getAlertText("alert alert-success","alertMessage"));
		} catch (Exception e) {
			System.out.println("Não foi possível interagir com o elemento: \n");
			throw new Exception(e.getMessage());
		}
	}
	
	/************ Click ************/
	
	public void click(String elementToBeClickable) throws Exception {
		WebElement element = movementMap.elementInput(elementToBeClickable);
		try {
			element.click();
		} catch (Exception e) {

			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}
	}
	
	public void click(String elementToBeClickable, String nameStep) throws Exception {
		WebElement element = movementMap.elementInput(elementToBeClickable);
		try {
			element.click();
			GetScreenShoot.getEvidenceElement(nameStep, element);
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}
	}
	
	public void clickButton(String elementToBeClickable, String nameStep) throws Exception {
		WebElement element = movementMap.elementButton(elementToBeClickable);
		try {
			GetScreenShoot.getEvidenceElement(nameStep, element);
			element.click();
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}
	
	/************ Write ************/
	
	public void write(String element, String text, String nameStep) throws Exception {
		WebElement elements = movementMap.elementInput(element);

		try {
			elements.sendKeys(text);
			GetScreenShoot.getEvidenceElement(nameStep, elements);
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}
		
	/************ Obtained_Texts ************/
	
	public String getAlertText(String classAlert, String nameStep) throws Exception {
		WebElement element = movementMap.elementAlert(classAlert);
		try {
			GetScreenShoot.getEvidenceElement(nameStep, element);
			return element.getText();

		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}
	
	/************ Element_Radio ************/
	
	public void selectItemCombo(String item, String element,String nameStep) throws Exception {
		WebElement elementWeb = movementMap.elementSelected(element);
		try {
			scrolltoElement(elementWeb);
			Thread.sleep(1000);
			Select combo = new Select(elementWeb);
			combo.selectByVisibleText(item);
			GetScreenShoot.getEvidenceElement(nameStep, elementWeb);
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + elementWeb + "\n");
		}
		

	}	
}
