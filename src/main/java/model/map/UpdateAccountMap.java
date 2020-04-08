package model.map;

import static model.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UpdateAccountMap {
	
	public String elementTableClick = ".//span[@class='glyphicon glyphicon-edit']";
	
	public WebElement elementAlert(String classAlert) {
		return getDriver().findElement(By.xpath("//div[@class='"+classAlert+"']"));
	}
	
	public WebElement elementTable(String nameTable) {
		return getDriver().findElement(By.xpath("//table[@id='"+nameTable+"']"));
	}
	
	public WebElement inputTable(int idColumnButton, int idRow, String idTable) {
		return getDriver().findElement(By.xpath("//table[@id='"+idTable+"']/tbody/tr["+idRow+"]/td["+idColumnButton+"]"));
	}
	
	public WebElement elementInput(String nameInput) {
		return getDriver().findElement(By.xpath("//input[@id='"+nameInput+"']"));
	}
	
	public WebElement elementButton(String name) {
		return getDriver().findElement(By.xpath("//button[text()='"+name+"']"));
	}
	

}
