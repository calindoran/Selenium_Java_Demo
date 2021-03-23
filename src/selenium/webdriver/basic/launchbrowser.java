package selenium.webdriver.basic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class launchbrowser {
	
	public static WebDriver driver = null;

	public static void main(String[] args) throws InterruptedException
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//open web app
		driver.navigate().to("https://amazon.com");
		driver.manage().window().maximize();;
		String title = driver.getTitle();
		
		if (title.equalsIgnoreCase("Amazon.com"))
			System.out.println("Title match");
		else
			System.out.println(title);
		
		//locate an element
		String tagname = " ";
		tagname = driver.findElement(By.cssSelector("#nav-hamburger-menu")).getText();
		System.out.println(tagname);
		
		//dropdown menu
		WebElement category = driver.findElement(By.cssSelector("#nav-main > div.nav-left"));
		Actions action = new Actions(driver);
		action.moveToElement(category).click().perform();
		Thread.sleep(500);
		
		WebElement books = driver.findElement(By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible > li:nth-child(3) > a"));
		action.moveToElement(books).click().perform();
		Thread.sleep(500);
		
		driver.findElement(By.linkText("Kindle Books")).click();
		Thread.sleep(500);
		
		//typing text
		WebElement myElement = driver.findElement(By.id("twotabsearchtextbox"));
		myElement.sendKeys("John Grisham");
		
		driver.findElement(By.className("nav-search-submit-text")).click();
		Thread.sleep(500);
		
		//select book
		driver.findElement(By.partialLinkText("A Time for")).click();
		Thread.sleep(500);
		
		//change tab control
		java.util.Set<String> handles = driver.getWindowHandles();
		String winHandle01 = driver.getWindowHandle();
		handles.remove(winHandle01);
		
		String winHandle = handles.iterator().next();
		String winHandle02 = " ";
		if (winHandle != winHandle01) 
		{
			//retrieve handle of second win, extract handle 
			winHandle02 = winHandle; //store handle of second win handle
			//switch control to new win
			driver.switchTo().window(winHandle02);
			System.out.println(winHandle02);
		}
		Thread.sleep(500);

		//add to cart
		driver.findElement(By.cssSelector("#add-to-cart-button")).click();
		Thread.sleep(500);
		
		//scroll to end of page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(500);
		
		//iFrames
		driver.get("http://demo.automationtesting.in/Frames.html");
		WebElement frame = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(frame);
		Thread.sleep(1000);
		
		WebElement textbox = driver.findElement(By.xpath("//input[@type='text']"));
		textbox.sendKeys("Hi!");
		Thread.sleep(2000);
		
		driver.close();
		driver.quit();
		
	}

}
