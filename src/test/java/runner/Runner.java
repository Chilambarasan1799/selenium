package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = "src/test/java/features/", glue = { "stepsDefinition", "appHooks" },

//		plugin = { "pretty",
//				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, 
//				 plugin = {"pretty", "tech.grasshopper.extent.ExtentCucumberAdapter:"},
		
		 	plugin = {"pretty",
					"html:target/cucumber/report.html",
					"timeline:target/cucumber",
					"me.jvt.cucumber.report.PrettyReports:target/cucumber" },

		monochrome = true, 
		
		publish = true,
			        
		dryRun = false,
		
		tags="@smoke"
  		)

public class Runner extends AbstractTestNGCucumberTests {
	
	

	  
	 @DataProvider(parallel = false) public Object[][] scenarios(){
		  return super.scenarios(); }
	 

}
