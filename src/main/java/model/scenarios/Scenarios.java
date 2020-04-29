package model.scenarios;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.core.BaseTest;
import model.core.Constants;
import model.core.Properties;
import model.pages.InsertAccountPage;
import model.pages.MovementPage;
import model.pages.ResumePage;
import model.pages.UpdateAccountPage;
import model.utilities.excel.DataDictionary;

@RunWith(Parameterized.class)
public class Scenarios extends BaseTest {

	private InsertAccountPage insertAccount = new InsertAccountPage();
	private UpdateAccountPage updateAccount = new UpdateAccountPage();
	private MovementPage movement = new MovementPage();
	private ResumePage resumePage = new ResumePage();

	public Scenarios(String executeTestName, DataDictionary excelData) {
		super(executeTestName, excelData);
	}

	@Parameters(name = "{0}")
	public static List<Object> parametersToTest() throws Exception {
		return loadData();
	}
	
	@Before
	public void beforeTest() {
		System.out.println(Constants.CABECALHO);
		System.out.println(Constants.DIRETORIO_RAIZ);
	}

	@Test
	public void Test() throws Exception {
		try {
			insertAccount.insertAccount((String) excelData.get("vNameAccount"));
			insertAccount.checkExceptions((String) excelData.get("vNameAccount"));
			updateAccount.updateAccount((String) excelData.get("vUpdateAccount"),
					(String) excelData.get("vNameAccount"));
			movement.toCreateMovement((String) excelData.get("vCreateMovement"));
			resumePage.deleteMovementAccount();
			
		} catch (Exception e) {
			Properties.RESULT_TEST = e.getMessage();
			if (Properties.RESULT_TEST != "" || Properties.RESULT_TEST != null) {
				Properties.RESULT_TEST = "Failed";
				System.out.println("[RESULT] = FAILED! \n");
				System.out.println(e.getMessage());
				throw new Exception(e.getMessage());
			}

		} finally {
			 if(Properties.RESULT_TEST == ""){
				Properties.RESULT_TEST = "Passed";
				System.out.println("[RESULT] = SUCCESS! \n");
			}
		}

	}	

}
