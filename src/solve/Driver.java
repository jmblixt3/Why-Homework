package solve;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	static String exePath = "/home/jonathan/eclipse-workspace/QUIA8/src/geckodriver";
	WebDriver driver = null;
	public Driver() 
	{
		
	}
	public void start() {
		System.setProperty("webdriver.gecko.driver",exePath);
		driver = new FirefoxDriver();
	}
	public void stop() {
		driver.close();
	}
	public WebDriver getDriver() {
		return driver;
	}
}
