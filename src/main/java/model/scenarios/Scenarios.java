package model.scenarios;

import io.qameta.allure.Story;
import model.core.BaseTest;
import model.core.Constants;
import model.core.Properties;
import model.pages.InsertAccountPage;
import model.pages.MovementPage;
import model.pages.ResumePage;
import model.pages.UpdateAccountPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.List;

@RunWith(Parameterized.class)
public class Scenarios extends BaseTest {

	private InsertAccountPage insertAccount = new InsertAccountPage();
	private UpdateAccountPage updateAccount = new UpdateAccountPage();
	private MovementPage movement = new MovementPage();
	private ResumePage resumePage = new ResumePage();
	private static String jsonFileName = "example";
	private static String dataGroup = "CT_01";

	public Scenarios(String executeTestName, Object excelData) {
		super(executeTestName, excelData);
	}

	@Parameters(name = "{0}")
	public static List<Object> parametersToTest() throws Exception {
		return loadData(jsonFileName,dataGroup);
	}
	
	@Test
	@Story("Testando a aplicacao 'Seu Barriga' de ponta a ponta")
	public void Test() throws Exception {
		try {
			insertAccount.insertAccount((String) jsonObjectData.get("vNameAccount"));
			
			  insertAccount.checkExceptions((String) jsonObjectData.get("vNameAccount"));
			  updateAccount.updateAccount((String) jsonObjectData.get("vUpdateAccount"),
			  (String) jsonObjectData.get("vNameAccount"));
			  movement.toCreateMovement((String) jsonObjectData.get("vCreateMovement"));
			  resumePage.deleteMovementAccount();
			 		
		} catch (Exception e) {
			Properties.RESULT_TEST = e.getMessage();
			if (Properties.RESULT_TEST != "" || Properties.RESULT_TEST != null) {
				Properties.RESULT_TEST = "Failed";
				System.out.println("[RESULT] = FAILED! \n");
				System.out.println(Constants.DIRETORIO_EVIDENCIAS + "\n");
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
