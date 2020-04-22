package model.utilities.excel;

import java.util.List;

public class NameRunTest {

	private InteractWithExcel iwe;
	private List<String> nameRunTest;
	
	public NameRunTest(String scenarioName, String className) {
		iwe = new InteractWithExcel(scenarioName, className);
	}
	
	public List<String> getNameRunTest(){
		nameRunTest = iwe.nameRunTests("RunTest");
		return nameRunTest;
	}
	
}
