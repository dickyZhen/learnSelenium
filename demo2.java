package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import utils.UTIL;

public class demo2 {
	
	private Map<Integer, String> key = new HashMap<>();
	private Map<Integer, String> value = new HashMap<>();
	private static String FileURL = "D:\\Users\\ASNPHL8\\Desktop\\test.xlsx";
	public static void main(String[] args) {

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
			init(sheet1, sheet2);
			
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(Sheet sheet1,Sheet sheet2){
		Row row;
		for(int i = 1;i<sheet2.getRow(0).getLastCellNum();i++){
			row = sheet2.getRow(i);
			key.put(i, UTIL.getCellValue(row.getCell(0)));
		}
	}
}
