package vinod.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinod.abstractComponent.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	private List<WebElement> products;
	
	//driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	@FindBy(css="button[routerlink*='cart']")
	private WebElement cartButton;
	
	//driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	private WebElement proceed;
	
	//driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys("Ind");
	@FindBy(css="[placeholder*='Country']")
	private WebElement country;
	
	//driver.findElements(By.cssSelector(".ta-item"));
	@FindBy(css=".ta-item")
	private List<WebElement> countryList;
	
	//driver.findElement(By.cssSelector(".action__submit ")).click();
	@FindBy(css=".action__submit ")
	private WebElement submit;
	
	//driver.findElement(By.tagName("h1")).getText().toUpperCase();
	@FindBy(tagName="h1")
	private WebElement thankYou;
	

	By appear = By.cssSelector(".mb-3");
	By toast = By.cssSelector("#toast-container");
	By disappear = By.cssSelector(".ng-animating");
	
	public void waitAndScrollDown()
	{
		elementToAppear(appear);
		scrollDown();
	}
	
	public void selectProduct(String productName)
	{
		WebElement prod = products.stream().filter(s->s.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	}
	
	public void waitAndScrollUp() throws InterruptedException
	{
		elementToAppear(toast);
		elementToDisppear(disappear);
		//scrollUp();
	}
	
	public void clickCartButton()
	{
		cartButton.click();
	}
	
	public Boolean getCartProducts(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(s->s.getText().equals(productName));
		return match;
	}
	
	public void proceed()
	{
		proceed.click();
	}
	
	public void selectCountry(String countryName)
	{
		country.sendKeys(countryName);
		WebElement countryClick = countryList.stream().filter(s->s.getText().equals("India")).findFirst().orElse(null);
		countryClick.click();
	}
	
	public void submit()
	{
		submit.click();
	}
	
	public String getThankYouText()
	{
		String thankYouText = thankYou.getText().toUpperCase();
		return thankYouText;
	}
	

}
