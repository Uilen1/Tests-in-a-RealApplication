package model.map;

public class mapComponentes {

	public String loginButton = "//button[text()='Entrar']";
	public String elementTableClick = ".//span[@class='glyphicon glyphicon-edit']";
	
	public static String elementAlert(String classAlert) {
		return "//div[@class='"+classAlert+"']";
	}
	
	public static String elementButton(String name) {
		return "//button[text()='"+name+"']";
	}
	
	public static String elementInput(String nameInput) {
		return "//input[@id='"+nameInput+"']";
	}
	
	public static String elementInput(String elementId,String tagName) {
		return "//"+tagName+"[@id='"+elementId+"']";
	}
	
	public static String elementLink(String name) {
		return "//a[text()='"+name+"']";
	}
	
	public static String elementSelected(String idSelect) {
		return "//select[@id='"+idSelect+"']";
	}
	
	public static String elementTable(String nameTable) {
		return "//table[@id='"+nameTable+"']";
	}
	
	public static String inputTable(int idColumnButton, int idRow, String idTable) {
		return "//table[@id='"+idTable+"']/tbody/tr["+idRow+"]/td["+idColumnButton+"]";
	}
	
	
	
	
}
