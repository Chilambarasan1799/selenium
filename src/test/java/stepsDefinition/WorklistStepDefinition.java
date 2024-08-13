package stepsDefinition;

import java.awt.AWTException;
import java.net.MalformedURLException;

import io.cucumber.java.en.Then;
import pages.Loginpage;
import pages.PeerReview;
import pages.ReportPage;
import pages.Worklistpage;
import utilities.DriverFactory;
import utilities.ExcelReader;
import utilities.PropertiesReader;
import utilities.WorkflowTestData;
import utilities.modalityTestData;
import utilities.orderEntryTestData;

public class WorklistStepDefinition {
	
	
	static orderEntryTestData orderEntryData = ExcelReader.readOrderEntryData("hosptalInfo");
	static WorkflowTestData workflowData = ExcelReader.readWorklistData("workflow");
	static modalityTestData ModalitySimulator=ExcelReader.readModalitySimulator("modality");
	
	Worklistpage worklistpage=new Worklistpage(DriverFactory.getDriver());
	public Loginpage loginpage=new Loginpage(DriverFactory.getDriver());
	
	WorkflowTestData testData=ExcelReader.readWorklistData("workflow");
	PropertiesReader propReader=new PropertiesReader();
	public ReportPage reportpage=new ReportPage(DriverFactory.getDriver());
	
	public PeerReview peerreview=new PeerReview(DriverFactory.getDriver());
	
	
	@Then("verify components in worklist page")
	public void fill_the_hospital_information() {
	    
		worklistpage.verifyWorkFlow();
		worklistpage.updateWorkflowParameter();
		
	}
	@Then("assign rad to in rad pop up page")
	public void assignedBasedNumberOfRad() throws InterruptedException, MalformedURLException, AWTException{
		
		if(testData.getTotalReads()==2) {
			worklistpage.verifyRadPopUP(testData.getTotalReads());
			loginpage.userLogin((String)propReader.initProp().get("newuser"), (String)propReader.initProp().get("newpass"));
			reportpage.MRNnumber();
			reportpage.AcceptStudyAndProvideQA();
			reportpage.Repotingtext();
			loginpage.userLogin((String)propReader.initProp().get("seconduser"), (String)propReader.initProp().get("secondpassword"));
			reportpage.MRNnumber();
			reportpage.AcceptStudyAndProvideQA();
			reportpage.Repotingtext1();
			loginpage.userLogin((String)propReader.initProp().get("username"), (String)propReader.initProp().get("password"));
			reportpage.ReportPages();
			
		}else {
			
			worklistpage.verifyRadPopUP(testData.getTotalReads());
			loginpage.userLogin((String)propReader.initProp().get("1ReadRaduser"), (String)propReader.initProp().get("1ReadRadpass"));
			reportpage.MRNnumber();
			reportpage.AcceptStudyAndProvideQA();
//			reportpage.OneReadReportingText();
			
			
			String PeerReview=workflowData.getPeerReview();
			if(PeerReview.equalsIgnoreCase("YES")) {
				//Next 5 lines is peer review code
				loginpage.userLogin((String)propReader.initProp().get("username"), (String)propReader.initProp().get("password"));
				peerreview.Report();
				peerreview.ReviewPage();
				loginpage.userLogin((String)propReader.initProp().get("1ReadPeerUser"), (String)propReader.initProp().get("1ReadPeerPassword"));
				peerreview.peerreview();
				loginpage.userLogin((String)propReader.initProp().get("username"), (String)propReader.initProp().get("password"));
				peerreview.CheckPeerReview();
			}else if(PeerReview.equalsIgnoreCase("NO")) {
				
			}
			
			
			
			
		}
		 
	}

}
