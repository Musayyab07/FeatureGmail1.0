package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import config.InitializeDriver;
import pages.Compose;
import pages.Login;

public class ComposeTest extends InitializeDriver{

	WebDriver driver;

	WebDriverWait wait;
	
	Compose compose;

	@Test(priority=0)
	public void ComposeSuccess() {
		compose.sendEmail("musayyab1994@gmail.com", "TestEmail1");
	}
	
	@Test(priority=1)
	public void verifyAlertforNoBodyAndSub() {
		compose.sendEmailWithoutSubjectAndBody("musayyab1994@gmail.com");
	}
	
	@Test(priority=2)
	public void ccMail() {
		compose.ccTest("musayyab1994@gmail.com","hirolimusayyab@gmail.com","TestMultipleRecipients");
	}

	@BeforeClass
	public void setup() {
		driver = getChromeDriver();
		wait = getWebDriverWait(driver);	
		Login loginObj= new Login(driver,wait);  
		compose = loginObj.loginToGmail("xyzabcmus@gmail.com", "$elenium451");
		
	}

	@AfterClass
	public void closure() {
		driver.close();
		driver.quit();
	}

}
