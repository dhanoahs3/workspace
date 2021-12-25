package com.retail.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	public  XSSFWorkbook workbook = null;
	public  XSSFSheet sheet = null;
	public  XSSFRow row   =null;
	public  XSSFCell cell = null;
	
	public ReadExcel(String path) {

		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
public  int getTotalRowCount(XSSFWorkbook workbook ,String sheetName){
		
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if(sheet == null){
			System.out.println("No sheet found with the name "+sheetName);
			return -1;
		}
		
		int rows = sheet.getLastRowNum()+1;
		System.out.println("The total rows are "+(rows));
		 return rows;
		
	}
	
	public  String getCellData(XSSFWorkbook workbook ,String sheetName,String colName,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
			XSSFSheet sheet = workbook.getSheet(sheetName);
			if(sheet == null){
				System.out.println("No sheet found with the name "+sheetName);
				return " ";
			}
			
			int col_Num = -1;

		Row row=sheet.getRow(1);
		if(row==null)
			return ""; 
		
		System.out.println("The total cols are for the row "+rowNum + " are  "+row.getLastCellNum());
		
		for(int i=0;i<row.getLastCellNum();i++){
			Cell cell = row.getCell(i);
			if(cell.getCellType() == CellType.STRING){
				  String value =  cell.getStringCellValue().trim();
				  if(value.equals(colName.trim())){
						System.out.println("Value has matched string "+ row.getCell(i).getStringCellValue().trim());
						col_Num=i;
						break;}
			}
			  
			  else if(cell.getCellType() == CellType.NUMERIC||cell.getCellType()==CellType.FORMULA){
				  String value =   String.valueOf(cell.getNumericCellValue());
				  if(value.equals(colName.trim())){
						System.out.println("Value has matched number "+ row.getCell(i).getStringCellValue().trim());
						col_Num=i;
						break;}
			}
			  
			  else if (cell.getCellType() == CellType.BLANK){
				  String value =  "";
				  if(value.equals(colName.trim())){
						System.out.println("Value has matched blank space "+ row.getCell(i).getStringCellValue().trim());
						col_Num=i;
						break;}
			  }
			
	}
		
		if(col_Num==-1)
			return "";
		
		row = sheet.getRow(rowNum);
		if(row==null){
			 System.out.println("Row is null");
				return "";
		}
		 Cell cell=row.getCell(col_Num);
		 if(cell==null)
		 {       
			 System.out.println("Cell is null");
				return "";
		 }		 
		 
		  if(cell.getCellType() == CellType.STRING){
			  System.out.println("Finally here .Value found is String");
			  return cell.getStringCellValue();
		}
		  
		  else if(cell.getCellType() == CellType.NUMERIC||cell.getCellType()==CellType.FORMULA){
			  System.out.println("Finally here .Value found is Numeric");
              return String.valueOf(cell.getNumericCellValue());
		}
		  
		  else if (cell.getCellType() == CellType.BLANK){
			  System.out.println("Finally here .Value found is blank");

			  return "";
		  }
		 
	}
	catch(Exception e){
	 e.printStackTrace();
	}

	   return " ";   
	}
	
	
	public  String getCellData(String sheetName,int colNum,int rowNum){
		try{
			
			if(colNum==-1)
				return "";
			
              if(rowNum <=0)
				return "";
		
			XSSFSheet sheet = workbook.getSheet(sheetName);
			if(sheet == null){
				return "";
			}
        Row row=sheet.getRow(rowNum-1);
		if(row==null)
			return ""; 
		 Cell cell=row.getCell(colNum-1);
		 if(cell==null)
		  return "";
		 
		 
		  if(cell.getCellType() == CellType.STRING){
			  return cell.getStringCellValue();
		}
		  
		  else if(cell.getCellType() == CellType.NUMERIC||cell.getCellType()==CellType.FORMULA){
			  return String.valueOf(cell.getNumericCellValue());
		}
		  
		  else if (cell.getCellType() == CellType.BLANK){
			  return "";
		  }
		 
	}
	catch(Exception e){
	 e.printStackTrace();
	}
	   return "";  
		
  }
	
public  int getCellRowNum(String sheetName,String colName,String cellValue){
	
	
	try {
	        for(int i=2;i<=getRowCount(sheetName);i++){
	        	System.out.println("Iterating over row no  "+i);
	        	String cellData = getCellData(workbook,sheetName,colName , i);
	        	System.out.println("Cell data we found is "+cellData+ " and cell value is "+cellValue);
	    	if(cellData.equalsIgnoreCase(cellValue)){
	    		return i;
	   
	    }
	  }       
	}
	catch (Exception e) {
		e.printStackTrace();
		return -1;
	}
	return -1;
}

public  int getRowCount(String sheetName){
	
	
	try {
		int index = workbook.getSheetIndex(sheetName);
    	if(index==-1)
		return 0;
	else{
	     XSSFSheet sheet = workbook.getSheetAt(index);
	     int number=sheet.getLastRowNum()+1;
	    return number;
	    }
    } 	
	 catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	
}
		
		
}