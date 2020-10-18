package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Compose {
	
    @FindBy(xpath="//div[text()='Compose']")
    private WebElement composeButton;
    
    @FindBy(xpath="//div[@class=\"nH Hd\"]")
    private WebElement composeDialogBox;

  
    @FindBy(xpath="//textarea[@name=\"to\"]")
    private WebElement to;
    
    @FindBy(xpath="//input[@name=\"subjectbox\"]")
    private WebElement subject;
  
    @FindBy(xpath="//div[text()=\"Send\"]")
    private WebElement send;
    
    @FindBy(xpath="//img[@class=\"Ha\"]")
    private WebElement closeDialog;
    
  
    @FindBy(xpath="//span[text()='Message sent.']")
    private WebElement mailSentConfirmation;
    
    @FindBy(xpath="//div[@class='vX UC']")
    private WebElement loading;
    
    @FindBy(xpath="//span[text()='Cc']")
    private WebElement ccLink;
    
    @FindBy(xpath="//textarea[@name='cc']")
    private WebElement ccInput;
    
  
    
    WebDriver driver;
	 
	 WebDriverWait wait;
	
	public Compose(WebDriver driver, WebDriverWait wait){
       this.driver = driver;
       PageFactory.initElements(driver, this);
       this.wait = wait;
   }
    
  
    public void sendEmail(String emailId,String sub) {
    	wait.until(ExpectedConditions.elementToBeClickable(composeButton));
    	composeButton.click();
    	wait.until(ExpectedConditions.elementToBeClickable(to));
    	to.sendKeys(emailId);
    	subject.sendKeys(sub);
    	send.click();
    	wait.until(ExpectedConditions.visibilityOf(mailSentConfirmation));
    	String expected = "Message sent.";
    	System.out.println(mailSentConfirmation.getText());
    	Assert.assertEquals(expected, mailSentConfirmation.getText());
    }
    
    public void sendEmailWithoutSubjectAndBody(String emailId) {
    	wait.until(ExpectedConditions.elementToBeClickable(composeButton));
    	composeButton.click();
    	wait.until(ExpectedConditions.elementToBeClickable(to));
    	to.sendKeys(emailId);
    	send.click();
    	String expected = "Send this message without a subject or text in the body?";
    	Alert alert = driver.switchTo().alert();
    	Assert.assertEquals(expected, alert.getText());
    	alert.dismiss();
    	closeDialog.click();
    }

    public void ccTest(String toId,String CcId,String sub) {
    	wait.until(ExpectedConditions.elementToBeClickable(composeButton));
    	composeButton.click();
    	wait.until(ExpectedConditions.elementToBeClickable(to));
    	to.sendKeys(toId);
    	ccLink.click();
    	wait.until(ExpectedConditions.elementToBeClickable(ccInput));
    	ccInput.sendKeys(CcId);
    	subject.sendKeys(sub);
    	send.click();
    	wait.until(ExpectedConditions.visibilityOf(mailSentConfirmation));
    	String expected = "Message sent.";
    	System.out.println(mailSentConfirmation.getText());
    	Assert.assertEquals(expected, mailSentConfirmation.getText());
    }
	
	
  
}
