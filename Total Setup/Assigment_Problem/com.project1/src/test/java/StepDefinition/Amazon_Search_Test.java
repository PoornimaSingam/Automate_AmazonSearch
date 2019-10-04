package StepDefinition;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//https://stackoverflow.com/questions/38852472/to-test-ecommerce-sorted-itemshigh-to-low-price-are-displaying-properly-or-not

public class Amazon_Search_Test {

	public static WebDriver driver;	
	
	List<WebElement> links = null;
	
	@Given("^open amazon site \"([^\"]*)\"$")	
	public void open_amazon_site(String site) throws Throwable {	
		
		/*System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\geckodriver.exe");		
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(site);*/
		
	     // System Property for Chrome Driver   
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\chromedriver.exe");
        // Instantiate a ChromeDriver class.     
        driver=new ChromeDriver();  
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(site);
        
       
	}

	@When("^user searches for product \"([^\"]*)\"$")
	public void user_searches_for_product(String searchKey) throws Throwable {
		//enter the text that you want to search
		driver.findElement(By.xpath(".//input[@id='twotabsearchtextbox']")).sendKeys(searchKey);
		driver.findElement(By.xpath("//input[@value='Go']")).click();
		
	}

	@When("^user clicks on sorts functionality to sort the results from High to low price$")
	public void user_clicks_on_sorts_functionality_to_sort_the_results_from_High_to_low_price() throws Throwable {
	    Thread.sleep(20);
	    
	    Select select = new Select(driver.findElement(By.name("s")));
	    Thread.sleep(10);
	    select.selectByVisibleText("Price: High to Low");
	}

	@When("^user clicks on the second product from sorted result page$")
	public void user_clicks_on_the_second_product_from_sorted_result_page() throws Throwable {
	    
				
		links = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		System.out.println(" links Size : "+links.size());
		for(int i =0;i<links.size();i++)
		{
			if(i==1)
			{
				System.out.println(" list : "+links.get(i).getText());
				links.get(i).click();
			}
		}
	}

	@Then("^verify in details page whether product topic contains text \"([^\"]*)\"$")
	public void verify_in_details_page_whether_product_topic_contains_text(String arg1) throws Throwable {	    
				
		String actualString = driver.findElement(By.cssSelector("#productTitle")).getText();
		assertTrue(actualString.contains("Nikon D3X"));
		driver.quit();
		
	}
}
