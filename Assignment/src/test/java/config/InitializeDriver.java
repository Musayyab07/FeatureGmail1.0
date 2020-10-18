package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InitializeDriver {

	 private static  WebDriver driver;
	
	 private  static WebDriverWait wait;
	 
	 
	 public static WebDriver getChromeDriver() {
		 System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("incognito");
		 driver = new ChromeDriver(options);
		 driver.manage().window().maximize();
		 return driver;
	 }
	 
	
	 public static WebDriverWait getWebDriverWait(WebDriver driver) {
		 wait = new WebDriverWait(driver,60);
		 return wait;
	 }
	 
}
