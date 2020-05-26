package model.utilities.doc;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class ConfigurationEvidenceData {

	XWPFParagraph paragraph;

	public ConfigurationEvidenceData() {

	}

	public static void createdBordLine(XWPFParagraph paragraph, Borders Border) throws Exception {
		try {
			paragraph.setBorderBottom(Border);
			paragraph.setBorderLeft(Border);
			paragraph.setBorderRight(Border);
			paragraph.setBorderTop(Border);
		} catch (Exception e) {
			throw new Exception("Erro ao colocar as bordas no parágrafo");
		}
		
	}

	public static void setFont(XWPFRun run, String fontName, int sizeFont, boolean setBold, String setText)
			throws Exception {
		try {
			run.setFontFamily(fontName);
			run.setFontSize(sizeFont);
			run.setBold(setBold);
			run.setText(setText);
		} catch (Exception e) {
			throw new Exception("Erro ao colocar a fonte no parágrafo");
		}

	}

	public static void setFont(XWPFRun run, String fontName, int sizeFont, boolean setBold) throws Exception {
		try {
			run.setFontFamily(fontName);
			run.setFontSize(sizeFont);
			run.setBold(setBold);
		} catch (Exception e) {
			throw new Exception("Erro ao colocar a fonte no parágrafo");
		}

	}

	public static void setImage(XWPFRun run, String result, File aprovado, File reprovado) throws Exception {
		try {
			run.addPicture(new FileInputStream(result.toString().trim() == "Passed" ? aprovado : reprovado),
					XWPFDocument.PICTURE_TYPE_PNG, System.getProperty("user.dir") + File.separator + "imagens",
					Units.pixelToEMU(350), Units.pixelToEMU(350));
		} catch (Exception e) {
			throw new Exception("Erro ao colocar a imagem na evidência");
		}
	}
	
	public static void addBreaks(XWPFRun run, int i) {
		for(int cont = 0; cont < i; cont++) {
			run.addBreak();
		}
	}

}
