package pages;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.io.Files;

import utilities.ExcelReader;
import utilities.ReUserKeywords;
import utilities.WorkflowTestData;
import utilities.modalityTestData;
import utilities.orderEntryTestData;

public class ReportPage {

	static orderEntryTestData orderEntryData = ExcelReader.readOrderEntryData("hosptalInfo");
	static WorkflowTestData workflowData = ExcelReader.readWorklistData("workflow");
	static modalityTestData ModalitySimulator=ExcelReader.readModalitySimulator("modality");
	
	private WebDriver driver;
	DesktopReport report=new DesktopReport();

	public ReportPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@class='signout']")
	private WebElement signout;

	@FindBy(xpath = "//button[@class='closeAlert']")
	private WebElement notifyAlertRight;

	@FindBy(xpath = "//*[@href='/ris/worklist/index']")
	private WebElement worklist;

	@FindBy(xpath = "//input[@displayvalue='MRNumber']")
	private WebElement MRNumberSearchBox;

	@FindBy(xpath = "//*[@wfname='AcceptStudy' and @class='performAction workflow ui-silk inline ui-silk-acceptStudy']")
	private WebElement AcceptStudy;

	@FindBy(xpath = "//*[@class='provideQA workflow ui-silk inline ui-silk-provideQa']")
	private WebElement ProvideQA;

	@FindBy(xpath = "//*[@id='reportingRte_ifr']")
	private WebElement Repotingtext;

	@FindBy(xpath = "//*[@id='mceu_16-button' and @role='presentation']")
	private WebElement savebutton;

	@FindBy(xpath = "//*[@class='button do-action' and @data-action='CompleteStudy']")
	private WebElement CompleteRead;

	@FindBy(xpath = "//*[@name='grade-selection-radio-234817-DEO' and @value='24']")
	private WebElement SelectGrade;

	@FindBy(xpath = "//textarea[@class='commentsTxtArea']")
	private WebElement commentTextArea;

	@FindBy(xpath = "//input[@id='btnSubmit-234817-DEO-0' and @value='Submit']")
	private WebElement SubmitButton;

	@FindBy(xpath = "//*[@class='clearFilter button small']")
	private WebElement clearfilter;
	
	@FindBy(xpath="//*[@href='/ris/finishedreports/index']")
	private WebElement Reportpage;
	
	@FindBy(xpath = "//*[@id='finishRepMRNumber']")
	private WebElement MRNnumber;
	
	@FindBy(xpath = "//*[@id='frSearch']")
	private WebElement search;
	
	@FindBy(xpath="//*[@href='javascript:void(0)' and @class='ui-silk inline jViewerWithReporting clickImageOrReport openWPFReport']")
	private WebElement Report;

	public void MRNnumber() {
		ReUserKeywords.javaScriptExecutor(driver, worklist);
	

		ReUserKeywords.javaScriptExecutor(driver, notifyAlertRight);
		try {
//			boolean flag = driver.findElements(By.xpath("//*[@class='clearFilter button small']")).size() > 0;
//			if (flag) {
//
////				 clearfilter.click();
//				System.out.println("Clear filter button clicked.");
//			}
			ReUserKeywords.javaScriptExecutor(driver, clearfilter);
			System.out.println("Clear filter button clicked");

		} catch (NoSuchElementException e) {
			System.out.println("Clear filter button not present, skipping.");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		    ReUserKeywords.javaScriptExecutor(driver, clearfilter);

		MRNumberSearchBox.sendKeys(ModalitySimulator.getPatientID() + Keys.ENTER);
		// MRNumberSearchBox.submit();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	public void AcceptStudyAndProvideQA() throws InterruptedException, MalformedURLException, AWTException {
		AcceptStudy.click();
		Thread.sleep(500);
		Report.click();
		Thread.sleep(4000);
		report.report(); 
		
//		ProvideQA.click();
//		Thread.sleep(500);
//		
//		try {
//			ReUserKeywords.javaScriptExecutor(driver, SelectGrade);
//			Thread.sleep(500);
////	        SelectGrade.click(); 
//			commentTextArea.sendKeys("test");
//			
//			Thread.sleep(500);
//			ReUserKeywords.javaScriptExecutor(driver, SubmitButton);
//		} catch (NoSuchElementException e) {
//			System.out.println("selectGrade is failure");
//		}

	}

	public void Repotingtext() throws InterruptedException {
		Repotingtext.sendKeys(workflowData.getReportingText());
		Thread.sleep(500);
		savebutton.click();
		Thread.sleep(500);
		CompleteRead.click();
		


		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			 Alert alert=driver.switchTo().alert();
	         alert.accept();
		} catch (NoSuchElementException e) {
			System.out.println("Alert message is failure");
		}
//        

		ReUserKeywords.javaScriptExecutor(driver, signout);

	}
	
	public void Repotingtext1() throws InterruptedException {
		Repotingtext.sendKeys(Keys.ENTER);;
		Thread.sleep(500);
		Repotingtext.sendKeys(workflowData.getReportingText2());
		Thread.sleep(500);
		savebutton.click();
		Thread.sleep(500);
		CompleteRead.click();
		

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 Alert alert=driver.switchTo().alert();
	         alert.accept();
		} catch (NoSuchElementException e) {
			System.out.println("Alert message is failure");
		}
        
	

		ReUserKeywords.javaScriptExecutor(driver, signout);

	}
	
	public void ReportPages() throws InterruptedException {
		ReUserKeywords.javaScriptExecutor(driver, Reportpage);
		
		MRNnumber.sendKeys(ModalitySimulator.getPatientID());
		
		search.click();
		
		Thread.sleep(10000);
	}
	public void OneReadReportingText() throws InterruptedException {
		Repotingtext.sendKeys(workflowData.getReportingText());
		Thread.sleep(500);
		savebutton.click();
		Thread.sleep(500);
		CompleteRead.click();
		
	

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 Alert alert=driver.switchTo().alert();
	         alert.accept();
		} catch (NoSuchElementException e) {
			System.out.println("Alert message is failure");
		}


//		ReUserKeywords.javaScriptExecutor(driver, signout);

	}

}
