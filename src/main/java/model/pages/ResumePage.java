package model.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import model.core.BasePage;
import model.map.ResumeMap;
import model.utilities.GetScreenShoot;

public class ResumePage extends BasePage {

	private MenuPage menuPage = new MenuPage();
	private ResumeMap resumeMap = new ResumeMap();

	public void deleteMovementAccount() throws Exception{
		String futureDate = utils.obtainedDateFormated(utils.obtainedDateWithDifferenceOfDays(10));
		try {
			menuPage.MonthlyResume();
			assertTrue(obtainedItemCombo("Abril", "mes", "MonthSelected"));
			assertTrue(obtainedItemCombo("2020", "ano", "YearSelected"));
			clickElementTable("Dt Pagamento", futureDate, "tabelaExtrato", "Ações", "clickDeleteMovementAccount");
			assertEquals("Movimentação removida com sucesso!", getAlertText("alert alert-success", "alertMessage"));

		} catch (Exception e) {
			System.out.println("Não foi possível interagir com o elemento: \n");
			throw new Exception(e.getMessage());
		}
	}

	/************ Element_Radio ************/

	public boolean obtainedItemCombo(String item, String idElement, String nameStep) throws Exception {
		WebElement elementWeb = resumeMap.elementSelected(idElement);
		Select combo = new Select(elementWeb);
		List<WebElement> options = combo.getOptions();
		boolean finded = false;

		for (WebElement option : options) {
			if (option.getText().equals(item)) {
				GetScreenShoot.getEvidenceElement(nameStep, elementWeb);
				finded = true;
				break;
			}
		}

		return finded;
	}

	/************ Obtained_Texts ************/

	public String getAlertText(String classAlert, String nameStep) throws Exception {
		WebElement element = resumeMap.elementAlert(classAlert);
		GetScreenShoot.getEvidenceElement(nameStep, element);
		return element.getText();
	}

	/************ Tables ************/

	public WebElement getTable(String idTable) throws Exception {
		return resumeMap.elementTable(idTable);

	}

	public int getColumn(String register, String idTable) throws Exception {
		int cont = -1;
		List<WebElement> column = getTable(idTable).findElements(By.xpath(".//th"));
		for (int i = 0; i < column.size(); i++) {
			if (column.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}
		}
		return cont;
	}

	public int getRow(String register, int idColumn, String idTable) throws Exception {
		int cont = -1;
		getTable(idTable);
		List<WebElement> row = getTable(idTable).findElements(By.xpath("./tbody/tr/td[" + idColumn + "]"));
		for (int i = 0; i < row.size(); i++) {
			if (row.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}
		}
		return cont;
	}

	public WebElement ElementTable(String register, String nameAccount, String idTable, String nameColumnAction,
			String nameStep) throws Exception {
		int idColumn = getColumn(register, idTable);
		int idRow = getRow(nameAccount, idColumn, idTable);
		int idColumnButton = getColumn(nameColumnAction, idTable);
		return resumeMap.inputTable(idColumnButton, idRow, idTable);
	}

	public void clickElementTable(String register, String nameAccount, String idTable, String nameColumnAction,
			String nameStep) throws Exception{
		WebElement elementButton = ElementTable(register, nameAccount, idTable, nameColumnAction, nameStep);
		WebElement tableButton = elementButton.findElement(By.xpath(resumeMap.elementTableClick));
		GetScreenShoot.getEvidenceElement(nameStep, elementButton);
		tableButton.click();
	}
}
