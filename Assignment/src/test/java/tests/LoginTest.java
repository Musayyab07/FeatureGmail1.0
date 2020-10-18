package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.InitializeDriver;
import pages.Login;

public class LoginTest extends InitializeDriver {
	
	WebDriver driver;
	
	WebDriverWait wait;
	
	@BeforeMethod()
	public void setup() {
		driver = getChromeDriver();
		wait = getWebDriverWait(driver);	
	}
	
  @Test(priority=0)
  public void login() {
		Login loginObj= new Login(driver,wait);  
		loginObj.loginToGmail("xyzabcmus@gmail.com", "$elenium451");
  }
  
  @Test(priority=1)
  public void loginWithWrongPwd() {
		Login loginObj= new Login(driver,wait);  
		loginObj.loginToGmailWrongPwd("xyzabcmus@gmail.com", "$elenium45");
  }
  
  @AfterMethod()
	public void closure() {
		driver.close();
		driver.quit();
	}
  
  
}
