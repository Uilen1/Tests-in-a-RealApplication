package model.utilities;

import static model.core.DriverFactory.getDriver;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.core.BaseTest;

public class GetScreenShoot{
	static BasePage basePage = new BasePage();

	public GetScreenShoot() {
	}

	public static void getEvidenceElement(String nameTest, WebElement... elements) throws Exception {
		try {
			for (WebElement element : elements) {
				basePage.scrolltoElement(element);
				TakesScreenshot takeSs = (TakesScreenshot) getDriver();
				File file = takeSs.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(file,
						new File(BaseTest.evidencePath + File.separator + "00" + BaseTest.evidenceCount + "_"
								+ nameTest + ".png"));
				Higthlight.higthlight(nameTest, element);
				BaseTest.evidenceCount++;
			}

		} catch (Exception e) {

			System.out.println("Error in get evidence: " + e.getMessage());
		}
	}

}
