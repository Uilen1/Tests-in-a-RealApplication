package model.utilities;

public class TestExcel {

	public static void main(String[] args) {
		InteractWithExcel excelData = new InteractWithExcel("scenarios", "Scenarios");

		Object[][] load =excelData.runTestInData("RunTest");

		for (int i = 0; i < excelData.AMOUNT_RUN_TESTS; i++) {
			for(int j = 0; j < excelData.getNumberOfCells();j++) {
				System.out.print(load[i][j] + " - ");
			}
			System.out.println();
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
