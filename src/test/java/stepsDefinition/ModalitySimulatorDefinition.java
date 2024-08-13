package stepsDefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;

import com.github.dockerjava.api.model.Driver;

import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.en.*;
import utilities.ExcelReader;
import utilities.ReUserKeywords;
import utilities.WorkflowTestData;
import utilities.modalityTestData;
import utilities.orderEntryTestData;

public class ModalitySimulatorDefinition {
	
	
	
	static orderEntryTestData orderEntryData = ExcelReader.readOrderEntryData("hosptalInfo");
	static WorkflowTestData workflowData = ExcelReader.readWorklistData("workflow");
	static modalityTestData ModalitySimulator=ExcelReader.readModalitySimulator("modality");
	
	@Given("Enter the RADSpa Modality Simulator")
	public static void  enter_the_rad_spa_modality_simulator() throws MalformedURLException, InterruptedException {

		 try {
		        Process process = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe");
		        Thread.sleep(3000);
		    } catch (IOException e) {
//		        e.printStackTrace();
		    }
		 
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability("app", "C:\\ModalitySimulator\\App\\RADSpaModalitySimulator.exe");
		WindowsDriver<WebElement> driver=new WindowsDriver<WebElement>(new URL(" http://127.0.0.1:4723/"),cap);
		
		
//		((WebElement) driver.findElementsByName("Minimize")).click();
		
		
		String mode = ModalitySimulator.getSimulatorMode();
				if(mode.equalsIgnoreCase("Import Study")) {
	        driver.findElementByAccessibilityId("radioButton1").click();
	    } else if (mode.equalsIgnoreCase("Auto-Scan")) {
	        driver.findElementByAccessibilityId("radioButton2").click();
	    }
	    
//		driver.findElementByAccessibilityId("MRcheckBox2").click();
		
		String CT=ModalitySimulator.getCT();
		if(CT.equalsIgnoreCase("YES")) {
			driver.findElementByAccessibilityId("CTcheckBox1").click();
		}else if(CT.equalsIgnoreCase("NO")){
			
		}
		
		String MR=ModalitySimulator.getMR();
		if(MR.equalsIgnoreCase("YES")) {
			driver.findElementByAccessibilityId("MRcheckBox2").click();
		}else if(MR.equalsIgnoreCase("NO")){
			
		}
		
		String US=ModalitySimulator.getUS();
		if(US.equalsIgnoreCase("YES")) {
			driver.findElementByAccessibilityId("UScheckBox3").click();
		}else if(US.equalsIgnoreCase("NO")){
			
		}
		
		String CR=ModalitySimulator.getCR();
		if(CR.equalsIgnoreCase("YES")) {
			driver.findElementByAccessibilityId("CRcheckBox4").click();
		}else if(CR.equalsIgnoreCase("NO")){
			
		}

		
		driver.findElementByAccessibilityId("RemoteAETextBox").clear();
		driver.findElementByAccessibilityId("RemoteAETextBox").sendKeys(ModalitySimulator.getAETitle());
		
		driver.findElementByAccessibilityId("RemoteIPtextBox").clear();
		driver.findElementByAccessibilityId("RemoteIPtextBox").sendKeys(ModalitySimulator.getIPAddress());
		
		
		driver.findElementByAccessibilityId("RemotePortTextBox").clear();
		double port = ModalitySimulator.getPort();
		int portInt = (int) port;
		String portString = Integer.toString(portInt);
		driver.findElementByAccessibilityId("RemotePortTextBox").sendKeys(portString);

		
		driver.findElementByAccessibilityId("echoButton").click();
		Thread.sleep(2000);
		driver.findElementByName("OK").click();
		
		driver.findElementByAccessibilityId("editPatDetailsCheckBox").click();
		
		driver.findElementByAccessibilityId("PatientNameTextBox").clear();
		driver.findElementByAccessibilityId("PatientNameTextBox").sendKeys(ModalitySimulator.getPatientName());
		
		driver.findElementByAccessibilityId("PatientIDTextBox").clear();
		driver.findElementByAccessibilityId("PatientIDTextBox").sendKeys(ModalitySimulator.getPatientID());
		
		driver.findElementByAccessibilityId("InstitutionNameTextBox").clear();
		driver.findElementByAccessibilityId("InstitutionNameTextBox").sendKeys(ModalitySimulator.getInstitutionName());
		
		driver.findElementByAccessibilityId("ageYTextBox").clear();
		double port1=ModalitySimulator.getAge();
		int portint=(int) port1;
		String portstring=Integer.toString(portint);
		driver.findElementByAccessibilityId("ageYTextBox").sendKeys(portstring);
		
		String gender = ModalitySimulator.getGender();
	    if (gender.equalsIgnoreCase("Male")) {
	        driver.findElementByAccessibilityId("mRadioButton").click();
	    } else if (gender.equalsIgnoreCase("Female")) {
	        driver.findElementByAccessibilityId("fRadioButton").click();
	    }
		
//		driver.findElementByAccessibilityId("scanTimePicker").click();
	    
	    driver.findElementByAccessibilityId("sdTextBox").sendKeys(ModalitySimulator.getStudyDescription());
	    
	    driver.findElementByAccessibilityId("scTextBox").sendKeys(ModalitySimulator.getStudyComments());
	    
	    driver.findElementByName("Start Scan").click();

		Thread.sleep(3000);
		
//		try {
//			Robot robot=new Robot();
//			robot.keyPress(KeyEvent.VK_ALT);
//			robot.keyPress(KeyEvent.VK_SPACE);
//			robot.keyPress(KeyEvent.VK_N);
//			
//		} catch (AWTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		
		driver.quit();
	}

	@Then("Fill the Modality simulator")
	public void fill_the_modality_simulator() {
		// Add implementation here
	}
	
}
