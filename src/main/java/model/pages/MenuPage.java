package model.pages;

import model.core.BasePage;

public class MenuPage extends BasePage{

	public void setAccount() {
		try {
			utils.clickLink("Contas ", "clickAccount");
			utils.clickLink("Adicionar", "ClickAdd");
		} catch (Exception e) {
			System.out.println("Não foi possível interagir com o elemento: "+e.getMessage());
		}
	}
	
	public void listAccounts() {
		try {
			utils.clickLink("Contas ", "clickAccount");
			utils.clickLink("Listar", "ClickAdd");
		} catch (Exception e) {
			System.out.println("Não foi possível interagir com o elemento: "+e.getMessage());
		}
	}
	
	public void movementAccounts() {
		try {
			utils.clickLink("Criar Movimentação", "clickMovementAccount");
			
		} catch (Exception e) {
			System.out.println("Não foi possível interagir com o elemento: " + e.getMessage());
		}
		
	}
	
	
	
}
