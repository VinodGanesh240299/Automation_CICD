package vinod.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinod.abstractComponent.AbstractComponent;

public class OrdersPage extends AbstractComponent{

WebDriver driver;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	@FindBy(css="ul li:nth-child(3) button")
	private WebElement orders;
	
	public void clickOrder()
	{
		orders.click();
	}
	
	public Boolean verifyOrder(String productName)
	{
		Boolean match = productNames.stream().anyMatch(s->s.getText().equals(productName));
		return match;
	}
}
