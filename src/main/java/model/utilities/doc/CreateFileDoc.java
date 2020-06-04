package model.utilities.doc;

import java.io.File;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class CreateFileDoc {
	
	
	
	public static void createEvidenceInDoc(String suiteName, String classTestName, String executionTestName,
			String timeStamps, String executionTime, String result, String date) throws Exception {
		try {
			
			File dir = new File(System.getProperty("user.dir") + File.separator + "outPut");
			File[] files = dir.listFiles();
			
			CompareFiles compareFiles = new CompareFiles();
			
			compareFiles.compareReverseOrder(files);
			
			File folder = new File(files[0].getAbsolutePath() + File.separator + classTestName + File.separator
					+ executionTestName + File.separator + "evidencesDoc" + File.separator + timeStamps);
			
			if (!folder.isDirectory()) {
				folder.mkdirs();
			}
			
			dir = new File(files[0].toString() + File.separator + classTestName + File.separator + executionTestName
					+ File.separator + "evidenceScreenShoot" + File.separator + timeStamps);
			
			files = dir.listFiles();
			
			if (!(files == null)) {
				
				compareFiles.compareNaturalOrder(files);
			}

			XWPFDocument document = new XWPFDocument(OPCPackage.open(System.getProperty("user.dir") + File.separator
					+ "outPut" + File.separator + "TemplateEvidencia.docx"));
			EvidenceData evidenceData = new EvidenceData(executionTestName, executionTime, result, date, document);

			evidenceData.createFirstPage();
			evidenceData.setContentPage(files);
			//evidenceData.createHeader("Seu Barriga", date);
			evidenceData.setDataInHeader("by: Uilen Helei");
			evidenceData.createFooter();
			evidenceData.CreateDocument(folder);

		} catch (Exception e) {
			throw new Exception("Erro ao criar a Evidência");
		}
	}
}
