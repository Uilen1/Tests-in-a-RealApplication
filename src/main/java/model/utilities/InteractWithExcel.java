package model.utilities;

import java.io.File;
//File and exception handling imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InteractWithExcel {

	private String scenarioName;
	private String className;
	private static FileInputStream fisExcel;
	private static XSSFWorkbook workBook;
	private static XSSFSheet sheet;
	private static List<Object> runTests;

	public InteractWithExcel(String scenararioName, String className) {
		this.scenarioName = scenararioName;
		this.className = className;
	}

	public File accessExcelData() throws IOException {
		File file = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + className
				+ File.separator + scenarioName + ".xlsx");
		return file;

	}

	public void initializeData() {
		try {
			fisExcel = new FileInputStream(accessExcelData());
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo não foi encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Não foi possível acessar o arquivo : " + e.getMessage());
		}
		try {
			workBook = new XSSFWorkbook(fisExcel);
		} catch (IOException e) {
			System.out.println("Não foi possível acessar o arquivo : " + e.getMessage());
		}
		sheet = workBook.getSheetAt(0);
	}

	public int getSpecificIndex(String columnHeader) {
		int columnIndex = 0;
		initializeData();
		Iterator<Row> rowIterator = sheet.rowIterator();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.getStringCellValue().contentEquals(columnHeader)) {
					columnIndex = cell.getColumnIndex();
					break;
				}
			}
			break;

		}

		return columnIndex;

	}

	public Collection<Object> runTestInData(String columnHeader) {
		int columnIndex = getSpecificIndex(columnHeader);
		Iterator<Row> rowIterator = sheet.rowIterator();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();

			if (row.getCell(columnIndex).getStringCellValue().contentEquals("Yes")) {
				runTests.add(row);
				break;
			}

		}
		return runTests;

	}
}
