package model.pages;

import model.core.BasePage;
import model.map.MenuMap;
import model.utilities.GetScreenShoot;

public class MenuPage extends BasePage{
	
	private MenuMap menuMap = new MenuMap();

	public void setAccount() {
		try {
			clickLink("Contas ", "clickAccount");
			clickLink("Adicionar", "ClickAdd");
		} catch (Exception e) {
			System.out.println("N�o foi poss�vel interagir com o elemento: "+e.getMessage());
		}
	}
	
	public void listAccounts() {
		try {
			clickLink("Contas ", "clickAccount");
			clickLink("Listar", "ClickAdd");
		} catch (Exception e) {
			System.out.println("N�o foi poss�vel interagir com o elemento: "+e.getMessage());
		}
	}
	
	public void movementAccounts() {
		try {
			clickLink("Criar Movimenta��o", "clickMovementAccount");
			
		} catch (Exception e) {
			System.out.println("N�o foi poss�vel interagir com o elemento: " + e.getMessage());
		}
		
	}
	
	public void MonthlyResume() {
		try {
			clickLink("Resumo Mensal", "clickMonthlyResume");
			
		} catch (Exception e) {
			System.out.println("N�o Foi poss�vel interagir com o elemento: " + e.getMessage());
		}
	}
	
	/************ Click ************/
	
	public void clickLink(String name, String nameStep) {
		GetScreenShoot.getEvidenceElement(nameStep, menuMap.elementLink(name));
		menuMap.elementLink(name).click();
	}
	
	
	
}
