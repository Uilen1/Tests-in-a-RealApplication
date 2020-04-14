package model.utilities;

import java.io.File;
//File and exception handling imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InteractWithExcel {

	private String scenarioName;
	private String className;
	private static FileInputStream fisExcel;
	private static XSSFWorkbook workBook;
	private static XSSFSheet sheet;
	private static Object[][] runTests;

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
	
	public int getNumberOfRows() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public int getNumberOfCells() {
		return sheet.getRow(0).getLastCellNum();
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

	public List<Object[]> runTestInData(String columnHeader) {
		int columnIndex = getSpecificIndex(columnHeader);
		int i = 0;
		Iterator<Row> rowIterator = sheet.rowIterator();

		while (rowIterator.hasNext() && (i < sheet.getPhysicalNumberOfRows() - 1)) {
			int j = 0;
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			if (runTests == null) {
				runTests = new Object[sheet.getPhysicalNumberOfRows()][row.getPhysicalNumberOfCells()];
			}
			if (row.getRowNum() == 0) {
				continue;
			}

			while (cellIterator.hasNext()) {
				Cell oneCell = cellIterator.next();

				if (row.getCell(columnIndex).getStringCellValue().contentEquals("Yes")) {
					switch (oneCell.getCellType()) {
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(oneCell)) {
							runTests[i][j] = oneCell.getDateCellValue();
						} else {
							runTests[i][j] = oneCell.getNumericCellValue();
						}
						j++;
						break;
					case STRING:
						runTests[i][j] = oneCell.getStringCellValue();
						j++;
						break;
					case FORMULA:
						runTests[i][j] = oneCell.getCellFormula();
						j++;
						break;
					case BLANK:
	                    runTests[i][j] = "";
	                    j++;
	                    break;
					case BOOLEAN:
						runTests[i][j] = oneCell.getBooleanCellValue();
						j++;
						break;
					case ERROR:
						runTests[i][j] = oneCell.getErrorCellValue();
						j++;
						break;
					default:
						throw new IllegalArgumentException("Invalid cell type " + oneCell.getCellType());
					}
				}

			}

			i++;

		}
		return Arrays.asList(runTests);

	}

	public Object[][] loadExcel() {

		initializeData();
		int i = 0;
		int j = 0;
		Object[][] loadData = null;

		Iterator<Row> rowIterator = sheet.rowIterator();

		while (rowIterator.hasNext()) {
			j = 0;
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			if (loadData == null) {
				loadData = new Object[sheet.getPhysicalNumberOfRows()][row.getPhysicalNumberOfCells()];
			}

			while (cellIterator.hasNext()) {
				Cell oneCell = cellIterator.next();
				switch (oneCell.getCellType()) {
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(oneCell)) {
						loadData[i][j] = oneCell.getDateCellValue();
					} else {
						loadData[i][j] = oneCell.getNumericCellValue();
					}
					j++;
					break;
				case STRING:
					loadData[i][j] = oneCell.getStringCellValue();
					j++;
					break;
				case FORMULA:
					loadData[i][j] = oneCell.getCellFormula();
					j++;
					break;
				case BLANK:
                    loadData[i][j] = "";
                    j++;
                    break;
				case BOOLEAN:
					loadData[i][j] = oneCell.getBooleanCellValue();
					j++;
					break;
				case ERROR:
					loadData[i][j] = oneCell.getErrorCellValue();
					j++;
					break;
				default:
					throw new IllegalArgumentException("Invalid cell type " + oneCell.getCellType());
				}
			}
			
			i++;
		}
		
		return loadData;
	}

}
