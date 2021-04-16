package model.core;

import java.io.File;

public class Constants {
	
	public static String CABECALHO = "==================== RUN_TEST ====================\n\n";
	public static String RODAPE = "==================== END_TEST ====================\n\n";
	public static String DIRETORIO_RAIZ = "[PROJECT_DIRECTORY] - "+System.getProperty("user.dir");
	public static String DIRETORIO_EVIDENCIAS = "[EVIDENCE_DIRECTORY] - "+System.getProperty("user.dir")+ File.separator+"outPut";
	public static String FOLDER_NAME_DATA = "Scenarios";
	public static String NAME_DATA = "scenarios";
	public static String DATA = "EXCEL"; // JSON ou EXCEL
	public static String EMAIL = "uilen@hotmail.com";
	public static String SENHA = "123456";
	public static Boolean CREATE_REPORT_DOCX = Boolean.FALSE;
	public static Boolean CREATE_ATTACHMENT_ALLURE = Boolean.FALSE;

	

}
