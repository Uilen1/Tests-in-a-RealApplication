package model.utilities;

import java.util.List;

public class TestExcel {

	public static void main(String[] args) {
		InteractWithExcel excelData = new InteractWithExcel("scenarios", "Scenarios");

		List<Object[]> load = (List<Object[]>) excelData.runTestInData("RunTest");

		for (int i = 0; i < load.size(); i++) {
			System.out.println(load.get(i));

		}
		
		System.out.println("\n/*********************************************************/\n");

		Object[][] loadData = excelData.loadExcel();

		for (int i = 0; i < excelData.getNumberOfRows(); i++) {
			for (int j = 0; j < excelData.getNumberOfCells(); j++) {
				System.out.print(loadData[i][j] + " - ");
			}
			System.out.println();

		}
	}
}
