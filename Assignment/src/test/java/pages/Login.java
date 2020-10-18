package pages;


import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login  {

	@FindBy(linkText="Sign in")
	private WebElement signIn;
	
	@FindBy(name="identifier")
	private WebElement name;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement password;

	@FindBy(xpath="//span[text()='Next']/following-sibling::div")
	private WebElement nextButton;
	
	@FindBy(xpath="//span[contains(text(),'Wrong password')]")
	private WebElement wrongPwdMsg;

	 WebDriver driver;
	 
	 WebDriverWait wait;
	
	public Login(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = wait;	
        driver.get("https://www.google.com/gmail/about/#");
    }
	
	public Compose loginToGmail(String usrName, String pwd) {
		enterUserAndPwd(usrName,pwd);
		wait.until(ExpectedConditions.urlContains("#inbox"));
		String urlToBe = "https://mail.google.com/mail/u/0/#inbox";
		Assert.assertEquals(urlToBe, driver.getCurrentUrl());
		Compose compose = new Compose(driver,wait);
		return compose;
	}
	
	
	public void loginToGmailWrongPwd(String usrName, String pwd) {
		enterUserAndPwd(usrName,pwd);
		wait.until(ExpectedConditions.visibilityOf(wrongPwdMsg));
		String wrongPwdExpectedMsg = "Wrong password. Try again or click Forgot password to reset it.";
		Assert.assertEquals(wrongPwdExpectedMsg, wrongPwdMsg.getText());

	}

	
	private void enterUserAndPwd(String usrName, String pwd) {
		String parentWinHandle = driver.getWindowHandle();
		signIn.click();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		Set<String> winhandles = driver.getWindowHandles();

		for (String winHandle : winhandles) {
			if (!winHandle.equals(parentWinHandle)) {
				driver.switchTo().window(winHandle);
				break;
			}
		}

		name.sendKeys(usrName);
		nextButton.click();
		
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(password)));
		password.sendKeys(pwd);
		nextButton.click();

	}

}
