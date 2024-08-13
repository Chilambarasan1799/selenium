package stepsDefinition;

import io.cucumber.java.en.*;
import pages.Loginpage;
import utilities.DriverFactory;
import utilities.PropertiesReader;

public class LoginStepDefinition {
	
	public Loginpage loginpage=new Loginpage(DriverFactory.getDriver());
	
	PropertiesReader propReader=new PropertiesReader();


	@Given("Launch the application")
	public void launch_the_application() {
		
	
//		 System.out.println("getting ul from prop "+propReader.initProp().get("url"));
 	DriverFactory.getDriver().get((String) propReader.initProp().get("url"));


		
	}
	
	@Given("Enter the {string} and {string}")
	public void enter_the_and(String username, String password) {
	    
		loginpage.userLogin((String)propReader.initProp().get(username), (String)propReader.initProp().get(password));
	   
	}

//	@Given("Enter the username and password")
//	public void enter_the_username_and_password() {
//		
//	loginpage.userLogin((String)propReader.initProp().get("username"), (String)propReader.initProp().get("password"));
//
//	
////		loginpage.userLogin("testing.team", "ZAcxsdf@321");
//	
//	}

	@Then("Click on signin button")
	public void click_on_signin_button() {
		
		
	}
}
