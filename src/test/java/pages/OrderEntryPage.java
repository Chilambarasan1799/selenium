package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.xml.xpath.XPath;

import org.bouncycastle.crypto.RuntimeCryptoException;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import utilities.ExcelReader;
import utilities.ReUserKeywords;
import utilities.WorkflowTestData;
import utilities.modalityTestData;
import utilities.orderEntryTestData;

public class OrderEntryPage {

	static orderEntryTestData orderEntryData = ExcelReader.readOrderEntryData("hosptalInfo");
	static WorkflowTestData workflowData = ExcelReader.readWorklistData("workflow");
	static modalityTestData ModalitySimulator = ExcelReader.readModalitySimulator("modality");

	private static WebDriver driver;

	public OrderEntryPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='nav']/li[3]/a")
	private WebElement OrderEntry;

	@FindBy(xpath = "//select[@id='StudySource']")
	private WebElement selectStudySource;

	@FindBy(xpath = "//input[@id='AdditionalFaxNumber']")
	private WebElement AdditionfaxNumber;

	@FindBy(xpath = "//select[@id='Physician']")
	private WebElement physician;

	@FindBy(xpath = "//input[@id='RefPhysician']")
	private WebElement AlternatePhysician;

	@FindBy(xpath = "//select[@id='OrderType']")
	private WebElement Criticality;

	@FindBy(xpath = "//select[@id='PatientDetails_108_']")
	private WebElement InsuranceType;

	@FindBy(xpath = "//select[@id='PatientDetails_101_']")
	private WebElement patientLocation;

	@FindBy(xpath = "//input[@class='patdetails' and @id='Provider_Phone_Number']")
	private WebElement ProviderPhoneNumbe;

	@FindBy(xpath = "//input[@id='MRNumber']")
	private WebElement MRNNumber;

	@FindBy(xpath = "//input[@id='fName']")
	private WebElement Firstname;

	@FindBy(xpath = "//*[@name='lName']")
	private WebElement Lastname;

	@FindBy(xpath = "//input[@id='AgeY']")
	private WebElement Age;

	@FindBy(xpath = "//select[@id='Modality']")
	private WebElement Modality;

	@FindBy(xpath = "//input[@id='ImageCount']")
	private WebElement ImageCount;

	@FindBy(xpath = "//textarea[@id='Clinical_History']")
	private WebElement ClinicalHistory;

	@FindBy(css = ("input#reportOnly"))
	private WebElement ReportOnly;

	@FindBy(css = ("input#TimeOutPerformed"))
	private WebElement timeoutPerformedCheckBox;

	@FindBy(xpath = "//input[@id='Create']")
	private WebElement createorder;

	@FindBy(xpath = "//*[@href='/ris/reconciliation/index']")
	private WebElement reconcilition;

	@FindBy(xpath = "//*[@id='glSearch']/a")
	private WebElement search;

	@FindBy(xpath = "//*[@id='categorylist']")
	private WebElement categorylist;

	@FindBy(xpath = "//input[@type='text' and @id='textbox']")
	private WebElement textbox;

	@FindBy(xpath = "//*[@href='/ris/worklist/index']")
	private WebElement worklist;

	@FindBy(xpath = "//*[@class='clearFilter button small']")
	private WebElement ClearFilter;

	@FindBy(xpath = "//*[@filterexpression='Patient MR Number']")
	private WebElement MRNTextbox;

	@FindBy(xpath = "//*[@id='UpdateOrder']")
	private WebElement UpdateOrder;

	@FindBy(xpath = "//*[@id='gs_PatientMRNumber']")
	private WebElement MRNSearch;

	public void Reconcilition() throws InterruptedException {
		reconcilition.click();
		Thread.sleep(2000);
		search.click();
		Thread.sleep(2000);

		categorylist.click();
		Thread.sleep(2000);

		categorylist.sendKeys("Patient MR Number");
		categorylist.click();
		Thread.sleep(2000);

		textbox.sendKeys(ModalitySimulator.getPatientID() + Keys.ENTER);

		Thread.sleep(1000);
		try {
			WebElement NRStudies = driver.findElement(By.xpath("//*[@id='1']/td[4]/a[1]"));
			NRStudies.click();

			MRNSearch.sendKeys(ModalitySimulator.getPatientID() + Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='1']/td[6]/a")).click();
			Thread.sleep(1000);

			fillHospitalInformation();

			verifyStudyDetails();

			createOrder();

		} catch (NoSuchElementException e) {
			WebElement patientname = driver.findElement(By.xpath("//*[@class='wlpatientname'][1]"));
			patientname.click();

			ReUserKeywords.javaScriptExecutor(driver, worklist);

			ReUserKeywords.javaScriptExecutor(driver, ClearFilter);

			MRNTextbox.sendKeys(ModalitySimulator.getPatientID() + Keys.ENTER);

			WebElement EditOrder = driver.findElement(By.xpath("//span[contains(text(),'"
					+ ModalitySimulator.getPatientID()
					+ "')]/parent::*/preceding-sibling::td//a[@class='ui-silk inline ui-silk-page-white-edit']"));
			EditOrder.click();

			fillHospitalInformation();

			verifyStudyDetails();

			createOrder();

		}

	}

	public void fillHospitalInformation() {

		String Radiobutton = orderEntryData.getRadioButton();
		if (Radiobutton.equalsIgnoreCase("Testing")) {
			driver.findElement(By.xpath("//*[@name='grpid' and @value='3682']")).click();
		} else if (Radiobutton.equalsIgnoreCase("Prelim Completed")) {
			driver.findElement(By.xpath("//*[@name='grpid' and @value='235581']")).click();
		}

		ReUserKeywords.selectDropDownOption(patientLocation, orderEntryData.getPatientLocation());

		Lastname.sendKeys(orderEntryData.getLastName());

//		ReportOnly.click();

		ImageCount.sendKeys(String.valueOf(orderEntryData.getImagecount()));

		ClinicalHistory.sendKeys(orderEntryData.getClinicalHistory());

	}

	public void verifyStudyDetails() {

//		ReUserKeywords.selectDropDownOption(Modality, orderEntryData.getModality());
//		 Modality.sendKeys(orderEntryData.getModality());

//		ReUserKeywords.javaScriptExecutor(driver, SelectProcedure);
//		try {
//			WebElement SelectProcedure = driver.findElement(By.xpath("//*[@id='ddcl-Procedure']/span/div"));
//			SelectProcedure.click();
//			driver.findElement(By.xpath("//*[@id='ddcl-Procedure-ddw']/div/div[2]/label")).click();
//
//		} catch (NoSuchElementException e) {

		WebElement SelectProcedure = driver.findElement(By.xpath("//span[text()='Select Procedure(s)']/parent::*"));
		SelectProcedure.click();
		multiSelectProcedureOption(orderEntryData.getSelectProcedure());
//		}

//		multiSelectProcedureOption(orderEntryData.getSelectProcedure());

//		 ReportOnly.click();

	}

	public static void multiSelectProcedureOption(String selectOption) {

		WebElement element = driver
				.findElement(By.xpath("//span[contains(text(),'" + selectOption + "')]/preceding-sibling::input"));
		ReUserKeywords.javaScriptExecutor(driver, element);

		// span[contains(text(),'3D Recon w/ Ind Workstation')]/preceding-sibling::input

	}

	public void createOrder() {
		try {

			timeoutPerformedCheckBox.click();

			createorder.click();
		} catch (NoSuchElementException e) {

			UpdateOrder.click();
		}

//		 verifyWorkFlow();
	}

	public static void verifyWorkFlow() {

		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'" + ModalitySimulator.getPatientID()
				+ "')]/parent::*/preceding-sibling::td//a[contains(text(),'Update workflow parameters')]"));

		ReUserKeywords.javaScriptExecutor(driver, element);
		// span[contains(text(),'silambu17')]/parent::*/preceding-sibling::td//a[contains(text(),'Update
		// workflow parameters')]
	}

}
