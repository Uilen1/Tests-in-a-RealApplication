package model.utilities.excel;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {

	private DataDictionary dd;
	private NameRunTest nrt;
	private String scenarioName;
	private String className;
	private List<Object> test = new ArrayList<Object>();

	public GlobalData(String scenarioName, String className) {
		this.nrt = new NameRunTest(scenarioName, className);
		this.scenarioName = scenarioName;
		this.className = className;
	}

	public List<Object> getData() {
		int i = 0;
		for (String string : nrt.getNameRunTest()) {
			this.dd = new DataDictionary(scenarioName, className);
			Object[] obj = new Object[2];
			dd.dictionary = dd.getDictionary().get(i);
			obj[0] = string;
			obj[1] = dd;
			test.add(obj);
			i++;
		}
		return test;
	}

}
