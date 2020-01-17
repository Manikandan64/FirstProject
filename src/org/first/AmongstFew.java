package org.first;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AmongstFew {
	
	static WebDriver driver;
	
	//Screenshot call method 
	public static void Screenshot(WebDriver driver, String filePath) throws IOException {
		
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(filePath));
		}
		// Wait Call method

	}
	
	public  static String Excel(int rowNo, int cellno) throws Throwable {
			String v = null;
			File loc = new File("C:\\Users\\Mani\\eclipse-workspace\\FirstProject\\TestData\\Testdata.xlsx");
			FileInputStream stream = new FileInputStream(loc);
			Workbook w = new XSSFWorkbook(stream);
			Sheet s = w.getSheet("Sheet1");
			Row r = s.getRow(rowNo);
			Cell c = r.getCell(cellno);
			v = c.getStringCellValue();
			return v;
		}

	public static void waits() {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	public static void main(String[] args) throws Throwable {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Mani\\eclipse-workspace\\Ticketech\\Driver\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("https://www.amongstfew.com/");
		driver.manage().window().maximize();
		Screenshot(driver, "E:\\Screenshot.img1.png");
		
		//Action Class to mouse over perform
		
		WebElement clothing = driver.findElement(By.xpath("(//a[@href='/collections/clothing'])[1]"));
			
		Actions acc = new Actions(driver);
		acc.moveToElement(clothing).perform();
		
		WebElement Tshirt = driver.findElement(By.xpath("(//a[text()='T-Shirts'])[1]"));
		Tshirt.click();
		Thread.sleep(5000);
		Screenshot(driver, "E:\\Screenshot.img2.png");
		
		driver.findElement(By.xpath("(//a[text()='amongst few x Chi Modu x Sole - Wu-Tang Forever T-Shirt (White)'])[1]")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("(//span[text()=' Please Select Size'])[1]")).click();
		driver.findElement(By.xpath("//span[text()='XL']")).click();
		driver.findElement(By.id("add")).click();
		Screenshot(driver, "E:\\Screenshot.img3.png");
		driver.findElement(By.id("checkout-button")).click();
		Screenshot(driver, "E:\\Screenshot.img4.png");
		
		//checkout contact Information
		//Passing the value by Excel Read option
		waits();
		driver.findElement(By.id("checkout_email")).sendKeys(Excel(0,0));
		driver.findElement(By.id("checkout_shipping_address_first_name")).sendKeys(Excel(1,0));
		driver.findElement(By.id("checkout_shipping_address_last_name")).sendKeys(Excel(2,0));
		driver.findElement(By.id("checkout_shipping_address_address1")).sendKeys(Excel(1,1));
		driver.findElement(By.id("checkout_shipping_address_address2")).sendKeys(Excel(2,2));
		driver.findElement(By.id("checkout_shipping_address_city")).sendKeys(Excel(1,1));
		WebElement country = driver.findElement(By.id("checkout_shipping_address_country"));
		Select s = new Select(country);
		s.selectByVisibleText("India");
		
		WebElement state= driver.findElement(By.id("checkout_shipping_address_province"));
		Select s1 = new Select(state);
		s1.selectByVisibleText("India");
	driver.findElement(By.id("checkout_shipping_address_zip")).sendKeys(Excel(0,3));
	driver.findElement(By.id("checkout_shipping_address_phone")).sendKeys(Excel(4,0));
	Screenshot(driver, "E:\\Screenshot.img5.png");
	driver.findElement(By.id("//span[text()='Continue to shipping']")).click();
	
	}
}
