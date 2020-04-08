package model.map;

import static model.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MovementMap {
	
	public WebElement elementSelected(String idSelect) {
		return getDriver().findElement(By.xpath("//select[@id='"+idSelect+"']"));
	}
	
	public WebElement elementAlert(String classAlert) {
		return getDriver().findElement(By.xpath("//div[@class='"+classAlert+"']"));
	}
			
	public  WebElement elementInput(String nameInput) {
		return getDriver().findElement(By.xpath("//input[@id='"+nameInput+"']"));
	}
	
	public WebElement elementButton(String name) {
		return getDriver().findElement(By.xpath("//button[text()='"+name+"']"));
	}

}
