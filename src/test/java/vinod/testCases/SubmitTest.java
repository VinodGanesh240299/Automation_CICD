package vinod.testCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import vinod.pageobjects.LandingPage;
import vinod.testcomponents.BaseTest;
import vinod.testcomponents.Retry;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubmitTest extends BaseTest{

	@Test(priority=1)
	public void submit() throws InterruptedException 
	{
		LandingPage land = new LandingPage(driver);
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("dummy999@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dummy@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,200);");  // scroll down
		
		String productName = "ZARA COAT 3";
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//Using Loops
//		for(int i=0;i<products.size();i++)
//		{
//			if(products.get(i).getText().contains("ZARA COAT 3"))
//			{
//				driver.findElements(By.cssSelector(".btn.w-10.rounded")).get(i).click();
//			}
//		}
		
		//Using Streams
		WebElement prod = products.stream().filter(s->s.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		//Thread.sleep(3000);
		
		//js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(1000);
		//js.executeScript("window.scrollTo(0, 0);");

		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(s->s.getText().equals(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys("Ind");
		
		List<WebElement> country = driver.findElements(By.cssSelector(".ta-item"));
		WebElement countryClick = country.stream().filter(s->s.getText().equals("India")).findFirst().orElse(null);
		countryClick.click();
		
		driver.findElement(By.cssSelector(".action__submit ")).click();
		String thankYou = driver.findElement(By.tagName("h1")).getText().toUpperCase();
		Assert.assertEquals(thankYou, "THANKYOU FOR THE ORDER.");
	}

}
