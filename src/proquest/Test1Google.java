package proquest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test1Google {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Take file path as an argument
		String fileName = args[0];
	    
		System.setProperty("webdriver.gecko.driver", 
				"C:/Users/vallagenah/Desktop/Garbage/geckodriver-v0.26.0-win64/geckodriver.exe");		
		try {

			WebDriver driver = new FirefoxDriver();
			
			//System.out.println(driver.getTitle());
			driver.manage().window().maximize();
			
			driver.get("https://www.google.com/");
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        
			//search url:  https://www.google.co.in/#q=ProQuest
	        WebElement element = driver.findElement(By.name("q"));	//finding the web element using name locator
	        element.sendKeys("ProQuest\n"); 						// send also a "\n"
	        element.submit();
	        
	        //Get search result and iterate the list to get all the search titles from that page
	        
	        List<WebElement> results = driver.findElements(By.xpath("//span[@class='S3Uucc']"));
	      
	        System.out.println(results.size());
	        
	        PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
	        	        
	        for(int i=0; i<results.size(); i++){
	        	writer.printf("%s\r\n", results.get(i).getText());	        	
			}
	        
	        writer.close();
	        	                
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
