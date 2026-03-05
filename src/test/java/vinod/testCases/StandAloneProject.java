package vinod.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vinod.abstractComponent.AbstractComponent;
import vinod.pageobjects.LandingPage;
import vinod.pageobjects.OrdersPage;
import vinod.pageobjects.ProductCataloguePage;
import vinod.testcomponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import vinod.testcomponents.Retry;

public class StandAloneProject extends BaseTest{

	String productName = "ZARA COAT 3";

	@Test(priority=2)
	public void StandAlone() throws InterruptedException, IOException 
	{
		LandingPage land = new LandingPage(driver);
		land.goTo();
		land.login("dummy999@gmail.com", "Dummy@123");
		
		ProductCataloguePage page = new ProductCataloguePage(driver);
		//page.waitAndScrollDown();
		page.selectProduct(productName);
		page.waitAndScrollUp();
		page.clickCartButton();
		Boolean match = page.getCartProducts(productName);
		
		Assert.assertTrue(match);
		
		page.proceed();
		page.selectCountry("Ind");
		
		page.submit();
		String thankYou = page.getThankYouText();
		Assert.assertEquals(thankYou, "THANKYOU FOR THE ORDER.");
		
	}
	
	@Test(dependsOnMethods="StandAlone", priority=3)
	public void verifyOrder() throws InterruptedException
	{
		LandingPage land = new LandingPage(driver);
		land.goTo();
		land.login("dummy999@gmail.com", "Dummy@123");
		OrdersPage order = new OrdersPage(driver);
		order.clickOrder();
		Assert.assertTrue(order.verifyOrder(productName));	
	}

}
