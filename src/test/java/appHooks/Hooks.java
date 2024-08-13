
package appHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import stepsDefinition.ModalitySimulatorDefinition;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
 
import utilities.DriverFactory;
import utilities.PropertiesReader;

//import java.io.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Properties;



public class Hooks {
	  private DriverFactory driverFactory;
	  private WebDriver driver;
	  private PropertiesReader propertiesReader;
	   public    Properties properties;
	 
	   static String ret=System.getProperty("file.separator");
	  // these are global hooks , where hooks has pre- and post-condition for each scenario
	   
	   // Hooks is mainly before the running the features files[pre-condition] and
	   //after executing the condition what we need to do [example: driver.close]
	  @Before(order = 0)
	  public void getProperty(){
	 
		  
		  try {
			ModalitySimulatorDefinition.enter_the_rad_spa_modality_simulator();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		  try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		  propertiesReader=new PropertiesReader();
	      properties=propertiesReader.initProp();
	 
	  }
	 
	  
	  @Before(order = 1)
	  public void launchBrowsers(){
	 
	    String browserName= properties.getProperty("browser");
	    driverFactory =new DriverFactory();
	    driver=driverFactory.initDriver(browserName);
	 
	 
	  }
	 
	  @After(order = 0)
	  public void quitBrowser(){
	 
//		 DriverFactory.getDriver().close();
	 
	   // driver.close();
	 
	    if (driver!=null){
	      //driver.close();
	      driver.quit();
	    }
	 
	 
	  }
	 
	  @After(order = 1)
	  public void tearDown(Scenario scenario){
	 
	    if(scenario.isFailed()){
	 
	    	byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	  //  	File source  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            scenario.attach(screenshot, "image/png", scenario.getName());
	    	captureScreenshot(driver, scenario.getName());
	    }
	  }
	  
	  
	
		   
	  
	  public static void captureScreenshot(WebDriver driver, String screenshotName) {
		        try {
		            TakesScreenshot ts = (TakesScreenshot) driver;
		            File source = ts.getScreenshotAs(OutputType.FILE);
//		            File source=new File();
		            
		           
		            FileUtils.copyFile(source, new File(System.getProperty("user.dir")+ret+"Screenshots" +ret+ screenshotName + ".png"));
		            System.out.println("Screenshot taken");
		        } catch (Exception e) {
		            System.out.println("Exception while taking screenshot " + e.getMessage());
		        }
		    }
		
}
