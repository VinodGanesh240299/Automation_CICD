package vinod.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinod.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	private WebElement email;
	
	@FindBy(id="userPassword")
	private WebElement pass;
	
	@FindBy(id="login")
	private WebElement login;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	private WebElement errorMessage;
	
	public void login(String mail, String password) throws InterruptedException
	{
		email.sendKeys(mail);
		pass.sendKeys(password);
		login.click();
		Thread.sleep(1000);
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public String getErrorMessage()
	{
		return errorMessage.getText();
	}
	
//	driver.findElement(By.id("userEmail")).sendKeys("dummy999@gmail.com");
//	driver.findElement(By.id("userPassword")).sendKeys("Dummy@123");
//	driver.findElement(By.id("login")).click();
}
