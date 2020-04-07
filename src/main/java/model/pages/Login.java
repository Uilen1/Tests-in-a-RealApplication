package model.pages;

import model.core.BasePage;
import model.map.mapComponentes;

public class Login extends BasePage{
	
	public mapComponentes mapComponentes = new mapComponentes();

	public Login() {
		
	}
	
	public void setLogin() {
		initialScreen();
		utils.click("email", "clickEmail");
		utils.write("email", EMAIL, "writeEmail");
		utils.click("senha", "clickPassword");
		utils.write("senha", SENHA, "writePassword");
		utils.clickButton("Entrar", "enter");
	}
	
	
	
	
}