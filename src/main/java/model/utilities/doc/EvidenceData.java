package model.utilities.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

public class EvidenceData {

	private String executionTestName, executionTime, result, date;
	XWPFDocument document;

	public EvidenceData(String executionTestName, String executionTime, String result, String date,
			XWPFDocument document) {

		this.executionTestName = executionTestName;
		this.executionTime = executionTime;
		this.result = result;
		this.date = date;
		this.document = document;

	}

	public void createFirstPage() throws Exception {

		/***** PRIMEIRA_PÁGINA *****/
		XWPFParagraph paragraph = document.createParagraph();
		ConfigurationEvidenceData.createdBordLine(paragraph, Borders.BASIC_BLACK_DASHES);

		XWPFRun run0 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run0, "Arial", 11, true, "Caso de teste: ");

		XWPFRun run_0 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run_0, "Arial", 11, false, executionTestName);
		run_0.addBreak();

		XWPFRun run1 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run1, "Arial", 11, true, "Status: ");

		XWPFRun run_1 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run_1, "Arial", 11, false, result);
		run_1.setColor(result.toString().trim() == "Passed" ? "32CD32" : "FF0000");
		run_1.addBreak();

		XWPFRun run3 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run3, "Arial", 11, true, "Data e hora da execução: ");

		XWPFRun run_3 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run_3, "Arial", 11, false, date);
		run_3.addBreak();

		XWPFRun run4 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run4, "Arial", 11, true, "Duração da execução: ");

		XWPFRun run_4 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run_4, "Arial", 11, false, executionTime);
		run_4.addBreak();

		XWPFRun run5 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run5, "Arial", 11, true, "Result: ");

		XWPFRun run_5 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run_5, "Arial", 11, false);

		if (result.length() > 76) {
			run_5.addBreak();
		}
		run_5.setText(result.length() > 300 ? result.substring(0, 300) + "[...]" : result);

		XWPFParagraph paragraph1 = document.createParagraph();
		paragraph1.setSpacingAfterLines(1);
		paragraph1.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun runResult = paragraph1.createRun();
		ConfigurationEvidenceData.addBreaks(runResult, 3);

		File imageResultPassed = new File(
				System.getProperty("user.dir") + File.separator + "imagens" + File.separator + "aprovado.png");
		File imageResultFailed = new File(
				System.getProperty("user.dir") + File.separator + "imagens" + File.separator + "reprovado.png");

		ConfigurationEvidenceData.setImage(runResult, result, imageResultPassed, imageResultFailed);
		runResult.addBreak(BreakType.PAGE);
	}

	public void setContentPage(File[] files) throws IOException {
		/***** CONTEUDO_ARQUIVO *****/

		InputStream pic = null;
		int cont = 0;
		XWPFParagraph paragraphOne = document.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		paragraphOneRunOne.addBreak();

		for (File file : files) {
			try {
				pic = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			XWPFParagraph paragraphNameImage = document.createParagraph();
			XWPFRun runNameImage = paragraphNameImage.createRun();

			XWPFParagraph paragraphImage = document.createParagraph();
			paragraphImage.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun paragraphImageRunOne = paragraphImage.createRun();

			try {
				String[] header = file.getName().toString().split("\\W");

				ConfigurationEvidenceData.setFont(runNameImage, "Arial", 10, true, header[0]);
				runNameImage.addBreak();

				paragraphImageRunOne.addPicture(new FileInputStream(file), XWPFDocument.PICTURE_TYPE_PNG,
						file.getAbsolutePath(), Units.toEMU(446), Units.toEMU(257));
				if (cont < (files.length - 1)) {
					ConfigurationEvidenceData.addBreaks(paragraphImageRunOne, 3);
				}
				cont++;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pic.close();
			}
		}

	}

	public void setDataInHeader(String valueToInsert) throws Exception {
		try {
			List<XWPFHeader> hdr = document.getHeaderList();
			List<XWPFTable> tbl = hdr.get(0).getTables();

			XWPFTableRow row = tbl.get(0).getRow(0);
			XWPFTableCell cell = row.getCell(2);
			XWPFParagraph p = cell.getParagraphArray(0);
			p.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun r = p.createRun();
			ConfigurationEvidenceData.setFont(r, "Arial", 10, false, valueToInsert);
		} catch (Exception e) {
			throw new Exception("Não foi possível inserir um valor no cabeçalho");
		}

	}

	public void createHeader(String nameProject, String date) throws Exception {

		XWPFHeader hdr = document.createHeader(HeaderFooterType.DEFAULT);
		XWPFTable tbl = hdr.createTable(1, 3);
		XWPFTable tbl2 = hdr.createTable(1, 1);

		int pad = (int) (.1 * 1440);
		tbl.setCellMargins(pad, pad, pad, pad);
		tbl2.setCellMargins(pad, pad, pad, pad);
		CTTbl ctTbl = tbl.getCTTbl();
		CTTbl ctTbl2 = tbl2.getCTTbl();

		ConfigurationEvidenceData.configureTable(ctTbl, tbl, 3, "3300");
		ConfigurationEvidenceData.configureTable(ctTbl2, tbl2, 1, "9900");

		// Add paragraphs to the cells of the firt Line
		XWPFTableRow row = tbl.getRow(0);
		XWPFTableCell cell = row.getCell(0);
		XWPFParagraph p = cell.getParagraphArray(0);
		XWPFRun r = p.createRun();
		ConfigurationEvidenceData.setFont(r, "Arial", 12, true, nameProject);

		cell = row.getCell(1);
		p = cell.getParagraphArray(0);
		r = p.createRun();
		ConfigurationEvidenceData.setFont(r, "Arial", 12, true, "EVIDÊNCIA DE TESTES");

		cell = row.getCell(2);
		p = cell.getParagraphArray(0);
		r = p.createRun();
		ConfigurationEvidenceData.setFont(r, "Arial", 12, true, date);

		// Add paragraphs to the cells of the Second Line
		XWPFTableRow row2 = tbl2.getRow(0);
		XWPFTableCell cell2 = row2.getCell(0);
		XWPFParagraph p2 = cell2.getParagraphArray(0);
		XWPFRun r2 = p2.createRun();
		ConfigurationEvidenceData.setFont(r2, "Arial", 12, true, "Sistema: Real Application");

	}

	public void createFooter() {
		/***** RODAPÉ *****/
		try {

			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();

			CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
			XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
			XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
			paragraph = footer.getParagraphArray(0);

			paragraph = footer.createParagraph();
			XmlCursor cursor = paragraph.getCTP().newCursor();
			XWPFTable table = footer.insertNewTbl(cursor);

			XWPFTableRow row = table.createRow();

			XWPFTableCell cell = row.createCell();
			ConfigurationEvidenceData.setWidthOfCell(cell, 8, 1440);
			
			paragraph = cell.getParagraphs().get(0);
			ConfigurationEvidenceData.createdBordLine(paragraph, Borders.BASIC_BLACK_DASHES);

			run = paragraph.createRun();
			run.setBold(true);
			run.setText("Executado por:  ");

			run = paragraph.createRun();
			run.setBold(false);
			run.setText("Muly Automação");

			XWPFTableCell cell2 = row.createCell();
			ConfigurationEvidenceData.setWidthOfCell(cell2, 3, 1440);

			DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal1 = Calendar.getInstance();
			String date1 = dateFormat1.format(cal1.getTime());
			paragraph = cell2.getParagraphs().get(0);
			ConfigurationEvidenceData.createdBordLine(paragraph, Borders.BASIC_BLACK_DASHES);

			run = paragraph.createRun();
			run.setBold(true);
			run.setText("Data: ");

			run = paragraph.createRun();
			run.setBold(false);
			run.setText(date1);

			XWPFTableCell cell3 = row.createCell();
			ConfigurationEvidenceData.setWidthOfCell(cell3, 4, 1440);

			paragraph = cell3.getParagraphs().get(0);
			ConfigurationEvidenceData.createdBordLine(paragraph, Borders.BASIC_BLACK_DASHES);
			run = paragraph.createRun();
			run.setText("    Versão: 1.0");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CreateDocument(File folder) throws IOException {
		FileOutputStream outStream = null;
		try {
			outStream = new FileOutputStream(folder.getAbsolutePath() + File.separator + executionTestName + ".docx");
			document.write(outStream);
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
