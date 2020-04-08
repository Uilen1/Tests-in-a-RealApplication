package model.scenarios;

import org.junit.Test;

import model.core.BaseTest;
import model.pages.InsertAccountPage;
import model.pages.MovementPage;
import model.pages.ResumePage;
import model.pages.UpdateAccountPage;

public class Scenarios extends BaseTest{
	
	private InsertAccountPage insertAccount = new InsertAccountPage();
	private UpdateAccountPage updateAccount = new UpdateAccountPage();
	private MovementPage movement = new MovementPage();
	private ResumePage resumePage = new ResumePage();
	
	
	@Test
	public void Test() {
		insertAccount.insertAccount("Uilen Helei");
		insertAccount.checkExceptions("Uilen Helei");
		updateAccount.updateAccount("Lelles Moreira");
		movement.toCreateMovement("uilen");
		resumePage.deleteMovementAccount();
		
		
	}
	
}
