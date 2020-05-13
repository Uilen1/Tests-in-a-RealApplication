package model.pages;

import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.map.MenuMap;
import model.utilities.screenshoot.GetScreenShoot;

public class MenuPage extends BasePage {

	private MenuMap menuMap = new MenuMap();

	public void setAccount() throws Exception {
		try {
			clickLink("Contas ", "clickAccount");
			clickLink("Adicionar", "ClickAdd");
		} catch (Exception e) {
			throw new Exception("Erro ao adicionar uma conta: \n" + e.getMessage());
		}
	}

	public void listAccounts() throws Exception {
		try {
			clickLink("Contas ", "clickAccount");
			clickLink("Listar", "ClickAdd");
		} catch (Exception e) {
			throw new Exception("Erro ao listar as contas: \n" + e.getMessage());
		}
	}

	public void movementAccounts() throws Exception {
		try {
			clickLink("Criar Movimentação", "clickMovementAccount");

		} catch (Exception e) {
			throw new Exception("Erro ao movimentar a conta: \n" + e.getMessage());
		}

	}

	public void MonthlyResume() throws Exception {
		try {
			clickLink("Resumo Mensal", "clickMonthlyResume");

		} catch (Exception e) {
			throw new Exception("Erro no resumo mensal da conta: \n" + e.getMessage());
		}
	}

	/************ Click ************/

	public void clickLink(String name, String nameStep) throws Exception {
		WebElement element = menuMap.elementLink(name);

		try {
			GetScreenShoot.getEvidenceElement(nameStep, element);
			element.click();
		} catch (Exception e) {
			throw new Exception("Não foi possível interagir com o elemento: " + element + "\n");
		}

	}

}
