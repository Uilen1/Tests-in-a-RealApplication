package model.suite;

import static model.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	
})

public class Suites {

	@AfterClass
	public static void closeAll() {
		killDriver();
	}
	
	
}
