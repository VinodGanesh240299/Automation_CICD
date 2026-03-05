package vinod.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	public WebDriver driver;
	
	@BeforeMethod(alwaysRun=true)
	public void initializeDriver() throws IOException, InterruptedException
	{
		//http://localhost:8080/
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\vinod\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");

			}
			driver = new ChromeDriver(options);
		}
		else if(browserName.contains("Edge"))
		{
			EdgeOptions options = new EdgeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");

			}
			driver = new EdgeDriver(options);
		}
		else if(browserName.contains("Firefox"))
		{
			FirefoxOptions options = new FirefoxOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");

			}
			driver = new FirefoxDriver(options);
		}
		
		driver.manage().window().setSize(new Dimension(1920,1080));
		//driver.manage().window().maximize();
		//return driver;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	public String getScreenshotPath(String testcaseName, WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String des = System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";
		FileUtils.copyFile(src,new File(des));
		return des;
	}

}
