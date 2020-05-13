package model.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import model.core.BasePage;
import model.map.ResumeMap;
import model.utilities.screenshoot.GetScreenShoot;

public class ResumePage extends BasePage {

	private MenuPage menuPage = new MenuPage();
	private ResumeMap resumeMap = new ResumeMap();

	public void deleteMovementAccount() throws Exception {
		String futureDate = utils.obtainedDateFormated(utils.obtainedDateWithDifferenceOfDays(10));
		try {
			menuPage.MonthlyResume();
			assertTrue(obtainedItemCombo("Abril", "mes", "MonthSelected"));
			assertTrue(obtainedItemCombo("2020", "ano", "YearSelected"));
			clickElementTable("Dt Pagamento", futureDate, "tabelaExtrato", "Ações", "clickDeleteMovementAccount");
			assertEquals("Movimentação removida com sucesso!", getAlertText("alert alert-success", "alertMessage"));

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/************ Element_Radio ************/

	public boolean obtainedItemCombo(String item, String idElement, String nameStep) throws Exception {
		WebElement elementWeb = resumeMap.elementSelected(idElement);
		Select combo = new Select(elementWeb);
		List<WebElement> options = combo.getOptions();
		boolean finded = false;

		try {
			for (WebElement option : options) {
				if (option.getText().equals(item)) {
					GetScreenShoot.getEvidenceElement(nameStep, elementWeb);
					finded = true;
					break;
				}
			}
			return finded;
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + elementWeb + "\n");
		}
		
	}

	/************ Obtained_Texts ************/

	public String getAlertText(String classAlert, String nameStep) throws Exception{
		WebElement element = resumeMap.elementAlert(classAlert);
		try {
			GetScreenShoot.getEvidenceElement(nameStep, element);
			return element.getText();

		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}
		
	}

	/************ Tables ************/

	public WebElement getTable(String idTable) throws Exception {
		WebElement element = resumeMap.elementTable(idTable);
		try {

			return element;

		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
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
			String nameStep) throws Exception {

		try {
			int idColumn = getColumn(register, idTable);
			int idRow = getRow(nameAccount, idColumn, idTable);
			int idColumnButton = getColumn(nameColumnAction, idTable);
			return resumeMap.inputTable(idColumnButton, idRow, idTable);
		} catch (Exception e) {
			throw new Exception("Não foi possível retornar o elemento tabela: \n");
		}

	}

	public void clickElementTable(String register, String nameAccount, String idTable, String nameColumnAction,
			String nameStep) throws Exception {
		WebElement elementButton = ElementTable(register, nameAccount, idTable, nameColumnAction, nameStep);
		WebElement tableButton = elementButton.findElement(By.xpath(resumeMap.elementTableClick));
		try {

			GetScreenShoot.getEvidenceElement(nameStep, elementButton);
			tableButton.click();
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento da tabela: \n" + tableButton);
		}

	}

}