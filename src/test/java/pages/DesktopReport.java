package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;
import utilities.ExcelReader;
import utilities.WorkflowTestData;
import utilities.modalityTestData;
import utilities.orderEntryTestData;

public class DesktopReport {

	static orderEntryTestData orderEntryData = ExcelReader.readOrderEntryData("hosptalInfo");
	static WorkflowTestData workflowData = ExcelReader.readWorklistData("workflow");
	static modalityTestData ModalitySimulator = ExcelReader.readModalitySimulator("modality");

	public void report() throws MalformedURLException, InterruptedException, AWTException {

		  try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	       
	            Robot robot = new Robot();
	            robot.keyPress(KeyEvent.VK_ALT);
	            robot.keyPress(KeyEvent.VK_SPACE);
	            robot.keyPress(KeyEvent.VK_N);
	            robot.keyRelease(KeyEvent.VK_ALT);
	            robot.keyRelease(KeyEvent.VK_SPACE);
	            robot.keyRelease(KeyEvent.VK_N);
	       
	        
	        try {
	            Thread.sleep(10000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        DesiredCapabilities cap = new DesiredCapabilities();
	        cap.setCapability("app","Root");
	        WindowsDriver<WebElement> driver = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723/"), cap);
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        
	        driver.findElementByClassName("TX17_DOTNET").sendKeys(workflowData.getDTextBox());
	       
	        
	        driver.findElementByName("2:Misspelled English word/words (History section or other); Incorrect accession number and social security number entered.").click();
	       
	        
	         driver.findElementByAccessibilityId("commentBox234817").sendKeys(workflowData.getDComments());
	        
	        
	         driver.findElementByName("Save Draft").click();
	        
	         driver.findElementByName("OK").click();
	        
	        driver.findElementByAccessibilityId("OnlySave").click();
	        driver.findElementByAccessibilityId("CompleteStudy").click();
	        driver.findElementByName("OK").click();
	        Thread.sleep(7000);
		
	        Robot robot1 = new Robot();
            robot1.keyPress(KeyEvent.VK_ALT);
            robot1.keyPress(KeyEvent.VK_SPACE);
            robot1.keyPress(KeyEvent.VK_N);
            robot1.keyRelease(KeyEvent.VK_ALT);
            robot1.keyRelease(KeyEvent.VK_SPACE);
            robot1.keyRelease(KeyEvent.VK_N);
	}
}
