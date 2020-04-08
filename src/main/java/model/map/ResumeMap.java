package model.map;

import static model.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ResumeMap {
	
public String elementTableClick = ".//span[@class='glyphicon glyphicon-remove-circle']";
	
	public WebElement elementAlert(String classAlert) {
		return getDriver().findElement(By.xpath("//div[@class='"+classAlert+"']"));
	}
	
	public WebElement elementTable(String nameTable) {
		return getDriver().findElement(By.xpath("//table[@id='"+nameTable+"']"));
	}
	
	public WebElement inputTable(int idColumnButton, int idRow, String idTable) {
		return getDriver().findElement(By.xpath("//table[@id='"+idTable+"']/tbody/tr["+idRow+"]/td["+idColumnButton+"]"));
	}
	
	public WebElement elementSelected(String idSelect) {
		return getDriver().findElement(By.xpath("//select[@id='"+idSelect+"']"));
	}

}
