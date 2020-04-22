package model.utilities.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataDictionary {

	public HashMap<String, Object> dictionary;
	protected InteractWithExcel iwe;
	protected List<HashMap<String, Object>> dataDictionary = new ArrayList<HashMap<String, Object>>();

	public DataDictionary(String scenarioName, String className) {
		iwe = new InteractWithExcel(scenarioName, className);
	}

	public List<HashMap<String, Object>> getDictionary() {
		int i = 0;
		List<HashMap<String, Object>> excelList = new ArrayList<HashMap<String, Object>>();
		excelList = iwe.runTestInData("RunTest");

		for (HashMap<String, Object> hashMap : excelList) {
			dataDictionary.add(hashMap);
			dictionary = dataDictionary.get(i);
			i++;
		}

		return dataDictionary;

	}

}
