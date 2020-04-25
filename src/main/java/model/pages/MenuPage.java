package model.pages;

import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.map.MenuMap;
import model.utilities.GetScreenShoot;

public class MenuPage extends BasePage {

	private MenuMap menuMap = new MenuMap();

	public void setAccount() throws Exception {
		try {
			clickLink("Contas ", "clickAccount");
			clickLink("Adicionar", "ClickAdd");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void listAccounts() throws Exception {
		try {
			clickLink("Contas ", "clickAccount");
			clickLink("Listar", "ClickAdd");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void movementAccounts() throws Exception {
		try {
			clickLink("Criar Movimentação", "clickMovementAccount");

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void MonthlyResume() throws Exception {
		try {
			clickLink("Resumo Mensal", "clickMonthlyResume");

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/************ Click ************/

	public void clickLink(String name, String nameStep) throws Exception {
		WebElement element = menuMap.elementLink(name);

		try {
			element.click();
			GetScreenShoot.getEvidenceElement(nameStep, element);

		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}

}
