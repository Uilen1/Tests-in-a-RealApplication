package model.utilities;

import static model.core.DriverFactory.driver;
import static model.core.DriverFactory.getDriver;
import static model.core.Properties.DISABLE_ELEMENT_HIGHLIGHTS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import model.core.BaseTest;
import model.core.Properties;

public class GetScreenShoot extends BaseTest {
	
	public GetScreenShoot() {
	}

	public static void getEvidenceElement(String nameTest, WebElement... elements) {
		try {
			for(WebElement element:elements) {
				scrolltoElement(element);
				TakesScreenshot takeSs = (TakesScreenshot) getDriver();
				File file = takeSs.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(file, new File(System.getProperty("user.dir") + ""+File.separator+"outPut"
									+File.separator+""+sdf.format(timeStamps)+""+File.separator+""+"00"+evidenceCount+"_"+nameTest+".png"));
				higthlight(nameTest, element);
				evidenceCount++;
			}
			
			} catch (Exception e) {

				System.out.println("Error in get evidence: " + e.getMessage());
			}
	}

	protected static void higthlight(String name, WebElement... elements) {
		if (DISABLE_ELEMENT_HIGHLIGHTS)
			return;
		String imagePath = evidencePath + "/"+"00"+evidenceCount+"_"+name+".png";

		try {
			String[] rectArgs = Properties.EVIDENCE_ARGS.split(" ");
			double percentScale = getScreenScale();
			BufferedImage myPicture = ImageIO.read(new File(imagePath));
			Graphics2D g2D = (Graphics2D) myPicture.getGraphics();
			g2D.setStroke(new BasicStroke(Integer.parseInt(rectArgs[3])));
			g2D.setColor(new Color(Integer.parseInt(rectArgs[0]), Integer.parseInt(rectArgs[1]),
					Integer.parseInt(rectArgs[2])));

			for (WebElement element : elements) {
				WebElement webElement;
				switch (getLocatorFromWebElement(element)) {
				case "id":
					webElement = driver.findElement(By.id(getValueFromWebElement(element)));
					break;
				case "className":
					webElement = driver.findElement(By.className(getValueFromWebElement(element)));
					break;
				case "tagName":
					webElement = driver.findElement(By.tagName(getValueFromWebElement(element)));
					break;
				case "xpath":
					webElement = driver.findElement(By.xpath(getValueFromWebElement(element)));
					break;
				case "cssSelector":
					webElement = driver.findElement(By.cssSelector(getValueFromWebElement(element)));
					break;
				case "linkText":
					webElement = driver.findElement(By.linkText(getValueFromWebElement(element)));
					break;
				case "name":
					webElement = driver.findElement(By.name(getValueFromWebElement(element)));
					break;
				case "partialLinkText":
					webElement = driver.findElement(By.partialLinkText(getValueFromWebElement(element)));
					break;
				default:
					throw new IllegalStateException("locator : " + getLocatorFromWebElement(element) + " not found!!!");
				}

				int width = (int) (webElement.getSize().getWidth() * percentScale);
				int height = (int) (webElement.getSize().getHeight() * percentScale);
				String yPosition = executarJavascript("return arguments[0].getBoundingClientRect().top", webElement)
						.toString();
				String xPosition = executarJavascript("return arguments[0].getBoundingClientRect().left", webElement)
						.toString();
				int y = (int) (Integer.parseInt(
						(yPosition.contains(".") ? yPosition.substring(0, yPosition.indexOf(".")) : yPosition))
						* percentScale);
				int x = (int) (Integer.parseInt(
						(xPosition.contains(".") ? xPosition.substring(0, xPosition.indexOf(".")) : xPosition))
						* percentScale);

				g2D.drawRect(x, y, width, height);
			}
			g2D.dispose();
			ImageIO.write(myPicture, "PNG", new File(imagePath));

		} catch (IOException e) {
			System.out.println("Error print Highlight (" + e.getMessage() + ") Evidence: " + name);
		}

	}

}
