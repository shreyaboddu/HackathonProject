package pom;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ExcelUtils;

public class surgeries {
	public WebDriver driver;
	
	
	public surgeries(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	static String file=System.getProperty("user.dir")+"/testdata/Hospitaldata.xlsx";
	
	//WebElement Locators
	@FindBy(xpath="//div[contains(text(),'Surgeries') and @class='product-tab__title']")
	WebElement surgery;
	@FindBy(xpath="//*[@class=\"flex flex-col items-center text-center\"]")
	List<WebElement> list;
	@FindBy(xpath="//span[text()='For Corporates']")
	WebElement Corporate_btn1;
	
	//Action methods
	public void ClickSurgeries()
	{
		surgery.click();	
		System.out.println("surgeries Opened");			
	}
	public void ScrollIntoView()
	{
		WebElement scroll=driver.findElement(By.xpath("//*[@id=\"surgeries\"]/div/div[1]/div/h1"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",scroll);		
	}
	
	public void noOfSurgeries()
	{
		System.out.println("Number of surgeries are:"+list.size());
	}
	
	public void listOfSurgeries() throws IOException 
	{
		
		System.out.println("----List of surgeries----");
		
		 int row=0;
		for(WebElement all:list) {
			String surgery=all.getText();
			ExcelUtils.setCellData(file,"Surgerylist",row , 0, surgery);
			System.out.println(surgery);
			row++;
		}
		
	}
	public void ScrollUp()
	{
		//WebElement scroll=driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/div[1]/div/div[3]/div/div[1]/span/a/i"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",Corporate_btn1);		
	}
	
	
}
