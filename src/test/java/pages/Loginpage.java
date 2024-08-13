package pages;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.io.Files;

import utilities.ReUserKeywords;

public class Loginpage {
	private WebDriver driver;

	public Loginpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input#loginID")
	private WebElement userName;

	@FindBy(css = "input#password")
	private WebElement password;

	@FindBy(css = "input#SignIn")
	private WebElement siginButton;
	


	public void userLogin(String username, String userpassword) {

		By user = By.cssSelector("input#loginID");
		By pass = By.cssSelector("input#password");

		ReUserKeywords.checkMultipleExpectedCondition(driver, user);
		ReUserKeywords.checkMultipleExpectedCondition(driver, pass);

		userName.sendKeys(username);

		password.sendKeys(userpassword);


		siginButton.click();
		
		String parentId=driver.getWindowHandle();

//		 driver.findElement(pass).click();
		 
		Set<String> listOfWindows=  driver.getWindowHandles();
		String child1=null;
		
		Iterator<String> I1= listOfWindows.iterator();

		while(I1.hasNext())
		{

		 child1=I1.next();


		if(!parentId.equals(child1))
		{
		driver.switchTo().window(child1);

		System.out.println(driver.switchTo().window(child1).getTitle());

		driver.close();
		}
	}

	}
}
