package TestCase;

import java.io.IOException;

import org.testng.annotations.Test;

import pom.surgeries;
import testbase.BaseClass;

public class TC_002_Surgeries extends BaseClass{
	
	@Test(priority=2 , groups= {"smoke","regression"})
	public void NoOfSurgeries() throws InterruptedException, IOException
	{
		surgeries list = new surgeries(driver); 
		
		list.ClickSurgeries();
		logger.info("---Click Surgeries---");
		Thread.sleep(2000);
		
		list.ScrollIntoView();
		logger.info("Scrolled");
		Thread.sleep(2000);
		
		list.noOfSurgeries();
		logger.info("Total no.of Surgeries");
		
		list.listOfSurgeries();
		captureScreen("listOfSurgeries");
		logger.info("List of Surgeries is dispalyed");
		
		list.ScrollUp();
		
	    
	}

}
