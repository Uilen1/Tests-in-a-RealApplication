package model.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
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

public class CreateFileDoc {

	public static void createEvidenceInDoc(String suiteName, String classTestName, String executionTestName,
			String timeStamps, String result, String date) throws InvalidFormatException, IOException {
		try {

			File dir = new File(System.getProperty("user.dir") + File.separator + "outPut");
			File[] files = dir.listFiles();
			Arrays.sort(files, new Comparator<File>() {

				@Override
				public int compare(File f1, File f2) {
					long result = f2.lastModified() - f1.lastModified();
					if (f1.isFile()) {
						return 1;
					}
					if (result > 0) {
						return -1;
					} else if (result < 0) {
						return 1;
					} else {
						return 0;
					}
				}
			});

			File folder = new File(files[0].getAbsolutePath() + File.separator + classTestName + File.separator
					+ executionTestName +File.separator+ "evidencesDoc" +File.separator+ timeStamps);
			if(!folder.isDirectory()) {
				folder.mkdirs();
			}
			dir = new File(files[0].toString() + File.separator + classTestName + File.separator + executionTestName
					+ File.separator + "evidenceScreenShoot" + File.separator + timeStamps);
			files = dir.listFiles();
			if (!(files == null)) {
				Arrays.sort(files, new Comparator<File>() {

					@Override
					public int compare(File f2, File f1) {
						long result = f2.lastModified() - f1.lastModified();
						if (result > 0) {
							return 1;
						} else if (result < 0) {
							return -1;
						} else {
							return 0;

						}
					}
				});
			}

			XWPFDocument document = new XWPFDocument(OPCPackage.open(System.getProperty("user.dir") + File.separator
					+ "outPut" + File.separator + "TemplateEvidencia.docx"));
			// criar paragrafo
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
			paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);
			paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);
			paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);

			XWPFRun run = paragraph.createRun();
			run.setFontFamily("Arial");
			run.setFontSize(11);
			run.setText("Caso de teste: " + executionTestName);

			XWPFRun run2 = paragraph.createRun();
			run2.setFontSize(11);
			run2.setFontFamily("Arial");
			run2.addBreak();
			run2.setText("Status: " + result);

			XWPFRun run4 = paragraph.createRun();
			run4.setFontSize(11);
			run4.setFontFamily("Arial");
			run4.addBreak();
			run4.setText("Data e hora da execução: " + date);

			XWPFRun run5 = paragraph.createRun();
			run5.setFontSize(11);
			run5.setFontFamily("Arial");
			run5.addBreak();
			run5.setText("Duração da execução: ???");

			XWPFRun prRun1 = paragraph.createRun();
			prRun1.setFontFamily("Arial");
			prRun1.setFontSize(11);
			prRun1.addBreak();
			prRun1.setBold(false);
			prRun1.setText("Result: ");

			XWPFRun prRun2 = paragraph.createRun();
			prRun2.setFontFamily("Arial");
			prRun2.setFontSize(11);
			prRun2.setBold(false);
			prRun2.addBreak();
			prRun2.setText(result.length() > 300 ? result.substring(0, 300) + "[...]" : result);

			XWPFParagraph paragraph1 = document.createParagraph();

			paragraph1.setSpacingAfterLines(1);
			InputStream pic = null;
			XWPFParagraph paragraphOne = document.createParagraph();
			XWPFRun paragraphOneRunOne = paragraphOne.createRun();
			paragraphOneRunOne.addBreak();

			for (File file : files) {
				try {
					pic = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				XWPFParagraph paragraphFive = document.createParagraph();
				paragraphFive.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun paragraphFiveRunOne = paragraphFive.createRun();
				try {
					paragraphFiveRunOne.addPicture(new FileInputStream(file), XWPFDocument.PICTURE_TYPE_PNG,
							file.getAbsolutePath(), Units.toEMU(446), Units.toEMU(257));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					pic.close();
				}
			}
			FileOutputStream outStream = null;
			try {
				outStream = new FileOutputStream(
						folder.getAbsolutePath() +File.separator+ executionTestName + ".docx");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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
				paragraph11.setBorderBottom(Borders.BASIC_BLACK_DASHES);
				paragraph11.setBorderLeft(Borders.BASIC_BLACK_DASHES);
				paragraph11.setBorderRight(Borders.BASIC_BLACK_DASHES);
				paragraph11.setBorderTop(Borders.BASIC_BLACK_DASHES);

				run11 = paragraph11.createRun();
				run11.setBold(true);
				run11.setText("Executado por:  ");

				run11 = paragraph11.createRun();
				run11.setBold(false);
				run11.setText("Automação");

				XWPFTableCell cell2 = row.createCell();
				CTTblWidth tblWidth2 = cell2.getCTTc().addNewTcPr().addNewTcW();
				tblWidth2.setW(BigInteger.valueOf(3 * twipsPerInch));
				tblWidth2.setType(STTblWidth.DXA);

				DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal1 = Calendar.getInstance();
				String date1 = dateFormat1.format(cal1.getTime());
				paragraph11 = cell2.getParagraphs().get(0);
				paragraph11.setBorderBottom(Borders.BASIC_BLACK_DASHES);
				paragraph11.setBorderLeft(Borders.BASIC_BLACK_DASHES);
				paragraph11.setBorderRight(Borders.BASIC_BLACK_DASHES);
				paragraph11.setBorderTop(Borders.BASIC_BLACK_DASHES);

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
				paragraph11.setBorderBottom(Borders.BASIC_BLACK_DASHES);
				paragraph11.setBorderLeft(Borders.BASIC_BLACK_DASHES);
				paragraph11.setBorderRight(Borders.BASIC_BLACK_DASHES);
				paragraph11.setBorderTop(Borders.BASIC_BLACK_DASHES);

				run11 = paragraph11.createRun();
				run11.setText("    Versão: 1.0");

				document.write(outStream);
				outStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
