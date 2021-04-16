package model.pages;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import io.qameta.allure.Step;
import model.core.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import model.core.BasePage;
import model.map.MovementMap;
import model.utilities.screenshoot.GetScreenShoot;

public class MovementPage extends BasePage{

	private MenuPage menuPage = new MenuPage();
	private MovementMap movementMap = new MovementMap();


	@Step("Create a movement to account: {account}")
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
			throw new Exception("Erro ao movimentar a conta: \n" + e.getMessage());
		}
	}
	
	/************ Click ************/
	
	public void click(String elementToBeClickable) throws Exception {
		try {
			WebElement element = movementMap.elementInput(elementToBeClickable);
			element.click();
		} catch (Exception e) {

			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}
	}
	
	public void click(String elementToBeClickable, String nameStep) throws Exception {
		try {
			WebElement element = movementMap.elementInput(elementToBeClickable);
			element.click();
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, element);
			}
		} catch (Exception e) {
			if(Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep);
			}
			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}
	}
	
	public void clickButton(String elementToBeClickable, String nameStep) throws Exception {
		try {
			WebElement element = movementMap.elementButton(elementToBeClickable);
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
			WebElement elements = movementMap.elementInput(element);
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
			WebElement element = movementMap.elementAlert(classAlert);
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, element);
			}
			return element.getText();

		} catch (Exception e) {
			if(Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep);
			}
			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}

	}
	
	/************ Element_Radio ************/
	
	public void selectItemCombo(String item, String element,String nameStep) throws Exception {
		try {
			WebElement elementWeb = movementMap.elementSelected(element);
			scrolltoElement(elementWeb);
			Thread.sleep(1000);
			Select combo = new Select(elementWeb);
			combo.selectByVisibleText(item);
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, elementWeb);
			}
		} catch (Exception e) {
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep);
			}
			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}
		

	}	
}
