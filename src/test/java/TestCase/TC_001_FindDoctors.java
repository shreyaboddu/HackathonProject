package TestCase;

import java.io.IOException;

import org.testng.annotations.Test;

import pom.FindHospitals;
import testbase.BaseClass;

public class TC_001_FindDoctors extends BaseClass{
	
	@Test(priority=1, groups= {"smoke","regression"} )
	public void Find_Doctors() throws InterruptedException, IOException 
	{
		logger.info("****starting TestCase001******");
		FindHospitals doctors = new FindHospitals(driver);
		doctors.getData();
		doctors.Location();
		Thread.sleep(2000);
		logger.info("Location clicked");
		
		doctors.InputSearchBar();
		logger.info("Cardiologist clicked");
		
		doctors.options();
		logger.info("patient stories and Experience selected");
		
		doctors.filter_fee();
		logger.info("fee option is clicked");
		
		
		doctors.SortBy();
		logger.info("---Sort option is selected---");
		Thread.sleep(5000);
		
		doctors.filter_avalability();
		logger.info("Avalability option is clicked");
		Thread.sleep(10000);
		
		doctors.getDoctorInfo();
		logger.info("Fetching and displaying top 5 doctors");
		captureScreen("doctorProfile");
	}

}
