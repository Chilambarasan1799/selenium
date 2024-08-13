package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReUserKeywords {

	public static Select select;
	public static Actions actions;
	public static JavascriptExecutor executor;
	public static FluentWait wait;
	public static WebDriverWait webDriverWait;

	public static void selectDropDownOption(WebElement element, Object value){
	 select =new Select(element);
     if(value instanceof String){
         select.selectByVisibleText((String) value);
     }else if(value instanceof Integer){
         select.selectByIndex((Integer) value);
     }else {

         throw new RuntimeException("Looking text is not available in dropdown option");
     }  
     
   }
	
	
	 public static void javaScriptExecutor(WebDriver driver,WebElement element){

	        executor=(JavascriptExecutor)driver;
	        executor.executeScript("arguments[0].scrollIntoView(true);",element);
	        executor.executeScript("arguments[0].click();", element);

	    }
	 
	 
	 public static void alert() {
		 
		 ////button[@class='closeAlert']
	 }
	 
	 
	 public static void checkMultipleExpectedCondition(WebDriver driver, By element) {
		 
//		 	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		 WebDriverWait wait=new WebDriverWait(driver,20);
		    wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(element)));
		 
		    
		 
	 }
	 
	 public static void waitForElementToApear(WebDriver driver, WebElement element, int duration) {
		 
//		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		 WebDriverWait wait=new WebDriverWait(driver, duration);
		 wait.until(ExpectedConditions.visibilityOf(element));
	 } 
	
	 
	
}
