package model.utilities.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataDictionary {

	public HashMap<String, Object> dictionary;
	protected InteractWithExcel iwe;

	public DataDictionary(String scenarioName, String className) {
		iwe = new InteractWithExcel(scenarioName, className);
	}

	public List<HashMap<String, Object>> getDictionary() {
		List<HashMap<String, Object>> excelList = new ArrayList<HashMap<String, Object>>();
		try {
			excelList = iwe.runTestInData("RunTest");

		} catch (Exception e) {
			System.out.println("Não foi possível obter o dicionário de testes da planilha!" + e.getMessage());
		}
		return excelList;

	}

}
