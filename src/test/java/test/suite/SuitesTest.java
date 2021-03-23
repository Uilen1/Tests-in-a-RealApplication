package test.suite;

import model.scenarios.Scenarios;
import model.scenarios.Scenarios1;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import static model.core.DriverFactory.killDriver;

@RunWith(Suite.class)

@SuiteClasses({Scenarios.class, Scenarios1.class
	
})

public class SuitesTest {

	@AfterClass
	public static void closeAll() {
		killDriver();
	}
	
	
}
