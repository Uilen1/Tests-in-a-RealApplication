package model.scenarios;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.core.BaseTest;
import model.pages.InsertAccountPage;
import model.pages.MovementPage;
import model.pages.ResumePage;
import model.pages.UpdateAccountPage;
import model.utilities.excel.DataDictionary;

@RunWith(Parameterized.class)
public class Scenarios extends BaseTest{
	
	private InsertAccountPage insertAccount = new InsertAccountPage();
	private UpdateAccountPage updateAccount = new UpdateAccountPage();
	private MovementPage movement = new MovementPage();
	private ResumePage resumePage = new ResumePage();
	
	public Scenarios (String executeTestName, DataDictionary excelData) {
		super(executeTestName, excelData);
	}
	
	@Parameters(name = "{0}")
	public static List<Object> parametersToTest() {
		return loadData();
	}
	
	
	@Test
	public void Test() throws Exception {
		insertAccount.insertAccount((String)excelData.get("vNameAccount"));
		insertAccount.checkExceptions((String)excelData.get("vNameAccount"));
		updateAccount.updateAccount((String)excelData.get("vUpdateAccount"), (String)excelData.get("vNameAccount"));
		movement.toCreateMovement((String)excelData.get("vCreateMovement"));
		resumePage.deleteMovementAccount();
		
	}
	
}
