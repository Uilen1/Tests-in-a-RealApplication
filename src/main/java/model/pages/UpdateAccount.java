package model.pages;

import static org.junit.Assert.assertEquals;

import model.core.BasePage;

public class UpdateAccount extends BasePage{

	private MenuPage menuPage = new MenuPage();
	
	public void updateAccount(String account) {
		menuPage.listAccounts();
		utils.clickElementTable("Conta", "Uilen Helei", "tabelaContas","Ações", "clickUpdate");
		utils.click("nome", "clickInputAccount");
		utils.write("nome", account, "writeAccount");
		utils.clickButton("Salvar", "save");	
		assertEquals("Conta alterada com sucesso!",utils.getAlertText("alert alert-success","alertMessage"));

	}
}
