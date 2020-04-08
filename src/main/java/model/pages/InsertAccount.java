package model.pages;

import static org.junit.Assert.assertEquals;

import model.core.BasePage;

public class InsertAccount extends BasePage{
	
	private MenuPage menuPage = new MenuPage();
	
	public void insertAccount(String account) {
		try {
			menuPage.setAccount();
			utils.click("nome", "clickInputAccount");
			utils.write("nome", account, "writeAccount");
			utils.clickButton("Salvar", "save");
			assertEquals("Conta adicionada com sucesso!", utils.getAlertText("alert alert-success","alertMessage"));

		} catch (Exception e) {
			System.out.println("Não foi possível interagir com o elemento: " + e.getMessage());
		}
						
	}
	
	public void checkExceptions(String account) {
		try {
			
			menuPage.setAccount();
			utils.click("nome", "clickInputAccount");
			utils.write("nome", account, "writeAccount");
			utils.clickButton("Salvar", "save");
			assertEquals("Já existe uma conta com esse nome!", utils.getAlertText("alert alert-danger","alertMessage"));
			
		} catch (Exception e) {
			System.out.println("Não foi possivel interagir com o elemento: " + e.getMessage());
		}
	}

}
