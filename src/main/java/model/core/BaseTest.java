package model.core;

import static model.core.DriverFactory.killDriver;
import static model.core.Properties.CLOSE_BROWNSER;

import java.awt.Toolkit;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.WinDef;

import model.pages.LoginPage;
import model.utilities.excel.DataDictionary;
import model.utilities.excel.GlobalData;

public class BaseTest {

	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy--HH_mm_ss SSS");
	protected static Date timeStamps;
	protected static String executionTestName;
	protected static String evidencePath;
	protected static int evidenceCount;
	protected static String[] className;
	protected static String tableName;
	protected static String folderName;
	protected static HashMap<String, Object> excelData = new HashMap<String, Object>();
	protected static GlobalData data;
	private LoginPage login;

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

	@Before
	public void before() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
		evidencePath = System.getProperty("user.dir") + "/outPut/" + sdf.format(getTimeStamps());
		Properties.RESULT_TEST = "";
		data.setData(sdf.format(new Date()), (String) excelData.get("Test"), "vOutData");
		this.login = new LoginPage();
		login.setLogin();
	}

	@After
	public void afterTest() throws IOException, InterruptedException {
		data.setData(Properties.RESULT_TEST, (String) excelData.get("Test"), "Status");
		data.setBackup();
		evidenceCount = 0;
		if (CLOSE_BROWNSER) {
			killDriver();
		}

	}

	public static List<Object> loadData() throws Exception {
		className = new Throwable().getStackTrace()[1].getClassName().toString().split("\\W");
		folderName = className[className.length - 1];
		tableName = className[className.length - 2];
		data = new GlobalData(tableName, folderName);
		return data.getData();
	}

	public static double getScreenScale() throws Exception {
		WinDef.HDC hdc = GDI32.INSTANCE.CreateCompatibleDC(null);
		if (hdc != null) {
			float actual = GDI32.INSTANCE.GetDeviceCaps(hdc, 10 /* VERTRES */);
			float logical = GDI32.INSTANCE.GetDeviceCaps(hdc, 117 /* DESKTOPVERTRES */);
			GDI32.INSTANCE.DeleteDC(hdc);
			if (logical != 0 && logical / actual >= 1) {
				return logical / actual;
			}
		}
		return (Toolkit.getDefaultToolkit().getScreenResolution() / 96.0f);
	}

}
