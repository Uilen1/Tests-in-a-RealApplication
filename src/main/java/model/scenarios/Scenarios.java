package model.scenarios;

import org.junit.Test;

import model.core.BaseTest;
import model.pages.InsertAccount;
import model.pages.Movement;
import model.pages.UpdateAccount;

public class Scenarios extends BaseTest{
	
	private InsertAccount insertAccount = new InsertAccount();
	private UpdateAccount updateAccount = new UpdateAccount();
	private Movement movement = new Movement();
	
	
	@Test
	public void Test() {
		insertAccount.insertAccount("Uilen Helei");
		insertAccount.checkExceptions("Uilen Helei");
		updateAccount.updateAccount("Lelles Moreira");
		movement.toCreateMovement("uilen");
		
		
	}
	
}
