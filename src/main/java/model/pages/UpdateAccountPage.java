package model.pages;

import static org.junit.Assert.assertEquals;

import java.util.List;

import io.qameta.allure.Step;
import model.core.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.map.UpdateAccountMap;
import model.utilities.screenshoot.GetScreenShoot;

public class UpdateAccountPage extends BasePage {

	private MenuPage menuPage = new MenuPage();
	private UpdateAccountMap updateAccountMap = new UpdateAccountMap();

	@Step("Update an account: {updateAccount} to {nameAccount}")
	public void updateAccount(String updateAccount, String nameAccount) throws Exception{
		try {
			menuPage.listAccounts();
			clickElementTable("Conta", nameAccount, "tabelaContas", "Ações", "clickUpdate");
			click("nome", "clickInputAccount");
			write("nome", updateAccount, "writeAccount");
			clickButton("Salvar", "save");
			assertEquals("Conta alterada com sucesso!", getAlertText("alert alert-success", "alertMessage"));
		} catch (Exception e) {
			throw new Exception("Erro ao alterar a conta: \n" + e.getMessage());
		}

	}

	/************ Click ************/

	public void click(String elementToBeClickable) throws Exception{
		try {
			WebElement element = updateAccountMap.elementInput(elementToBeClickable);
			element.click();
		} catch (Exception e) {

			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}
	}

	public void click(String elementToBeClickable, String nameStep) throws Exception {
		
		try {
			WebElement element = updateAccountMap.elementInput(elementToBeClickable);
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

	public void clickButton(String elementToBeClickable, String nameStep) throws Exception{
		try {
			WebElement element = updateAccountMap.elementButton(elementToBeClickable);
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

	public void write(String element, String text, String nameStep) throws Exception{
		try {
			WebElement elements = updateAccountMap.elementInput(element);
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

	public String getAlertText(String classAlert, String nameStep) throws Exception{
		try {
			WebElement element = updateAccountMap.elementAlert(classAlert);
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

	/************ Tables ************/

	public WebElement getTable(String idTable) throws Exception{
		try {
			WebElement element = updateAccountMap.elementTable(idTable);

			return element;

		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + e.getMessage() + "\n");
		}

	}

	public int getColumn(String register, String idTable) throws Exception {
		int cont = -1;
		try {
			List<WebElement> column = getTable(idTable).findElements(By.xpath(".//th"));
			for (int i = 0; i < column.size(); i++) {
				if (column.get(i).getText().equals(register)) {
					cont = i + 1;
					break;
				}
			}
			return cont;
		} catch (Exception e) {
			throw new Exception("Não foi possível encontrar a coluna da tabela: \n");
		}
		
	}

	public int getRow(String register, int idColumn, String idTable) throws Exception {
		int cont = -1;
		try {
			getTable(idTable);
			List<WebElement> row = getTable(idTable).findElements(By.xpath("./tbody/tr/td[" + idColumn + "]"));
			for (int i = 0; i < row.size(); i++) {
				if (row.get(i).getText().equals(register)) {
					cont = i + 1;
					break;
				}
			}
			return cont;
		} catch (Exception e) {
			throw new Exception("Não foi possível encontrar a linha da tabela: \n");
		}
		
	}

	public WebElement ElementTable(String register, String nameAccount, String idTable, String nameColumnAction,
			String nameStep) throws Exception{
		
		try {
			int idColumn = getColumn(register, idTable);
			int idRow = getRow(nameAccount, idColumn, idTable);
			int idColumnButton = getColumn(nameColumnAction, idTable);
			return updateAccountMap.inputTable(idColumnButton, idRow, idTable);
		} catch (Exception e) {
			throw new Exception("Não foi possível retornar o elemento tabela: \n");
		}
		
		
		
	}

	public void clickElementTable(String register, String nameAccount, String idTable, String nameColumnAction,
			String nameStep) throws Exception {
		try {
			WebElement elementButton = ElementTable(register, nameAccount, idTable, nameColumnAction, nameStep);
			WebElement tableButton = elementButton.findElement(By.xpath(updateAccountMap.elementTableClick));
			if(!Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep, elementButton);
			}
			tableButton.click();
		} catch (Exception e) {
			if(Constants.SCREENSHOT_BY_EXCEPTION){
				GetScreenShoot.getEvidenceElement(nameStep);
			}
			throw new Exception("Não foi possível interagir com o elemento da tabela: \n" + e.getMessage());
		}
	
	}
}
