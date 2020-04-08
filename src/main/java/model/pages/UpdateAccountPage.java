package model.pages;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.map.UpdateAccountMap;
import model.utilities.GetScreenShoot;

public class UpdateAccountPage extends BasePage{

	private MenuPage menuPage = new MenuPage();
	private UpdateAccountMap updateAccountMap = new UpdateAccountMap();
	
	public void updateAccount(String account) {
		menuPage.listAccounts();
		clickElementTable("Conta", "Uilen Helei", "tabelaContas","Ações", "clickUpdate");
		click("nome", "clickInputAccount");
		write("nome", account, "writeAccount");
		clickButton("Salvar", "save");	
		assertEquals("Conta alterada com sucesso!",getAlertText("alert alert-success","alertMessage"));

	}
	
	/************ Click ************/
	
	public void click(String elementToBeClickable) {
		updateAccountMap.elementInput(elementToBeClickable).click();
	}

	public void click(String elementToBeClickable, String nameStep) {
		updateAccountMap.elementInput(elementToBeClickable).click();
		GetScreenShoot.getEvidenceElement(nameStep, updateAccountMap.elementInput(elementToBeClickable));

	}
	
	public void clickButton(String elementToBeClickable, String nameStep) {
		GetScreenShoot.getEvidenceElement(nameStep, updateAccountMap.elementButton(elementToBeClickable));
		updateAccountMap.elementButton(elementToBeClickable).click();

	}
	
	/************ Write ************/
	
	public void write(String element, String text,String nameStep) {
		updateAccountMap.elementInput(element).sendKeys(text);
		GetScreenShoot.getEvidenceElement(nameStep, updateAccountMap.elementInput(element));

	}
	
	/************ Obtained_Texts ************/
	
	public String getAlertText(String classAlert, String nameStep) {
		WebElement element = updateAccountMap.elementAlert(classAlert);
		GetScreenShoot.getEvidenceElement(nameStep, element);

		return element.getText();
	}
	
	/************ Tables ************/
	
	public WebElement getTable(String idTable) {
			return updateAccountMap.elementTable(idTable);

	}
	
	public int getColumn(String register,String idTable) {
		int cont = -1;
		List<WebElement> column = getTable(idTable).findElements(By.xpath(".//th"));
		for(int i = 0; i < column.size(); i++ ) {
			if(column.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}			
		}		
		return cont;
	}
	
	public int getRow(String register, int idColumn,String idTable) {
		int cont = -1;
		getTable(idTable);
		List<WebElement> row = getTable(idTable).findElements(By.xpath("./tbody/tr/td["+idColumn+"]"));
		for(int i = 0; i < row.size(); i++ ) {
			if(row.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}			
		}		
		return cont;
	}
	
	public WebElement ElementTable(String register,String nameAccount, String idTable,String nameColumnAction,String nameStep) {
		int idColumn = getColumn(register, idTable);
		int idRow = getRow(nameAccount, idColumn, idTable);
		int idColumnButton = getColumn(nameColumnAction, idTable);
		return updateAccountMap.inputTable(idColumnButton, idRow, idTable);		
	}
	
	public void clickElementTable(String register,String nameAccount, String idTable,String nameColumnAction, String nameStep ) {
		WebElement elementButton = ElementTable(register, nameAccount, idTable,nameColumnAction, nameStep);
		WebElement tableButton = elementButton.findElement(By.xpath(updateAccountMap.elementTableClick));
		GetScreenShoot.getEvidenceElement(nameStep, elementButton);
		tableButton.click();
	}
}
