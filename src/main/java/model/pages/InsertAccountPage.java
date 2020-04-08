package model.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.map.InsertAccountMap;
import model.utilities.GetScreenShoot;

public class InsertAccountPage extends BasePage{
	
	private MenuPage menuPage = new MenuPage();
	private InsertAccountMap insertAccountMap = new InsertAccountMap();
	
	public void insertAccount(String account) {
		try {
			menuPage.setAccount();
			click("nome", "clickInputAccount");
			write("nome", account, "writeAccount");
			clickButton("Salvar", "save");
			assertEquals("Conta adicionada com sucesso!", utils.getAlertText("alert alert-success","alertMessage"));

		} catch (Exception e) {
			System.out.println("Não foi possível interagir com o elemento: " + e.getMessage());
		}
						
	}
	
	public void checkExceptions(String account) {
		try {
			
			menuPage.setAccount();
			click("nome", "clickInputAccount");
			write("nome", account, "writeAccount");
			clickButton("Salvar", "save");
			assertEquals("Já existe uma conta com esse nome!", getAlertText("alert alert-danger","alertMessage"));
			
		} catch (Exception e) {
			System.out.println("Não foi possivel interagir com o elemento: " + e.getMessage());
		}
	}
	
	/************ Click ************/
	
	public void click(String elementToBeClickable) {
		insertAccountMap.elementInput(elementToBeClickable).click();
	}

	public void click(String elementToBeClickable, String nameStep) {
		insertAccountMap.elementInput(elementToBeClickable).click();
		GetScreenShoot.getEvidenceElement(nameStep,insertAccountMap.elementInput(elementToBeClickable));

	}
	
	public void clickButton(String elementToBeClickable, String nameStep) {
		GetScreenShoot.getEvidenceElement(nameStep, insertAccountMap.elementButton(elementToBeClickable));
		insertAccountMap.elementButton(elementToBeClickable).click();

	}
	
	/************ Write ************/
	
	public void write(String element, String text,String nameStep) {
		insertAccountMap.elementInput(element).sendKeys(text);
		GetScreenShoot.getEvidenceElement(nameStep, insertAccountMap.elementInput(element));

	}
	
	/************ Obtained_Texts ************/
	
	public String getAlertText(String classAlert, String nameStep) {
		WebElement element = insertAccountMap.elementAlert(classAlert);
		GetScreenShoot.getEvidenceElement(nameStep, element);

		return element.getText();
	}
	

}
