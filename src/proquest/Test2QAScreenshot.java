package proquest;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test2QAScreenshot {
	
	public static void takeSnapShot(WebDriver webdriver,String fileName) throws Exception{

		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);

		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination
		File DestFile=new File(fileName);

		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
    	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String imagePath = args[0];
		System.setProperty("webdriver.gecko.driver", "C:/Users/vallagenah/Desktop/Garbage/geckodriver-v0.26.0-win64/geckodriver.exe");
		
		try {
			
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			
			driver.get("https://www.google.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				//Search url: https://www.google.co.in/#q=ProQuest
			WebElement element = driver.findElement(By.name("q"));	//finding the web element using name locator
			element.sendKeys("ProQuest\n"); 						// send also a "\n"
			element.submit();

			//Get search result links
			List<WebElement> results = driver.findElements(By.xpath("//cite[@class='iUh30']"));

			//get 1st result's URL
			String proquest_url=results.get(0).getText();        	        
			driver.get("https://"+proquest_url+"/");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//Click on search icon
			driver.findElement(By.xpath("/html/body/div[1]/nav[2]/div/div[2]/ul[1]/li[8]/a/i")).click();

			//type qa to text box 
			driver.findElement(By.xpath("/html/body/div[1]/nav[2]/div/div[2]/ul[1]/li[8]/ul/li/form/div/span[1]/input[2]")).sendKeys("QA");
			driver.findElement(By.xpath("/html/body/div[1]/nav[2]/div/div[2]/ul[1]/li[8]/ul/li/form/div/span[2]/button/i")).click();

			//Call take screenshot function
		    	takeSnapShot(driver, imagePath) ;
			System.out.println("Snapshot saved in path: "+imagePath);
				        
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
