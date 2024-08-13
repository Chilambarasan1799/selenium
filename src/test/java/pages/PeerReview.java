package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.io.Files;

import utilities.ExcelReader;
import utilities.ReUserKeywords;
import utilities.WorkflowTestData;
import utilities.modalityTestData;
import utilities.orderEntryTestData;

public class PeerReview {
	static orderEntryTestData orderEntryData = ExcelReader.readOrderEntryData("hosptalInfo");
	static WorkflowTestData workflowData = ExcelReader.readWorklistData("workflow");
	static modalityTestData ModalitySimulator=ExcelReader.readModalitySimulator("modality");
	
	private static WebDriver driver;

	public PeerReview(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@href='/ris/finishedreports/index']")
	private WebElement Reportpage;

	@FindBy(xpath = "//*[@id='finishRepMRNumber' and @ name='FinishedReportRequest.PatientMRNumber']")
	private WebElement MRNnumber;

	@FindBy(xpath = "//input[@class='button medium' and @id='frSearch']")
	private WebElement SearchButton;

	@FindBy(xpath = "//input[@class='actionChk worklistfinrepburncdChkbox peerreviewchkbox wlCheckBox' and @type='checkbox']")
	private WebElement PeerCheckbox;

	@FindBy(xpath = "//*[@class=' button peerreviewbutton']")
	private WebElement PeerReviewButton;

	@FindBy(xpath = "//input[@type='text' and @id='prpatmrn']")
	private WebElement MRNPatient;

	@FindBy(xpath = "//input[@role='checkbox' and @id='jqg_tblPeerReview_1']")
	private WebElement PeerReviewCheckbox;

	@FindBy(xpath = "//input[@id='assignforpeerreviewrad' and @type='button']")
	private WebElement AssignPeerReviewRadiology;

	@FindBy(xpath = "//input[@id='unScheduled' and @type='checkbox']")
	private WebElement UnscheduledCheckbox;

	@FindBy(xpath = "//input[@id='assignforpeerreview' and @value='Assign for Peer Review']")
	private WebElement AssignForPeerReview;

	@FindBy(xpath = "//button[@class='ech-multiselect ui-widget ui-state-default ui-corner-all']")
	private WebElement status;

	@FindBy(xpath = "//*[@id='ech-multiselect-Status-option-0']")
	private WebElement UnAssigned;

	@FindBy(xpath = "//*[@id='ech-multiselect-Status-option-1']")
	private WebElement Assigned;

	@FindBy(xpath = "//input[@id='ech-multiselect-Status-option-3']")
	private WebElement ReviewCheckbox;

	@FindBy(xpath = "//*[@id='GetPeerReviewList' and @value='Get PR Study Details']")
	private WebElement GetPRStudyDetails;

	@FindBy(xpath = "//*[@id='logout']")
	private WebElement signout;

	@FindBy(xpath = "//*[@href='/qa/peerreview/index']")
	private WebElement peerreview;

	@FindBy(xpath = "//*[@class='patnameclickfromPR']")
	private WebElement PatientName;

	@FindBy(xpath = "//input[@name='grade-selection-radio-220716-RAD' and @value='11']")
	private WebElement SelectGrade;

	@FindBy(xpath = "//*[@id='tabs-220716-RAD-1']/textarea")
	private WebElement text;

	@FindBy(xpath = "//*[@id='btnSubmit-220716-RAD-1']")
	private WebElement submit;

	@FindBy(xpath = "//*[@id='peerReviewCompleted']")
	private WebElement ReviewCompleted;

	public void Report() throws InterruptedException {
		ReUserKeywords.javaScriptExecutor(driver, Reportpage);

		MRNnumber.sendKeys(ModalitySimulator.getPatientID() + Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ReUserKeywords.javaScriptExecutor(driver, PeerCheckbox);
		Thread.sleep(1000);

		ReUserKeywords.javaScriptExecutor(driver, PeerReviewButton);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void ReviewPage() throws InterruptedException {
		MRNPatient.sendKeys(ModalitySimulator.getPatientID() + Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, PeerReviewCheckbox);
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, AssignPeerReviewRadiology);
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, UnscheduledCheckbox);
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By
				.xpath("//td[normalize-space(text())='RAD,ER Test']/preceding-sibling::td//input[@id='AssignRADID']")));
		Thread.sleep(500);
//		ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"+workflowData+"']/preceding-sibling::td//input[@id='AssignRADID']")));

		ReUserKeywords.javaScriptExecutor(driver, AssignForPeerReview);
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, status);
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, UnAssigned);
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, Assigned);
		Thread.sleep(500);
		MRNPatient.sendKeys(ModalitySimulator.getPatientID());
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, GetPRStudyDetails);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ReUserKeywords.javaScriptExecutor(driver, signout);
	}

	public void peerreview() throws InterruptedException {
		ReUserKeywords.javaScriptExecutor(driver, peerreview);
		Thread.sleep(500);
		MRNPatient.sendKeys(ModalitySimulator.getPatientID() + Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ReUserKeywords.javaScriptExecutor(driver, PatientName);

		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, SelectGrade);
		Thread.sleep(500);
		text.sendKeys("test");

		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, submit);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, ReviewCompleted);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ReUserKeywords.javaScriptExecutor(driver, signout);
	}

	public void CheckPeerReview() throws InterruptedException {
		ReUserKeywords.javaScriptExecutor(driver, peerreview);
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, status);
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, UnAssigned);
		Thread.sleep(500);
		ReUserKeywords.javaScriptExecutor(driver, ReviewCheckbox);
		Thread.sleep(500);
		MRNPatient.sendKeys(ModalitySimulator.getPatientID() + Keys.ENTER);

		Thread.sleep(10000);

	}

}
