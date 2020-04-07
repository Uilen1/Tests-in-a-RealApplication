package model.utilities;



import static model.core.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import model.map.mapComponentes;

public class Utils {
	
	public GetScreenShoot getScreenShoot = new GetScreenShoot();
	
	/************ Click ************/
	
	public void click(String elementToBeClickable) {
		getDriver().findElement(By.xpath(mapComponentes.elementInput(elementToBeClickable))).click();
	}

	public void click(String elementToBeClickable, String nameStep) {
		getDriver().findElement(By.xpath(mapComponentes.elementInput(elementToBeClickable))).click();
		GetScreenShoot.getEvidenceElement(nameStep, getDriver().findElement(By.xpath(mapComponentes.elementInput(elementToBeClickable))));

	}
	
	public void clickButton(String elementToBeClickable, String nameStep) {
		GetScreenShoot.getEvidenceElement(nameStep, getDriver().findElement(By.xpath(mapComponentes.elementButton(elementToBeClickable))));
		getDriver().findElement(By.xpath(mapComponentes.elementButton(elementToBeClickable))).click();

	}
	
	public void clickByXpath(String xpath) {
		getDriver().findElement(By.xpath(xpath));
	}
	
	public void clickByXpath(String xpath,String nameStep) {
		GetScreenShoot.getEvidenceElement(nameStep, getDriver().findElement(By.xpath(xpath)));
		getDriver().findElement(By.xpath(xpath)).click();
	}
	
	public void clickLink(String name, String nameStep) {
		GetScreenShoot.getEvidenceElement(nameStep,getDriver().findElement(By.xpath(mapComponentes.elementLink(name))));
		getDriver().findElement(By.xpath(mapComponentes.elementLink(name))).click();
	}
	
	/************ Write ************/
	
	public void write(String element, String text,String nameStep) {
		getDriver().findElement(By.xpath(mapComponentes.elementInput(element))).sendKeys(text);
		GetScreenShoot.getEvidenceElement(nameStep, getDriver().findElement(By.xpath(mapComponentes.elementInput(element))));

	}
	
	public void write(String tagName, String element, String text, String nameStep) {
		getDriver().findElement(By.xpath(mapComponentes.elementInput(element, tagName))).sendKeys(text);
		GetScreenShoot.getEvidenceElement(nameStep, getDriver().findElement(By.xpath(mapComponentes.elementInput(element, tagName))));

	}
	
	/************ Obtained_Texts ************/

	public Object obtainedText(String element) {
		return getDriver().findElement(By.xpath(mapComponentes.elementInput(element))).getAttribute("value");
	}
	
	public Object obtainedText(String tagName,String element) {
		return getDriver().findElement(By.xpath(mapComponentes.elementInput(element, tagName))).getAttribute("value") ;
	}
	
	/************ Element_Radio ************/

	public boolean elementRadioIsSelected(String element) {
		return getDriver().findElement(By.xpath(mapComponentes.elementInput(element))).isSelected();
	}
	
	public void selectItemCombo(String item, String element,String nameStep) {
		WebElement elementWeb = getDriver().findElement(By.xpath(mapComponentes.elementSelected(element)));
		Select combo = new Select(elementWeb);
		combo.selectByVisibleText(item);
		GetScreenShoot.getEvidenceElement(nameStep, elementWeb);

	}	
	
	public boolean obtainedItemCombo(String item, String element) {
		WebElement elementWeb = getDriver().findElement(By.xpath(mapComponentes.elementSelected(element)));
		Select combo = new Select(elementWeb);
		List<WebElement> options = combo.getOptions();
		boolean finded = false;
		
		for(WebElement option : options) {
			if(option.getText().equals(item)) {
				finded = true;
				break;				
			}
		}
		
		return finded; 
	}
	
	/************ FRAME ************/
	public void goFrame(String frame) {
		getDriver().switchTo().frame(frame);
	}
	
	
	/************ Alert ************/
	public String getAlertTextAndClick() {
		Alert alert = getDriver().switchTo().alert();
		String msg = alert.getText();
		alert.accept();
		return msg;
	}
	
	/************ Tables ************/
	
	public WebElement getTable(String idTable) {
			return getDriver().findElement(By.xpath(mapComponentes.elementTable(idTable)));

	}
	
	public int getColumn(String register,String idTable) {
		int cont = -1;
		List<WebElement> column = getTable(idTable).findElements(By.xpath(".//th"));
		for(int i = 0; i < column.size(); i++ ) {
			if(column.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}			
		}		
		return cont;
	}
	
	public int getRow(String register, int idColumn,String idTable) {
		int cont = -1;
		getTable(idTable);
		List<WebElement> row = getTable(idTable).findElements(By.xpath("./tbody/tr/td["+idColumn+"]"));
		for(int i = 0; i < row.size(); i++ ) {
			if(row.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}			
		}		
		return cont;
	}
	
	public WebElement ElementTable(String register,String nameAccount, String idTable,String nameStep) {
		int idColumn = getColumn(register, idTable);
		int idRow = getRow(nameAccount, idColumn, idTable);
		return getDriver().findElement(By.xpath(mapComponentes.inputTable(idColumn, idRow, idTable)));		
	}
	
	public void clickElementTable(String register,String nameAccount, String idTable, String nameStep ) {
		WebElement tableButton = ElementTable(register, nameAccount, idTable, nameStep).
				findElement(By.xpath("/.//*[@class='glyphicon glyphicon-edit']"));
		GetScreenShoot.getEvidenceElement(nameStep, tableButton);
		tableButton.click();
	}
	
	public void clickInputTable(int idColumnButton, int idRow, String nameStep,String idTable) {
		WebElement tableButton = getDriver().findElement(By.xpath(mapComponentes.inputTable(idColumnButton, idRow, idTable)));
		tableButton.click();
		GetScreenShoot.getEvidenceElement(nameStep, tableButton);

	}
	
	
}
