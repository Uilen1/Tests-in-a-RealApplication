package model.map;

import static model.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuMap {
	
	public WebElement elementLink(String name) {
		return getDriver().findElement(By.xpath("//a[text()='"+name+"']"));
	}

}
