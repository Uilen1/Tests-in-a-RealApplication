package model.utilities.excel;

import java.io.File;
//File and exception handling imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	private List<String> nameRunTest = new ArrayList<String>();
	private List<HashMap<String, Object>> runTests = new ArrayList<HashMap<String, Object>>();

	public InteractWithExcel(String scenararioName, String className) {
		this.scenarioName = scenararioName;
		this.className = className;
	}

	public File accessExcelData() throws IOException {

		File file = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + className
				+ File.separator);
		return file;

	}

	public void initializeData() {
		try {
			fisExcel = new FileInputStream(accessExcelData()+File.separator+ scenarioName + ".xlsx");
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo n�o foi encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("N�o foi poss�vel acessar o arquivo : " + e.getMessage());
		}
		try {
			workBook = new XSSFWorkbook(fisExcel);
		} catch (IOException e) {
			System.out.println("N�o foi poss�vel acessar o arquivo : " + e.getMessage());
		}
		sheet = workBook.getSheetAt(0);
	}

	public int getNumberOfRows() throws Exception {
		return sheet.getPhysicalNumberOfRows();
	}

	public int getNumberOfCells() throws Exception {
		return sheet.getRow(0).getLastCellNum();
	}

	public int getSpecificRowOfTest(String testName) {
		int rowIndex = 0;
		initializeData();
		Iterator<Row> rowIterator = sheet.rowIterator();

		try {
			while (rowIterator.hasNext()) {
				Row oneRow = rowIterator.next();

				if (oneRow.getCell(0).getStringCellValue().contentEquals(testName)) {
					rowIndex = oneRow.getRowNum();
				}
			}
		} catch (Exception e) {
			System.out.println("N�o foi poss�vel encontrar o �ndice da linha! " + e.getMessage());
		}
		return rowIndex;

	}

	public int getSpecificIndex(String columnHeader) {
		int columnIndex = 0;
		initializeData();
		Iterator<Row> rowIterator = sheet.rowIterator();

		try {
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

		} catch (Exception e) {
			System.out.println("N�o foi poss�vel encontrar o �ndice do elemento! " + e.getMessage());
		}
		return columnIndex;

	}

	public List<String> nameRunTests(String columnHeader) {
		int columnIndex = getSpecificIndex(columnHeader);
		initializeData();
		Iterator<Row> rowIterator = sheet.rowIterator();

		try {

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				if (row.getCell(columnIndex).getStringCellValue().contentEquals("Yes")) {
					nameRunTest.add(row.getCell(0).getStringCellValue());
				}
			}

		} catch (Exception e) {
			System.out.println("N�o foi poss�vel encontrar os nomes dos testes! " + e.getMessage());
		}
		return nameRunTest;

	}

	public String[] header() {
		String[] header = null;
		int i = 0;
		initializeData();
		Iterator<Cell> cellIterator = sheet.getRow(0).cellIterator();

		try {
			while (cellIterator.hasNext()) {
				Cell oneCell = cellIterator.next();
				if (header == null) {
					header = new String[sheet.getRow(0).getPhysicalNumberOfCells()];
				}
				header[i] = oneCell.getStringCellValue();
				i++;
			}

		} catch (Exception e) {
			System.out.println("N�o foi poss�vel encontrar o cabe�alho da planilha! " + e.getMessage());
		}
		return header;

	}

	public List<HashMap<String, Object>> runTestInData(String columnHeader) {
		String[] header = header();
		HashMap<String, Object> hashMapExcel = null;

		int columnIndex = getSpecificIndex(columnHeader);
		Iterator<Row> rowIterator = sheet.rowIterator();

		try {
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				int j = 0;

				if (row.getCell(columnIndex).getStringCellValue().contentEquals("Yes")) {

					hashMapExcel = new HashMap<String, Object>();

					while (cellIterator.hasNext()) {
						Cell oneCell = cellIterator.next();

						switch (oneCell.getCellType()) {
						case NUMERIC:
							if (DateUtil.isCellDateFormatted(oneCell)) {
								hashMapExcel.put(header[j], oneCell.getDateCellValue());
							} else {
								hashMapExcel.put(header[j], oneCell.getNumericCellValue());
							}
							j++;
							break;
						case STRING:
							hashMapExcel.put(header[j], oneCell.getStringCellValue());
							j++;
							break;
						case FORMULA:
							hashMapExcel.put(header[j], oneCell.getCellFormula());
							j++;
							break;
						case BLANK:
							hashMapExcel.put(header[j], "");
							j++;
							break;
						case BOOLEAN:
							hashMapExcel.put(header[j], oneCell.getBooleanCellValue());
							j++;
							break;
						case ERROR:
							hashMapExcel.put(header[j], oneCell.getErrorCellValue());
							j++;
							break;
						default:
							throw new IllegalArgumentException("Invalid cell type " + oneCell.getCellType());
						}
					}
					runTests.add(hashMapExcel);
				}
			}

		} catch (Exception e) {
			System.out.println("N�o foi poss�vel encontrar os testes que ser�o executados! " + e.getMessage());
		}
		return runTests;
	}

	public List<Object> loadData() throws Exception {
		List<Object> listData = new ArrayList<>();
		List<HashMap<String, Object>> hashMapTest = new ArrayList<HashMap<String, Object>>();

		try {
			hashMapTest = runTestInData("RunTest");
			listData.add(hashMapTest.get(0).get("Test"));
			listData.add(hashMapTest);

		} catch (Exception e) {
			System.out.println(
					"N�o foi poss�vel encontrar os nomes e casos de testes que ser�o executados! " + e.getMessage());
		}
		return listData;
	}

	public Object[][] loadExcel() {

		initializeData();
		int i = 0;
		int j = 0;
		Object[][] loadData = null;

		Iterator<Row> rowIterator = sheet.rowIterator();

		try {
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

		} catch (Exception e) {
			System.out.println("N�o foi poss�vel carregar a planilha em formato de matriz! " + e.getMessage());
		}

		return loadData;
	}

	public void setCellData(String value, String testName,String columnHeader) {
		int rowNum = getSpecificRowOfTest(testName);
		int colNum = getSpecificIndex(columnHeader);
		try {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
			}
			Cell cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(value);
			} else {
				cell.setCellValue(value);
			}
			FileOutputStream fileOut = new FileOutputStream(accessExcelData()+File.separator+ scenarioName + ".xlsx");
			workBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void createExcelBackup() {
		try {
			FileOutputStream fileOut = new FileOutputStream(accessExcelData() +File.separator+ scenarioName + "Backup.xlsx");
			workBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
