package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import utils.UTIL;

public class demo2 {
	
	private List<String> key = new ArrayList<>();
	private static String FileURL = "C:\\Users\\Administrator\\Downloads\\test.xlsx";
	public static void main(String[] args) {
		new demo2().run();
	}

	public void run(){
		try {
			Workbook workbook = WorkbookFactory.create(new FileInputStream(FileURL));
			Sheet sheet1 = null,sheet2 = null;
			if (null != workbook.getSheetAt(0)) {
				sheet1 = workbook.getSheetAt(0);
			}
			if (null != workbook.getSheetAt(1)) {
				sheet2 = workbook.getSheetAt(1);
			}
			init(sheet2);
			for (int i = 0; i < key.size(); i++) {
				Map<String, String> dataMap = getData(sheet2, i+1);
				readData(sheet1, sheet2, i, dataMap);
			}
			
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(Sheet sheet2){
		Row row = sheet2.getRow(0);
		for(int i = 1;i<row.getLastCellNum();i++){
			key.add(UTIL.getCellValue(row.getCell(i)).trim());
		}
	}
	
	public Map<String, String> getData(Sheet sheet2,int row_num){
		Map<String, String> data = new HashMap<>();
		Row row = sheet2.getRow(row_num);
		for (int j = 0; j < key.size(); j++) {
			data.put(key.get(j), UTIL.getCellValue(row.getCell(j+1)));
		}
		for (Map.Entry<String, String> entry : data.entrySet()) {  
			   String key = entry.getKey().toString();  
			   String value = entry.getValue().toString();  
			   System.out.println("key=" + key + " value=" + value);  
			  }  
		
		return data;
	}
	
	public void readData(Row row,int count, Map<String, String> data){
		
	}
}
