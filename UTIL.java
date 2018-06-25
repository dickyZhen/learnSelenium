package utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

public class UTIL {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");  //转换时间格式  
	
	public static String GetTime(){
		return dateFormat.format(Calendar.getInstance().getTime());  //获取当前时间  
	}

	public static void SwitchHandle(WebDriver driver){
		String currentWindow=driver.getWindowHandle();//获取当前窗口的句柄
		Set<String> handles=driver.getWindowHandles();//获取所有窗口的句柄	
		Iterator<String> it=handles.iterator();	
		while (it.hasNext()){
			String handle=it.next();
			if(!handle.equals(currentWindow)){       
				driver=driver.switchTo().window(handle);   //切换到新的句柄所指向的窗口
			    break;
			}
		}
	}
	
	/**
     * 截屏操作 
     * 图片已当前时间命名 
	 * @param driver
	 */
	public static void CaptureScreenshot(WebDriver driver){
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  //执行屏幕截取  
		try {
			//利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件;"屏幕截图"即时保存截图的文件夹  
			FileUtils.copyFile(srcFile, new File("屏幕截图", GetTime() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/** 
     * 部分截图（元素截图） 
	 * @throws IOException 
     */  
	public static void CaptureElementScreenshot(WebElement element){
        //创建全屏截图  
        WrapsDriver wrapsDriver = (WrapsDriver)element;  
        File screen = ((TakesScreenshot)wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);  
        BufferedImage image;
		try {
			image = ImageIO.read(screen);
			//获取元素的高度、宽度  
	        int width = element.getSize().getWidth();  
	        int height = element.getSize().getHeight();  
	          
	        //创建一个矩形使用上面的高度，和宽度  
	        Rectangle rect = new Rectangle(width, height);  
	        //元素坐标  
	        Point p = element.getLocation();  
	        BufferedImage img = image.getSubimage(p.getX(), p.getY(), rect.width, rect.height);  
	        ImageIO.write(img, "png", screen);
	        FileUtils.copyFile(screen, new File("屏幕截图", System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
    public static boolean ElementExist(WebDriver driver,By locator)
    {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    
    public static String getCellValue(Cell cell) {
        String result = null;
        switch (cell.getCellTypeEnum()) {
        case BLANK:
            result = "";
            break;
        case NUMERIC: 
            result = String.valueOf(cell.getNumericCellValue());
//            result = String.valueOf(cell.getStringCellValue());
//            System.out.println(result);
            break;
        case FORMULA:
            result = String.valueOf(cell.getStringCellValue());
            break;
        case STRING:
            result = cell.getStringCellValue();
            break; 
        default:
            break;
        }
        return result;
    }
}
