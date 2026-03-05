package vinod.testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vinod.abstractComponent.AbstractComponent;
import vinod.pageobjects.LandingPage;
import vinod.pageobjects.OrdersPage;
import vinod.pageobjects.ProductCataloguePage;
import vinod.testcomponents.BaseTest;

public class DataProviderClass extends BaseTest{

	@Test(dataProvider="getData")
	public void dataprovidermethod(HashMap<String,String> input) throws InterruptedException, IOException 
	{
		LandingPage land = new LandingPage(driver);
		land.goTo();
		land.login("dummy999@gmail.com", "Dummy@123");
		
		ProductCataloguePage page = new ProductCataloguePage(driver);
		//page.waitAndScrollDown();
		page.selectProduct(input.get("product"));
		page.waitAndScrollUp();
		page.clickCartButton();
		Boolean match = page.getCartProducts(input.get("product"));
		
		Assert.assertTrue(match);
		
		page.proceed();
		page.selectCountry("Ind");
		
		page.submit();
		String thankYou = page.getThankYouText();
		Assert.assertEquals(thankYou, "THANKYOU FOR THE ORDER.");
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		AbstractComponent a = new AbstractComponent(driver);
		List<HashMap<String,String>> hash = a.getJsonData("C:\\Users\\PRITHVI B\\Web_Automation\\SeleniumFrameworkDesign\\src\\test\\java\\vinod\\testData\\testdata.json");
		return new Object[][] { 
									{hash.get(0)},
									{hash.get(1)}
							  };
	}

}
