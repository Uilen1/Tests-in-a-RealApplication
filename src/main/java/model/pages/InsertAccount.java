package model.pages;

import model.core.BasePage;

public class InsertAccount extends BasePage{
	
	private MenuPage menuPage = new MenuPage();
	
	public void insertAccount(String account) {
		menuPage.setAccount();
		utils.click("nome", "clickInputAccount");
		utils.write("nome", account, "writeAccount");
		utils.clickButton("Salvar", "save");		
		
	}
	

}
