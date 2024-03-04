package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class BaseClass {
	public static WebDriver driver;
    public static Logger logger;
    Properties p;
	@BeforeTest
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException  {
	
	FileInputStream	file = new FileInputStream(".//src/test/resources/config.properties");
	Properties p = new Properties();
	p.load(file);
	
	logger= LogManager.getLogger(this.getClass());
	
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		
		
		
		DesiredCapabilities capabilities=new DesiredCapabilities();
		
		//os
		if(os.equalsIgnoreCase("windows"))
		{
			capabilities.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("mac"))
		{
			capabilities.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("No matching os..");
			return;
		}
		
		//browser
		switch(br.toLowerCase())
		{
		case "chrome" : capabilities.setBrowserName("chrome"); break;
		case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
		default: System.out.println("No matching browser.."); return;
		}
		
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		
	    }
	else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	{
			//launching browser based on condition - locally
		if(br.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver(); 
		}
		else if(br.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver(); 
		}
		else {
			System.out.println("Its Not Vaild Browser");
		}

	}
	


	
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	driver.get(p.getProperty("appURL"));
	driver.manage().window().maximize();
}

@AfterTest
public void close() {
	driver.quit();
}

public String captureScreen(String name) 
{
	String timeStamp = new SimpleDateFormat("yyyy-MM-dd.hh.mm.ss").format(new Date());
	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	String targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\"+ name + "_" + timeStamp + ".png";
	File targetFile=new File(targetFilePath);
	sourceFile.renameTo(targetFile);
	return targetFilePath;
}

}
