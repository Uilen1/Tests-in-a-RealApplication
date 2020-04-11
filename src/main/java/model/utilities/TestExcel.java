package model.utilities;

import java.util.List;

public class TestExcel {

	public static void main(String[] args) {
		InteractWithExcel excelData = new InteractWithExcel("scenarios", "Scenarios");
		
		List<Object> loadData = (List<Object>) excelData.runTestInData("RunTest");

		System.out.println(loadData);
	}

}
