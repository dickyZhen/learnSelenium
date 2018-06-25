package demo;

import java.util.concurrent.TimeUnit;
import utils.UTIL;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class demo1 {

	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.baidu.com/");
		WebElement kw = driver.findElement(By.id("kw"));
		WebElement su = driver.findElement(By.id("su"));
		kw.sendKeys("谷歌");
		su.click();
		UTIL.CaptureElementScreenshot(su);
//		while (!UTIL.ElementExist(driver, By.id("ent_sug"))) {}
		UTIL.CaptureScreenshot(driver);
		System.out.println("WindowHandle:"+driver.getWindowHandle());
		WebElement a1 = driver.findElement(ByXPath.xpath("//*[@id=\"1\"]/h3/a"));
		UTIL.CaptureElementScreenshot(a1);
		a1.click();
		UTIL.SwitchHandle(driver);
		WebElement lst_ib = driver.findElement(By.id("lst-ib"));
		WebElement btnk = driver.findElement(By.name("btnK"));
		UTIL.CaptureElementScreenshot(btnk);
		System.out.println("WindowHandle:"+driver.getWindowHandle());
		lst_ib.sendKeys("百度");
		lst_ib.submit();
		UTIL.CaptureScreenshot(driver);
		WebElement a2 = driver.findElement(ByXPath.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div/h3/a"));
		a2.click();
		System.out.println("CurrentUrl:"+driver.getCurrentUrl());
		System.out.println("Title:"+driver.getTitle());
		System.out.println("WindowHandleSize:"+driver.getWindowHandles().size());
		System.out.println("WindowHandle:"+driver.getWindowHandle());
		
		Thread.sleep(5000);
		driver.quit();
	}

}
