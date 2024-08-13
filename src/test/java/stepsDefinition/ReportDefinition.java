package stepsDefinition;

import java.awt.AWTException;
import java.net.MalformedURLException;

import io.cucumber.java.en.Then;
import pages.Loginpage;
import pages.ReportPage;
import utilities.DriverFactory;
import utilities.PropertiesReader;

public class ReportDefinition {
	
    public Loginpage loginpage=new Loginpage(DriverFactory.getDriver());
	
	public PropertiesReader propReader=new PropertiesReader();
	public ReportPage reportpage=new ReportPage(DriverFactory.getDriver());
	
	@Then("Enter the Report username and report password")
	public void enter_the_report_username_and_report_password() {
		
		loginpage.userLogin((String)propReader.initProp().get("username1"), (String)propReader.initProp().get("password1"));

	}
   
    
	@Then("Fill the Report information")
	public void fill_the_report_information() throws InterruptedException, MalformedURLException, AWTException {
		reportpage.MRNnumber();
		reportpage.AcceptStudyAndProvideQA();
		reportpage.Repotingtext();
	}

}
