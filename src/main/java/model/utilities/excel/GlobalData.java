package model.utilities.excel;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {

	private DataDictionary dd;
	private NameRunTest nrt;
	private String scenarioName;
	private String className;
	private List<Object> testCase = new ArrayList<Object>();

	public GlobalData(String scenarioName, String className) {
		this.nrt = new NameRunTest(scenarioName, className);
		this.dd = new DataDictionary(scenarioName, className);
		this.scenarioName = scenarioName;
		this.className = className;
	}

	public List<Object> getData(){
		int i = 0;
		try {
			for (String nameRunTest : nrt.getNameRunTest()) {
				this.dd = new DataDictionary(scenarioName, className);
				Object[] obj = new Object[2];
				dd.dictionary = dd.getDictionary().get(i);
				obj[0] = nameRunTest;
				obj[1] = dd;
				testCase.add(obj);
				i++;
			}
		} catch (Exception e) {
			System.out.println("Não possível obter o nome e os casos de teste da planilha! " + e.getMessage());
		}
		return testCase;
	}
	
	public void setData(String value, String testName ,String columnHeader) {
		dd.iwe.setCellData(value, testName, columnHeader);
	}
	
	public void setBackup() {
		dd.iwe.createExcelBackup();
	}

}
