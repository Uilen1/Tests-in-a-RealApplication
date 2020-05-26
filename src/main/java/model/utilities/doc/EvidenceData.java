package model.utilities.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

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

		/***** PRIMEIRA_P�GINA *****/
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
		ConfigurationEvidenceData.setFont(run3, "Arial", 11, true, "Data e hora da execu��o: ");

		XWPFRun run_3 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run_3, "Arial", 11, false, date);
		run_3.addBreak();

		XWPFRun run4 = paragraph.createRun();
		ConfigurationEvidenceData.setFont(run4, "Arial", 11, true, "Dura��o da execu��o: ");

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
				if(cont < (files.length-1)) {
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

	public void createFooter() {
		/***** RODAP� *****/
		try {

			XWPFParagraph paragraph11 = document.createParagraph();
			XWPFRun run11 = paragraph11.createRun();

			CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
			XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
			XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
			paragraph11 = footer.getParagraphArray(0);

			paragraph11 = footer.createParagraph();
			XmlCursor cursor = paragraph11.getCTP().newCursor();
			XWPFTable table = footer.insertNewTbl(cursor);

			XWPFTableRow row = table.createRow();
			int twipsPerInch = 1440;
			table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(6 * twipsPerInch));

			XWPFTableCell cell = row.createCell();
			CTTblWidth tblWidth = cell.getCTTc().addNewTcPr().addNewTcW();
			tblWidth.setW(BigInteger.valueOf(8 * twipsPerInch));
			tblWidth.setType(STTblWidth.DXA);
			paragraph11 = cell.getParagraphs().get(0);
			ConfigurationEvidenceData.createdBordLine(paragraph11, Borders.BASIC_BLACK_DASHES);

			run11 = paragraph11.createRun();
			run11.setBold(true);
			run11.setText("Executado por:  ");

			run11 = paragraph11.createRun();
			run11.setBold(false);
			run11.setText("Muly Automa��o");

			XWPFTableCell cell2 = row.createCell();
			CTTblWidth tblWidth2 = cell2.getCTTc().addNewTcPr().addNewTcW();
			tblWidth2.setW(BigInteger.valueOf(3 * twipsPerInch));
			tblWidth2.setType(STTblWidth.DXA);

			DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal1 = Calendar.getInstance();
			String date1 = dateFormat1.format(cal1.getTime());
			paragraph11 = cell2.getParagraphs().get(0);
			ConfigurationEvidenceData.createdBordLine(paragraph11, Borders.BASIC_BLACK_DASHES);

			run11 = paragraph11.createRun();
			run11.setBold(true);
			run11.setText("Data: ");

			run11 = paragraph11.createRun();
			run11.setBold(false);
			run11.setText(date1);

			XWPFTableCell cell3 = row.createCell();
			CTTblWidth tblWidth3 = cell3.getCTTc().addNewTcPr().addNewTcW();
			tblWidth3.setW(BigInteger.valueOf(4 * twipsPerInch));
			tblWidth3.setType(STTblWidth.DXA);

			paragraph11 = cell3.getParagraphs().get(0);
			ConfigurationEvidenceData.createdBordLine(paragraph11, Borders.BASIC_BLACK_DASHES);
			run11 = paragraph11.createRun();
			run11.setText("    Vers�o: 1.0");

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
