 package pom;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExcelUtils;

public class Wellness {
		
		public WebDriver driver;
		public static String[] data;
		
		//Constructor
		public Wellness (WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		//WebElement Locators
		@FindBy(xpath="//span[text()='For Corporates']")
		WebElement Corporate_btn;
		@FindBy(xpath="//*[contains(text(),'Health & Wellness Plans')]")
		WebElement wellness_btn;
		@FindBy(xpath="//*[@id=\'name\']")
		WebElement Name;
		@FindBy(xpath="//*[@id=\"organizationName\"]")
		WebElement org;
		@FindBy(xpath="//*[@id=\"contactNumber\"]")
		WebElement Number;
		@FindBy(xpath="//*[@id=\"officialEmailId\"]")
		WebElement Mail;
		@FindBy(xpath="//*[@id='organizationSize']")
		WebElement Size;
		@FindBy(xpath="//*[@id='interestedIn']")
		WebElement option;
		@FindBy(xpath="//*[@id=\"header\"]/div[2]/div/form/button")
		WebElement Demo;
		@FindBy(xpath="//div[text()='THANK YOU']")
		WebElement Thank;
		
		// Action methods
		
		public String[] getData() throws IOException {

				   data=ExcelUtils.readExcelData("data");

				   return data;

			  }
		
		
		public void ClickCorporate() throws InterruptedException
		{		
			Corporate_btn.click();
	     	Thread.sleep(2000);
	     	Actions act=new Actions(driver);
	     	act.clickAndHold(Corporate_btn);
		}
		
		public void ClickWellness()
		{
			wellness_btn.click();
	     	//Thread.sleep(2000);
	     	Actions act=new Actions(driver);
	     	act.clickAndHold(wellness_btn);
		}
		
		public void Scroll()
		{
			 WebElement element=Name;
		 	 JavascriptExecutor js=(JavascriptExecutor)driver;
		 	 js.executeScript("arguments[0].scrollIntoView(true);",element);
		}
		public void InValidDetails()
		{
			
	     	Name.sendKeys(data[2]);
	     	org.sendKeys(data[3]);
	     	Number.sendKeys("9885656536");
	     	Mail.sendKeys(data[4]);
	     	
	     	Select orgsize =new Select(Size);
			orgsize.selectByIndex(2);
			
			Select option1 =new Select(option);
			option1.selectByIndex(1);
		}
		public void demoButton() {
			if(Demo.isEnabled()) {
		     		System.out.println("schedule a demo button is enabled ");
		     	}
		     	else {
		     		System.out.println("schedule a demo button is disabled ");
		     	}
			}
		public void ValidDetails()
		{
			Mail.clear();
			Mail.sendKeys(data[5]);
		}
		public void sdemoButton() 
		{
			if(Demo.isEnabled()) {
	     		System.out.println("schedule a demo button is enabled ");
	     	}
	     	else {
	     		System.out.println("schedule a demo button is disabled ");
	     	}
		}
		
			public void clickdemo()
			{
			  Demo.click();
			}
			
			public void thankyou() 
			{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement Thank = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='THANK YOU']")));
	     	if(Thank.isDisplayed()) {
	     		System.out.println("Thank you  is displayed");
	     	}
	     	else {
	     		System.out.println("Thank you  is not displayed ");
	     	}
		}
		
	}