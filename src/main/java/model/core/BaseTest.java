package model.core;

import static model.core.DriverFactory.killDriver;
import static model.core.Properties.CLOSE_BROWNSER;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

public class BaseTest {

	public static String evidencePath;
	public static int evidenceCount;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy--HH_mm_ss SSS");
	public static Date timeStamps;
	protected long startTest;
	protected long finishTest;
	protected static String executionTestName;
	protected static String[] className;
	protected static String suiteName;
	protected static String classTestName;
	protected static HashMap<String, Object> excelData = new HashMap<String, Object>();
	protected static GlobalData data;
	private LoginPage login;
	private Utils utils = new Utils();

	public BaseTest() {

	}

	public BaseTest(String executionTestName, DataDictionary excelData) {
		BaseTest.executionTestName = executionTestName;
		BaseTest.excelData = excelData.dictionary;
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
			evidencePath = System.getProperty("user.dir") +File.separator+ "outPut" +File.separator+ suiteName+File.separator+ classTestName +File.separator+ executionTestName+File.separator+"evidenceScreenShoot" +File.separator+ sdf.format(getTimeStamps());
			Properties.RESULT_TEST = "";
			data.setData(utils.obtainedDateFormated(new Date()), (String) excelData.get("Test"), "vOutData");
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
			data.setData(Properties.RESULT_TEST, (String) excelData.get("Test"), "Status");
			data.setBackup();
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
		try {
			className = new Throwable().getStackTrace()[1].getClassName().toString().split("\\W");
			classTestName = className[className.length - 1];
			suiteName = className[className.length - 2];
			data = new GlobalData(suiteName, classTestName);
			return data.getData();
		} catch (Exception e) {
			throw new Exception("Erro no método loadTest! ");
		}
		
	}

}
