package model.utilities;

import static model.core.DriverFactory.getDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.map.mapComponentes;
import model.utilities.screenshoot.GetScreenShoot;

public class Utils {

	private mapComponentes mapComponents = new mapComponentes();
	public GetScreenShoot getScreenShoot = new GetScreenShoot();
	WebDriverWait wait = new WebDriverWait(getDriver(), 60);

	
	public void scrolltoElement(WebElement element) {
		boolean condition = true;
		WebDriverWait wait = new WebDriverWait(getDriver(), 3);
		while (condition) {
			if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {

				executarJavascript(
						"arguments[0].scrollIntoView({behavior: 'instant', block: 'center',inline: 'center'})",
						element);
				condition = false;
				break;
			}
		}

	}
	
	public static Object executarJavascript(String cmd, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, params);
	}
	
	/********* Console Log *********/
	public void description(String text) {
		System.out.println(text);
	}
	
	/************ Click ************/

	public void clicar(WebElement element) throws Exception {
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void clickElement(WebElement element, String nameStep) throws Exception {
		GetScreenShoot.getEvidenceElement(nameStep, element);
		element.click();
	}

	public void click(String elementToBeClickable) throws Exception {
		getDriver().findElement(By.xpath(mapComponentes.elementInput(elementToBeClickable))).click();
	}

	public void click(String elementToBeClickable, String nameStep) throws Exception {
		getDriver().findElement(By.xpath(mapComponentes.elementInput(elementToBeClickable))).click();
		GetScreenShoot.getEvidenceElement(nameStep,
				getDriver().findElement(By.xpath(mapComponentes.elementInput(elementToBeClickable))));

	}

	public void clickButton(String elementToBeClickable, String nameStep) throws Exception {
		GetScreenShoot.getEvidenceElement(nameStep,
				getDriver().findElement(By.xpath(mapComponentes.elementButton(elementToBeClickable))));
		getDriver().findElement(By.xpath(mapComponentes.elementButton(elementToBeClickable))).click();

	}

	public void clickByXpath(String xpath) throws Exception {
		getDriver().findElement(By.xpath(xpath));
	}

	public void clickByXpath(String xpath, String nameStep) throws Exception {
		GetScreenShoot.getEvidenceElement(nameStep, getDriver().findElement(By.xpath(xpath)));
		getDriver().findElement(By.xpath(xpath)).click();
	}

	public void clickLink(String name, String nameStep) throws Exception {
		GetScreenShoot.getEvidenceElement(nameStep,
				getDriver().findElement(By.xpath(mapComponentes.elementLink(name))));
		getDriver().findElement(By.xpath(mapComponentes.elementLink(name))).click();
	}

	/************ Write ************/

	public void escrever(WebElement element, String text) throws Exception {
		wait.until(ExpectedConditions.visibilityOf(element));
		scrolltoElement(element);
		element.sendKeys(text);
	}
	
	public void write(String element, String text, String nameStep) throws Exception {
		getDriver().findElement(By.xpath(mapComponentes.elementInput(element))).sendKeys(text);
		GetScreenShoot.getEvidenceElement(nameStep,
				getDriver().findElement(By.xpath(mapComponentes.elementInput(element))));

	}

	public void write(String tagName, String element, String text, String nameStep) throws Exception {
		getDriver().findElement(By.xpath(mapComponentes.elementInput(element, tagName))).sendKeys(text);
		GetScreenShoot.getEvidenceElement(nameStep,
				getDriver().findElement(By.xpath(mapComponentes.elementInput(element, tagName))));

	}

	/************ Obtained_Texts ************/

	public Object obtainedText(String element) throws Exception {
		return getDriver().findElement(By.xpath(mapComponentes.elementInput(element))).getAttribute("value");
	}

	public Object obtainedText(String tagName, String element) throws Exception {
		return getDriver().findElement(By.xpath(mapComponentes.elementInput(element, tagName))).getAttribute("value");
	}

	public String getAlertText(String classAlert, String nameStep) throws Exception {
		WebElement element = getDriver().findElement(By.xpath(mapComponentes.elementAlert(classAlert)));
		GetScreenShoot.getEvidenceElement(nameStep, element);

		return element.getText();
	}

	/************ Element_Radio ************/

	public boolean elementRadioIsSelected(String element) throws Exception {
		return getDriver().findElement(By.xpath(mapComponentes.elementInput(element))).isSelected();
	}

	public void selectItemCombo(String item, String element, String nameStep) throws Exception {
		WebElement elementWeb = getDriver().findElement(By.xpath(mapComponentes.elementSelected(element)));
		Select combo = new Select(elementWeb);
		combo.selectByVisibleText(item);
		GetScreenShoot.getEvidenceElement(nameStep, elementWeb);

	}

	public boolean obtainedItemCombo(String item, String element) throws Exception {
		WebElement elementWeb = getDriver().findElement(By.xpath(mapComponentes.elementSelected(element)));
		Select combo = new Select(elementWeb);
		List<WebElement> options = combo.getOptions();
		boolean finded = false;

		for (WebElement option : options) {
			if (option.getText().equals(item)) {
				finded = true;
				break;
			}
		}

		return finded;
	}

	/************ FRAME ************/
	public void goFrame(String frame) throws Exception {
		getDriver().switchTo().frame(frame);
	}

	/************ Alert ************/
	public String getAlertTextAndClick() throws Exception {
		Alert alert = getDriver().switchTo().alert();
		String msg = alert.getText();
		alert.accept();
		return msg;
	}

	/************ Tables ************/

	public WebElement getTable(String idTable) throws Exception {
		return getDriver().findElement(By.xpath(mapComponentes.elementTable(idTable)));

	}

	public int getColumn(String register, String idTable) throws Exception {
		int cont = -1;
		List<WebElement> column = getTable(idTable).findElements(By.xpath(".//th"));
		for (int i = 0; i < column.size(); i++) {
			if (column.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}
		}
		return cont;
	}

	public int getRow(String register, int idColumn, String idTable) throws Exception {
		int cont = -1;
		getTable(idTable);
		List<WebElement> row = getTable(idTable).findElements(By.xpath("./tbody/tr/td[" + idColumn + "]"));
		for (int i = 0; i < row.size(); i++) {
			if (row.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}
		}
		return cont;
	}

	public WebElement ElementTable(String register, String nameAccount, String idTable, String nameColumnAction,
			String nameStep) throws Exception {
		int idColumn = getColumn(register, idTable);
		int idRow = getRow(nameAccount, idColumn, idTable);
		int idColumnButton = getColumn(nameColumnAction, idTable);
		return getDriver().findElement(By.xpath(mapComponentes.inputTable(idColumnButton, idRow, idTable)));
	}

	public void clickElementTable(String register, String nameAccount, String idTable, String nameColumnAction,
			String nameStep) throws Exception{
		WebElement elementButton = ElementTable(register, nameAccount, idTable, nameColumnAction, nameStep);
		WebElement tableButton = elementButton.findElement(By.xpath(mapComponents.elementTableClick));
		GetScreenShoot.getEvidenceElement(nameStep, elementButton);
		tableButton.click();
	}

	public void clickInputTable(int idColumnButton, int idRow, String nameStep, String idTable) throws Exception{
		WebElement tableButton = getDriver()
				.findElement(By.xpath(mapComponentes.inputTable(idColumnButton, idRow, idTable)));
		tableButton.click();
		GetScreenShoot.getEvidenceElement(nameStep, tableButton);

	}

	/************ Date ************/

	public Date obtainedDateWithDifferenceOfDays(int differenceOfDays) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, differenceOfDays);
		return calendar.getTime();

	}

	public String obtainedDateFormated(Date date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);

	}
	
	public String obtainedDateWithHoursFormated(Date date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(date);

	}
	
	//teste

}
