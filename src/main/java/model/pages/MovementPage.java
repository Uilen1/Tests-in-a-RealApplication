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
	

	
	public void toCreateMovement(String account) {
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
			System.out.println("Não foi possível interagir com o elemento: " + e.getMessage());
		}
	}
	
	/************ Click ************/
	
	public void click(String elementToBeClickable) {
		movementMap.elementInput(elementToBeClickable).click();
	}

	public void click(String elementToBeClickable, String nameStep) {
		movementMap.elementInput(elementToBeClickable).click();
		GetScreenShoot.getEvidenceElement(nameStep, movementMap.elementInput(elementToBeClickable));

	}
	
	public void clickButton(String elementToBeClickable, String nameStep) {
		GetScreenShoot.getEvidenceElement(nameStep, movementMap.elementButton(elementToBeClickable));
		movementMap.elementButton(elementToBeClickable).click();

	}
	
	/************ Write ************/
	
	public void write(String element, String text,String nameStep) {
		movementMap.elementInput(element).sendKeys(text);
		GetScreenShoot.getEvidenceElement(nameStep, movementMap.elementInput(element));

	}
	
	/************ Obtained_Texts ************/
	
	public String getAlertText(String classAlert, String nameStep) {
		WebElement element = movementMap.elementAlert(classAlert);
		GetScreenShoot.getEvidenceElement(nameStep, element);

		return element.getText();
	}
	
	/************ Element_Radio ************/
	
	public void selectItemCombo(String item, String element,String nameStep) {
		WebElement elementWeb = movementMap.elementSelected(element);
		Select combo = new Select(elementWeb);
		combo.selectByVisibleText(item);
		GetScreenShoot.getEvidenceElement(nameStep, elementWeb);

	}	
}
