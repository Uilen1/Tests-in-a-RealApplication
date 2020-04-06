package model.map;

public class mapComponentes {

	public static String elementInput(String nameInput) {
		return "//input[@id='"+nameInput+"']";
	}
	
	public static String elementInput(String elementId,String tagName) {
		return "//"+tagName+"[@id='"+elementId+"']";
	}
	
	public static String elementSelected(String idSelect) {
		return "//select[@id='"+idSelect+"']";
	}
	
	public static String elementTable() {
		return "//table[@id='elementosForm:tableUsuarios']";
	}
	
	public static String inputTable(int idColumnButton, int idRow) {
		return "//table[@id='elementosForm:tableUsuarios']/tbody/tr["+idRow+"]/td["+idColumnButton+"]//input";
	}
	
	
	
	
}
