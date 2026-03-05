package vinod.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import vinod.pageobjects.LandingPage;
import vinod.pageobjects.ProductCataloguePage;
import vinod.testcomponents.BaseTest;
import vinod.testcomponents.Retry;

import java.io.IOException;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"Error"}, priority=3, retryAnalyzer=Retry.class)
	public void error() throws InterruptedException, IOException 
	{ 
		
		LandingPage land = new LandingPage(driver);
		land.goTo();
		land.login("dummy99@gmail.com", "Dummy@123");
		System.out.println(land.getErrorMessage());
		Assert.assertEquals(land.getErrorMessage(), "Incorrect email or password.");
	}

}
