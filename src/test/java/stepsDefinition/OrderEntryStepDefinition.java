package stepsDefinition;

import io.cucumber.java.en.Then;
import pages.OrderEntryPage;
import utilities.DriverFactory;
import utilities.ExcelReader;
import utilities.orderEntryTestData;

public class OrderEntryStepDefinition {
	
	
	
//	@Then("Click on signin button")
//	public void click_on_signin_button() {
//		
//		
//	}
	
	
	OrderEntryPage orderEntryPage=new OrderEntryPage(DriverFactory.getDriver());
	
	
	@Then("fill the hospital Information")
	public void fill_the_hospital_information() throws InterruptedException  {
	    orderEntryPage.Reconcilition();
//		orderEntryPage.fillHospitalInformation();
//		orderEntryPage.verifyStudyDetails();
//		orderEntryPage.createOrder();
		
		
		
	}

}
