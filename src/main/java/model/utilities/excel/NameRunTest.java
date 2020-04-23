package model.utilities.excel;

import java.util.List;

public class NameRunTest {

	private InteractWithExcel iwe;
	private List<String> nameRunTest;
	
	public NameRunTest(String scenarioName, String className) {
		iwe = new InteractWithExcel(scenarioName, className);
	}
	
	public List<String> getNameRunTest(){
		try {
			nameRunTest = iwe.nameRunTests("RunTest");
			
		} catch (Exception e) {
			System.out.println("Não foi possível obter os nomes dos testes!" + e.getMessage());
		}
		return nameRunTest;
		
	}
	
}
