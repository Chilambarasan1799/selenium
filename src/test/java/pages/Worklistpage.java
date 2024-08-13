package pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

public class Worklistpage {

	static orderEntryTestData orderEntryData = ExcelReader.readOrderEntryData("hosptalInfo");
	static WorkflowTestData workflowData = ExcelReader.readWorklistData("workflow");
	static modalityTestData ModalitySimulator=ExcelReader.readModalitySimulator("modality");

	private static WebDriver driver;

	public Worklistpage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@id='totalstudies']")
	private WebElement worklistTotalStudies;

	@FindBy(xpath = "//button[@class='closeAlert']")
	private WebElement notifyAlertRight;

	@FindBy(xpath = "//*[@class='clearFilter button small']")
	private WebElement clearfilter;

	@FindBy(xpath = "//input[@displayvalue='MRNumber']")
	private WebElement MRNumberSearchBox;

	// Update workflow parameter
	@FindBy(xpath = "//input[@paramname='totalreads']")
	private WebElement totalReads;

	@FindBy(xpath = "//select[@class='rptTypeParam']")
	private WebElement reportType;

	@FindBy(xpath = "//input[@value='Update']")
	private WebElement updateWorkflowPrmBtn;

	@FindBy(xpath = "//*[@type='checkbox' and @id='unScheduled']")
	private WebElement unscheduledRADsCheckBox;

	@FindBy(xpath = "//button[@class='button small assign']")
	private WebElement AssignRadSubmit;

	@FindBy(xpath = "//*[@id='logout']")
	private WebElement signout;

	public void verifyWorkFlow() {

//		 ReUserKeywords.waitForElementToApear(driver, worklistTotalStudies, 3600);

//      	 try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ReUserKeywords.javaScriptExecutor(driver, notifyAlertRight);

//		boolean flag = driver.findElements(By.xpath("//*[@class='clearFilter button small']")).size() > 0;

//					 clearfilter.click(); 
//					 ReUserKeywords.javaScriptExecutor(driver, clearfilter);
					 try {
					 if (clearfilter.isDisplayed()) {
			                JavascriptExecutor js = (JavascriptExecutor) driver;
			                js.executeScript("arguments[0].click();", clearfilter);
			                System.out.println("Clear filter executed.");
			            }
			        } catch (NoSuchElementException e) {
			            System.out.println("Clear filter element not present. Skipping.");
			        }
			        
		

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MRNumberSearchBox.sendKeys(ModalitySimulator.getPatientID() + Keys.ENTER);
		// MRNumberSearchBox.submit();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement element = driver.findElement(By.xpath("(//span[contains(text(),'" + ModalitySimulator.getPatientID()
				+ "')]/parent::*/preceding-sibling::td//a[contains(text(),'Update workflow parameters')])"));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReUserKeywords.javaScriptExecutor(driver, element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// span[contains(text(),'silambu17')]/parent::*/preceding-sibling::td//a[contains(text(),'Update
		// workflow parameters')]
	}

	public void updateWorkflowParameter() {

//		System.out.println("total read value is "+(int)workflowData.getTotalReads());
		totalReads.clear();

		totalReads.sendKeys(String.valueOf((int) workflowData.getTotalReads()));

		ReUserKeywords.selectDropDownOption(reportType, workflowData.getReportType());


		updateWorkflowPrmBtn.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().refresh();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyRadPopUP(Double radNumber) {

		// (//span[contains(text(),'silambu17')]/parent::*/preceding-sibling::td//a[@wfname='AssignRADToStudy'])[1]
		try {
			WebElement element = driver.findElement(By.xpath("(//span[contains(text(),'" + ModalitySimulator.getPatientID()
			+ "')]/parent::*/preceding-sibling::td//a[@wfname='AssignRADToStudy'])"));
			element.click();

		} catch (NoSuchElementException e) {
			
			
			WebElement element = driver.findElement(By.xpath("(//span[contains(text(),'" + ModalitySimulator.getPatientID()
			+ "')]/parent::*/preceding-sibling::td//a[@wfname='ReassignRADToStudy'])"));
			element.click();
		}

		
		
		
		//span[contains(text(),'TestCase15.30')]/parent::*/preceding-sibling::td//a[@wfname='ReassignRADToStudy'])
		
//		ReUserKeywords.javaScriptExecutor(driver, element);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		unscheduledRADsCheckBox.click();

		if (radNumber == 2) {
			// td[normalize-space(text())='RAD
			// Testing2']/preceding-sibling::td//input[@id='AssignRADID']
			// ReUserKeywords.javaScriptExecutor(driver,
			// driver.findElement(By.xpath("//td[contains(text(),'"+workflowData.getRad()+"')]/preceding-sibling::td//input[@id='AssignRADID']")));
			ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"
					+ workflowData.getRad() + "']/preceding-sibling::td//input[@id='PreAssignRADID']")));

			// Team AI']/preceding-sibling::td//input[@id='AssignRADID']
			ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"
					+ workflowData.getRAD2() + "']/preceding-sibling::td//input[@id='AssignRADID']")));
		} else {

			ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"
					+ workflowData.getRad() + "']/preceding-sibling::td//input[@id='AssignRADID']")));

		}
//		if(radNumber==1) {
//			ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"
//	                + workflowData.getRad() + "']/preceding-sibling::td//input[@id='AssignRADID']")));
//		}
//		else if(radNumber==2) {
//			
//			ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"
//	                + workflowData.getRad() + "']/preceding-sibling::td//input[@id='PreAssignRADID']")));
//	        ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"
//	                + workflowData.getRAD2() + "']/preceding-sibling::td//input[@id='AssignRADID']")));
//		}
//		else {
//			System.out.println("Hii Simba");
//		}

//		switch (radNumber) {
//		case 1:
//			 ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"
//		                + workflowData.getRad() + "']/preceding-sibling::td//input[@id='PreAssignRADID']")));
//		        ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"
//		                + workflowData.getRAD2() + "']/preceding-sibling::td//input[@id='AssignRADID']")));
//			break;
//
//		default:
//			ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[normalize-space(text())='"
//	                + workflowData.getRad() + "']/preceding-sibling::td//input[@id='AssignRADID']")));
//			break;
//		}
		
		ReUserKeywords.javaScriptExecutor(driver, AssignRadSubmit);

		// td[contains(text(),'RAD')]/preceding-sibling::td//input[@id='AssignRADID']

		signout.click();

	}

//	 public void Rad2() {
//		 WebElement element= driver.findElement(By.xpath("(//span[contains(text(),'"+orderEntryData.getMRNnumber()+"')]/parent::*/preceding-sibling::td//a[@wfname='AssignRADToStudy'])[1]"));
//		 ReUserKeywords.javaScriptExecutor(driver, element);
//		 
//		 try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		 unscheduledRADsCheckBox.click();
//		 
//		 ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[contains(text(),'"+workflowData.getRad()+"')]/preceding-sibling::td//input[@id='PreAssignRADID']")));
//		 
//		 ////td[contains(text(),'RAD')]/preceding-sibling::td//input[@id='PreAssignRADID']
//		 
//		 ReUserKeywords.javaScriptExecutor(driver, driver.findElement(By.xpath("//td[contains(text(),'"+workflowData.getRad()+"')]/preceding-sibling::td//input[@id='AssignRADID']")));
//		 ////td[contains(text(),'Radspa')]/preceding-sibling::td//input[@id='AssignRADID']
//		 
//		 // //td[normalize-space(text())='Radspa RAD']/preceding-sibling::td//input[@id='AssignRADID']
//	}

}
