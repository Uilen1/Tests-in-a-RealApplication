package model.utilities.screenshoot;

import static model.core.DriverFactory.getDriver;

import java.io.File;

import io.qameta.allure.Attachment;
import model.core.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.core.BasePage;
import model.core.BaseTest;

public class GetScreenShoot{
	static BasePage basePage = new BasePage();

	public GetScreenShoot() {
	}

	public static void getEvidenceElement(String nameTest, WebElement... elements) throws Exception {
		try {
			TakesScreenshot takeSs = (TakesScreenshot) getDriver();
			if(Constants.CREATE_ATTACHMENT_ALLURE){
				saveScreenshotPNGAllure(getDriver());
			}
			File file = takeSs.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file,
					new File(BaseTest.evidencePath + File.separator + "00" + BaseTest.evidenceCount + "_"
							+ nameTest + ".png"));
			for (WebElement element : elements) {
				Higthlight.higthlight(nameTest, element);
			}
			BaseTest.evidenceCount++;

		} catch (Exception e) {

			throw new Exception("Error in get evidence: " + e.getMessage());
		}
	}

	public static void getEvidenceElement(String nameTest, WebElement element) throws Exception {
		try {
				basePage.scrolltoElement(element);
				TakesScreenshot takeSs = (TakesScreenshot) getDriver();
				if(Constants.CREATE_ATTACHMENT_ALLURE){
					saveScreenshotPNGAllure(getDriver());
				}
				File file = takeSs.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(file,
						new File(BaseTest.evidencePath + File.separator + "00" + BaseTest.evidenceCount + "_"
								+ nameTest + ".png"));
				Higthlight.higthlight(nameTest, element);
				BaseTest.evidenceCount++;

		} catch (Exception e) {

			throw new Exception("Error in get evidence: " + e.getMessage());
		}
	}

	public static void getEvidenceElement(String nameTest) throws Exception {
		try {
			TakesScreenshot takeSs = (TakesScreenshot) getDriver();
			if(Constants.CREATE_ATTACHMENT_ALLURE){
				saveScreenshotPNGAllure(getDriver());
			}
			File file = takeSs.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file,
					new File(BaseTest.evidencePath + File.separator + "00" + BaseTest.evidenceCount + "_"
							+ nameTest + ".png"));
			BaseTest.evidenceCount++;

		} catch (Exception e) {

			throw new Exception("Error in get evidence: " + e.getMessage());
		}
	}

	@Attachment(value = "Page Screenshot", type = "image/png")
	public static byte[] saveScreenshotPNGAllure(WebDriver driver){
		TakesScreenshot takeSs = (TakesScreenshot) getDriver();
		return takeSs.getScreenshotAs(OutputType.BYTES);
	}

}
