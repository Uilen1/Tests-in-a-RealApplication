package model.utilities.excel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TestExcel {

	public static void main(String[] args) throws Exception {
		InteractWithExcel excelData = new InteractWithExcel("scenarios", "Scenarios");
		GlobalData gd = new GlobalData("scenarios", "Scenarios");

		int nr = excelData.getSpecificRowOfTest("CT 01");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		excelData.setCellData(sdf.format(new Date()),"CT 05","vOutData");
		excelData.createExcelBackup();

		System.out.println(nr);

		System.out.println("\n/*********************************************************/\n");

		List<Object> test = gd.getData();

		for (Object obj : test) {
			System.out.println(obj);
		}

		System.out.println("\n/*********************************************************/\n");

		List<String> loadDatas = excelData.nameRunTests("RunTest");

		for (String string : loadDatas) {
			System.out.println(string);
		}

		System.out.println("\n/*********************************************************/\n");

		List<HashMap<String, Object>> loadExcel = excelData.runTestInData("RunTest");
		System.out.println(loadExcel.size() + "\n\n");

		for (HashMap<String, Object> load : loadExcel) {
			System.out.println(load);

		}

		System.out.println("\n/*********************************************************/\n");

		for (HashMap<String, Object> load : loadExcel) {
			System.out.println(load.get("Test") + " - " + load.get("vNameAccount"));

		}

//		Object[][] load =excelData.runTestInData("RunTest");
//
//		for (int i = 0; i < excelData.AMOUNT_RUN_TESTS; i++) {
//			for(int j = 0; j < excelData.getNumberOfCells();j++) {
//				System.out.print(load[i][j] + " - ");
//			}
//			System.out.println();
//		}

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
