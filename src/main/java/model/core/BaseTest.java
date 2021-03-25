package model.core;

import static model.core.DriverFactory.driver;
import static model.core.DriverFactory.killDriver;
import static model.core.Properties.CLOSE_BROWNSER;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import model.utilities.json.JsonData;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import model.pages.LoginPage;
import model.utilities.Utils;
import model.utilities.doc.CreateFileDoc;
import model.utilities.excel.DataDictionary;
import model.utilities.excel.GlobalData;
import org.openqa.selenium.By;

public class BaseTest {

	public static String evidencePath;
	public static int evidenceCount;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy--HH_mm_ss SSS");
	public static Date timeStamps;
	protected long startTest;
	protected long finishTest;
	protected static String executionTestName;
	protected static String[] className;
	public static String suiteName;
	protected static String classTestName;
	protected static HashMap<String, Object> excelData = new HashMap<String, Object>();
	protected static GlobalData data;
	private static JsonData jsonData = new JsonData();
	protected JSONObject jsonObjectData;
	private LoginPage login;
	private Utils utils = new Utils();

	public BaseTest() {

	}

	public BaseTest(String executionTestName, Object objectData) {
		if(Constants.DATA.contentEquals("JSON")){
			jsonObjectData = (JSONObject) objectData;
			BaseTest.executionTestName = executionTestName;
		} else if (Constants.DATA.contentEquals("EXCEL")) {
			DataDictionary dataDictionary = (DataDictionary) objectData;
			BaseTest.executionTestName = executionTestName;
			BaseTest.excelData = dataDictionary.dictionary;
		}
	}

	public static Date getTimeStamps() throws Exception {
		timeStamps = new Date();
		return timeStamps;
	}

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void setup(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
	}

	@Before
	public void before() throws Exception {
		try {
			System.out.println(Constants.CABECALHO);
			System.out.println(Constants.DIRETORIO_RAIZ);
			evidencePath = System.getProperty("user.dir") +File.separator+ "outPut" +File.separator+ suiteName+File.separator+ classTestName +File.separator+ executionTestName+File.separator+"evidenceScreenShoot" +File.separator+ sdf.format(getTimeStamps());
			Properties.RESULT_TEST = "";
			if (Constants.DATA.contentEquals("EXCEL")){
				data.setData(utils.obtainedDateFormated(new Date()), (String) excelData.get("Test"), "vOutData");
			}
			startTest = System.currentTimeMillis();
			this.login = new LoginPage();
			login.setLogin();
		} catch (Exception e) {
			throw new Exception("Erro no método Before! " + executionTestName);
		}
		
	}

	@After
	public void afterTest() throws IOException, InterruptedException,Exception {
		
		try {
			if(Constants.DATA.contentEquals("EXCEL")){
				data.setData(Properties.RESULT_TEST, (String) excelData.get("Test"), "Status");
				data.setBackup();
			}
			finishTest = System.currentTimeMillis();
			long total = finishTest - startTest;
			String executionTime = String.format("%02d:%02d:%02d", total/3600000,(total/60000)%60,(total/1000)%60);
			CreateFileDoc.createEvidenceInDoc(suiteName, classTestName, executionTestName, sdf.format(timeStamps), executionTime,Properties.RESULT_TEST, utils.obtainedDateWithHoursFormated(new Date()));
			evidenceCount = 0;
			System.out.println("\n"+Constants.RODAPE);

			if (CLOSE_BROWNSER) {
				killDriver();
			}
		} catch (Exception e) {
			throw new Exception("Erro no método AfterTest! " + executionTestName);
		}
		

	}

	public static List<Object> loadData() throws Exception {
		className = new Throwable().getStackTrace()[1].getClassName().toString().split("\\W");
		classTestName = className[className.length - 1];
		suiteName = className[className.length - 2];
		switch (Constants.DATA) {
				case "JSON":
					return jsonData.getJsonData("example","CT_01");

				case "EXCEL":
					data = new GlobalData(Constants.NAME_DATA, Constants.FOLDER_NAME_DATA);
					return data.getData();

				default:
					throw new Exception("Não foi possível retornar nenhum tipo de base de dados para os testes");
			}
	}
	public static List<Object> loadData(String jsonFileName,String DataGroup) throws Exception {
		className = new Throwable().getStackTrace()[1].getClassName().toString().split("\\W");
		classTestName = className[className.length - 1];
		suiteName = className[className.length - 2];
		switch (Constants.DATA) {
				case "JSON":
					return jsonData.getJsonData(jsonFileName,DataGroup);

				case "EXCEL":
					data = new GlobalData(Constants.NAME_DATA, Constants.FOLDER_NAME_DATA);
					return data.getData();

				default:
					throw new Exception("Não foi possível retornar nenhum tipo de base de dados para os testes");
			}
	}

}
