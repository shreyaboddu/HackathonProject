package TestCase;

import java.io.IOException;

import org.testng.annotations.Test;

import pom.Wellness;
import testbase.BaseClass;

public class TC_003_Wellness extends BaseClass{
	
	@Test(priority=3 , groups= {"smoke","regression"})
	public void CoporatesAndWellness() throws InterruptedException, IOException
	{
		Wellness details = new Wellness(driver);
		details.getData();
		details.ClickCorporate();
		logger.info("---Corporates  is clicked---");
		
		details.ClickWellness();
		logger.info("Wellness is clicked");
		
		details.Scroll();
		logger.info("Scrolled");
		
		details.InValidDetails();
		captureScreen("InValidDetails");
		
		details.demoButton();
		logger.info("Demo is Disabled");
		captureScreen("demoButton");
		
		details.ValidDetails();
		captureScreen("ValidDetails");
		
		details.sdemoButton();
		logger.info("Demo is Enabled");
		
		details.clickdemo();
		details.thankyou();
		captureScreen("thankyou");

	}

}
