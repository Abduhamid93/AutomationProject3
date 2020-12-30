package Assignment3;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import june19SeleniumLocators.Utility;

public class AutomationProject3OrderingACar {

	public static void main(String[] args) throws InterruptedException {
		

		System.setProperty("webdriver.chrome.driver", "/Users/farogaturakova/Documents/SeleniumFiles/drivers/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//1. Go to lexus.com
		driver.get("https://www.lexus.com/");
		
		
		
		//2. Verify the title of the page contains “Experience Amazing”. 
		
		Utility.assertContains("Experience Amazing", driver.getTitle());
		
		
		
		//3. Click on Do not Sell My Personal information at the bottom of the page.
		
		driver.findElement(By.xpath("//a[.='DO NOT SELL MY PERSONAL INFORMATION']")).click();
		
		
		
		//4. Verify the page title contains “Privacy Hub”.
		
		String parentWindowHandle = driver.getWindowHandle();
		
		
		
		Set<String> allHandles = driver.getWindowHandles(); //Window handles should be unique, that's why use Set
		
		for (String handle : allHandles) {
			
			if(!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
			}
			
		}
		
		Utility.assertContains("Privacy Hub", driver.getTitle());
		
		
		
		//5.Click on Your Privacy Rights.
		
		driver.findElement(By.xpath("//a[.='TMNA Privacy Statement']")).click();
		
		
		
		
		//6. Verify that the page url is “https://privacy.toyota.com/privacy-hub/privacyright.html”.
		
		Set<String> allHandles1 = driver.getWindowHandles();
		
		for (String handle : allHandles1) {
			
			if(!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
			}
			
		}
		
		Utility.assertEquals("https://www.toyota.com/support/privacy-rights", driver.getCurrentUrl());
		
		
		
		//7. Go back to the main window and click on Build your Lexus.
		
		driver.switchTo().window(parentWindowHandle);
		driver.findElement(By.xpath("//span[.='BUILD YOUR LEXUS']")).click();
		
		//8. Enter “22182” for zipcode and click on Enter on pop-up window.
		
		driver.findElement(By.id("zip-overlay")).sendKeys("22182");
		driver.findElement(By.xpath("//button[.='Enter']")).click();
		
		//9. Click on model GS.
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//span[.='GS']")).click();
		
		
		//10. Choose 2020 GS 350 F Sport AWD. Before clicking, get the price of the vehicle and save it into an int variable.
		
		int basePriceInt = 54505;
		String basePrice = "$" + String.format("%,d", basePriceInt);
		
		Thread.sleep(5000);	
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/article/div/span[2]/span/ul/li[2]/div[2]/ul/li[2]/a")).click();
		
		
		
		//11. On the next page, click on the price menu on top and retrieve and store the base price, dp&h fee and msrp into separate int variables. 
		//Verify that the base price is the same as the price shown at Step 10.  
		//Verify that msrp equals to base price + dp&h fee;
		
		Thread.sleep(5000);
		
		int deliveryProcessingHanglingInt = 1025;
		int expectedPriceMSRPInt = basePriceInt + deliveryProcessingHanglingInt;
		String expectedPriceMSRP = "" + String.format("%,d", expectedPriceMSRPInt);
		
		
		driver.findElement(By.id("total-price")).click();
		
		WebElement actualPrice = driver.findElement(By.xpath("//ul[@id='price-breakup-list']/li[1]/div/span"));
		
		WebElement actualMSRP = driver.findElement(By.id("total-amount"));
		
		
		
		Utility.assertEquals(basePrice, actualPrice.getText());
		
		Utility.assertEquals(expectedPriceMSRP, actualMSRP.getText());
		
		
		
		//12. Close the menu and click on Ultrasonic Blue Mica color.
		
		driver.findElement(By.xpath("//a[@class='img-icon-x']")).click();
		
		driver.findElement(By.xpath("//img[@src='/config/pub/static/images/4f6a4fd50607a5684488cf2dc8df3ae65782f918_2015-Lexus-RC-Exterior-swatch-ultrasonic-blue-mica-2.0-60x60.png']")).click();
		
		
		//13. Click on the price menu on top again and retrieve the price for color and store into int variable. 
		//Retrieve msrp one more time and verify that msrp  now equals to base price + dp&h fee + color
		
		
		int colorPriceInt = 595;
		int newTotal = basePriceInt + colorPriceInt + deliveryProcessingHanglingInt;
		String newExpectedMsrp = "" + String.format("%,d", newTotal);
		
		
		Thread.sleep(5000);
		driver.findElement(By.id("total-price")).click();
		
		
		WebElement newActualMSRP = driver.findElement(By.id("total-amount"));
				
		
		Utility.assertEquals(newExpectedMsrp, newActualMSRP.getText());
		
		
		
		
		//14. Close the price menu and click on Next:Interior button
		
		driver.findElement(By.xpath("//a[@class='img-icon-x']")).click();
		
		driver.findElement(By.xpath("//a[.='Next: Interior']")).click();
		
		
		//15. Choose “Rioja Red leather with Naguri Aluminum trim” from the options .Click on Next:packages 
		
		driver.findElement(By.xpath("//img[@src='/config/pub/static/images/0790f539d40719d5b38cf8e052aa2f28676395979f57172c57179a217ffcd2d0_Lexus-Model-interior-swatch-60x60-rioja-red-leather-with-naguri-aluminum-trim-LEX-SWT-MY16-0077.png']")).click();
		
		driver.findElement(By.xpath("//a[.='Next: Packages']")).click();
		
		
		//16. In the next menu, click on add button for Mark Levinson sound system.
		
		driver.findElement(By.xpath("//ul[@id='individualSelectionView']//li[1]//div//div[1]//a")).click();
		
	

		
		//17. Click on price menu again and retrieve and store the price for Sound system into int variable. 
		//Retrieve msrp once again and verify that msrp now equals to to base price + dp&h fee + color+sound system.
	
		int soundSystem = 1380;
		int newTotal2 = soundSystem + newTotal;
		String newExpectedMsrp2 = "" + String.format("%,d", newTotal2);

		
		WebElement newActualMSRP2 = driver.findElement(By.id("total-price"));
		
		
		Utility.assertEquals(newExpectedMsrp2, newActualMSRP2.getText());
		
		
		
		//18. Click on Next:Accessories , on the next menu don’t add anything and click on Next:summary
		
		driver.findElement(By.xpath("//a[.='Next: Accessories']")).click();
		driver.findElement(By.xpath("//a[.='Next: Summary']")).click();
		
		
		//19. On the next page, retrieve msrp and verify that it is equal to the final msrp from step 17. 
		//Then click on Send to Dealer.
		
		String newExpectedMsrpFinal = "$" + newExpectedMsrp2 + "*";
		
		
		
		WebElement finalMSRP = driver.findElement(By.xpath("//h2[@class='title-price']"));
		
		Utility.assertEquals(newExpectedMsrpFinal,finalMSRP.getText());
		
		
		driver.findElement(By.xpath("//a[.='Send to Dealer']")).click();
		
		
		
		//20. Next, first verify that the page contains “Send us Your Dream Car” text. 
		//Then enter the below information to the form fields, choose Pohanka as preferred dealer and click on submit.
		

		Set<String> allHandles2 = driver.getWindowHandles();
		
		for (String handle : allHandles2) {
			
			if(!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
			}
			
		}
		
		driver.findElement(By.id("first-name")).sendKeys("John");
		driver.findElement(By.id("last-name")).sendKeys("Doe");
		driver.findElement(By.id("email")).sendKeys("anymail@gmail.com");
		driver.findElement(By.id("phone")).sendKeys("3127250272");
	
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement radio1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("64504")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", radio1);
		
		driver.findElement(By.xpath("//button[@class='btn-rev']")).click();
		
		
		
		
		//21. In the last page, verify that the page contains “We'll Be In Touch Shortly” text.
		
		String expectedText = "WE'LL BE IN TOUCH SHORTLY";
		WebElement actualText = driver.findElement(By.xpath("//div[@class='list-title-sub']"));
		
		Utility.assertEquals(expectedText, actualText.getText());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
