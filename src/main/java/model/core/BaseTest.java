package model.core;

import static model.core.DriverFactory.getDriver;
import static model.core.DriverFactory.killDriver;
import static model.core.Properties.CLOSE_BROWNSER;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class BaseTest {

	
	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy--HH_mm_ss SSS");
	protected static Date timeStamps;
	protected static String executionTestName;
	protected static String evidencePath;
	protected static int evidenceCount;
	
	public BaseTest() {
		
	}
	
	public BaseTest(String executionTestName){
		BaseTest.executionTestName = executionTestName;
	}
	
	public static Date getTimeStamps() {
		timeStamps = new Date();
		return timeStamps;
	}
	
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before() {
		getTimeStamps();
		evidencePath = System.getProperty("user.dir")+"/outPut/"+sdf.format(timeStamps);
		

	}
	
	@After
	public void afterTest() throws IOException, InterruptedException {
		
		evidenceCount = 0;
		
		if(CLOSE_BROWNSER) {
			killDriver();
		}
		
	}
	
	protected static void scrolltoElement(WebElement element) {
		executarJavascript("arguments[0].scrollIntoViewIfNeeded({behavior: 'instant', block: 'center',inline: 'center'})", element);
	}
	
	protected static void refresh() {
		getDriver().navigate().refresh();
	}
	
	public static Object executarJavascript(String cmd, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, params);
	}
	

	
	
}
