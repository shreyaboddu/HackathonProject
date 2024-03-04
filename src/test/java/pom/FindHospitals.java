package pom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExcelUtils;


public class FindHospitals {
public WebDriver driver;
public static String[] data;
	
	//Constructor
	public  FindHospitals(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	static String file=System.getProperty("user.dir")+"/testdata/Hospitaldata.xlsx";
	
	//WebElement Locators
	@FindBy(xpath="//input[@data-qa-id='omni-searchbox-locality']")
	WebElement location;
	@FindBy(xpath="//div[@data-qa-id='omni-suggestion-main' and text()='Bangalore']")
	WebElement Loc_option;
	@FindBy(xpath="//input[@data-qa-id='omni-searchbox-keyword']")
	WebElement Search_bar;
	@FindBy(xpath="//div[@data-qa-id='omni-suggestion-main'and text()='Cardiologist']")
	WebElement Search_option;
	@FindBy(xpath="//div[@data-qa-id='doctor_review_count_section']")
	WebElement Stories;
	@FindBy(xpath="//span[text()='10+ Patient Stories']")
	WebElement Pat_stories;
	@FindBy(xpath="//div[@data-qa-id='years_of_experience_section']")
	WebElement Exp;
	@FindBy(xpath="//span[text()='5+ Years of experience']")
	WebElement Exp_option;
	@FindBy(xpath="//span[@data-qa-id='all_filters']")
	WebElement All_filters;
	@FindBy(xpath="//span[text()='₹0-₹500']")
	WebElement Fees;
	@FindBy(xpath="//span[@data-qa-id='sort_by_selected']")
	WebElement Sort_by;
	@FindBy(xpath="//li[@aria-label='Number of patient stories - High to low']")
	WebElement Sort_option;
	@FindBy(xpath="//span[text()='Available in next 4 hours']")
	WebElement Avalability;
	@FindBy(xpath="//h2[@data-qa-id='doctor_name']")
	List<WebElement> Search_doctors;
	@FindBy(xpath="//div[@class='info-section']")
	List<WebElement>  doctorProfile;

	
	
	// Action methods
	public String[] getData() throws IOException {

		   data=ExcelUtils.readExcelData("data");

		   return data;

	  }
	public void Location() throws InterruptedException
	{		
		 location.clear();
		 Thread.sleep(2000);
		 location.sendKeys(data[0]);
		 Thread.sleep(2000);
		 Loc_option.click();
		 
	}
	
	public void InputSearchBar() throws InterruptedException
	{
		Search_bar.clear();
		Search_bar.sendKeys(data[1]);
		Search_option.click();
	}
	
	public void options()
	{
		Stories.click();
		Pat_stories.click();
		Exp.click();
		Exp_option.click();
		}
	public void filter_fee() {
		All_filters.click();
		Fees.click();
	}
	
	public void SortBy() {
		Sort_by.click();
		Sort_option.click();
	}
	public void filter_avalability() throws InterruptedException {
		All_filters.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement Thank = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Available in next 4 hours']")));
		Thank.click();
	}
	public void getDoctorInfo() throws IOException {
	
		
		for(int i=0; i<=5 ; i++) {	
			
			String doctor=doctorProfile.get(i).getText();	
			ExcelUtils.setCellData(file, "Doctorlist", i, 0, doctor);
			System.out.println("\n"+doctor);
			
			
			System.out.println("**************************************************");
			   
			}

		}
	
	}


