package model.pages;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import model.core.BasePage;

public class Movement extends BasePage{

	private MenuPage menuPage = new MenuPage();
	

	
	public void toCreateMovement(String account) {
		String actualDate = utils.obtainedDateFormated(new Date());
		String futureDate = utils.obtainedDateFormated(utils.obtainedDateWithDifferenceOfDays(10));
		try {
			menuPage.movementAccounts();
			utils.selectItemCombo("Receita", "tipo", "selectType");
			utils.click("data_transacao", "clickTransactionDate");
			utils.write("data_transacao",actualDate , "writeTransactionDate");
			utils.click("data_pagamento", "clickPaymentDate");
			utils.write("data_pagamento",futureDate, "writePaymentDate");
			utils.click("descricao", "clickDescription");
			utils.write("descricao","Movement Account", "writeTransactionDate");
			utils.click("interessado", "clickInterest");
			utils.write("interessado","Interest", "writeTransactionDate");
			utils.click("valor", "clickValue");
			utils.write("valor","500", "writeTransactionDate");
			utils.selectItemCombo(account, "conta", "selectAccount");
			utils.clickButton("Salvar", "saveMovement");
			assertEquals("Movimentação adicionada com sucesso!",utils.getAlertText("alert alert-success","alertMessage"));
		} catch (Exception e) {
			System.out.println("Não foi possível interagir com o elemento: " + e.getMessage());
		}
		
		
	}
	
}
