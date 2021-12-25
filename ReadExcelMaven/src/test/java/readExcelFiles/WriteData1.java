package readExcelFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteData1 {

	public static void main(String[] args) throws IOException {
		
		//System.out.println("The result is " + setCellData("Data","Result",3,"abc"));
	//	System.out.println("The result is " + setCellData("Data","Result",3,"abc","dfdgdg"));
	//	addSheet("SheetN");
		
	//	System.out.println("The result to remove sheet is "+removeSheet("Sheet1"));
	//	System.out.println("The result to add column is "+addColumn(1,"Data","New column"));
		
		//System.out.println("The result to remove column is "+removeColumn("Data",6));
		
		System.out.println("The total no of columns are "+getColumnCount("Data"));




		
	}
		// TODO Auto-generated method stub
	// returns true if data is set successfully else false
		public static boolean setCellData(String sheetName,String colName,int rowNum, String data){
			int colNum=-1;
			try{
				FileInputStream file = new FileInputStream(new File("C://DCIM//Data.xlsx"));
		        XSSFWorkbook workbook = new XSSFWorkbook(file); 
                if(rowNum<=0)
				return false;
                
                
                XSSFSheet sheet = workbook.getSheet(sheetName);
    			if(sheet == null){
    				System.out.println("No sheet found with the name "+sheetName);
    				return false;
    			}
    			
    			Row row=sheet.getRow(1);
    			for(int i=0;i<row.getLastCellNum();i++){
    				System.out.println(row.getCell(i).getStringCellValue().trim());
    				if(row.getCell(i).getStringCellValue().trim().equals(colName)){
    					colNum=i;
    					break;
    				}
    			}
    			if(colNum==-1)
    				return false;
    			
    			System.out.println("The column where data is found is "+colNum);
    			
    			file.close();
    			
    		     row = sheet.getRow(rowNum);
    		     if(row==null){
    		    	 System.out.println("Row not present");
    		    	 row = sheet.createRow(rowNum);
    		     }
     			Cell cell = row.getCell(colNum);

    			 if(cell==null){
    		    	 System.out.println("Cell not present");
    		    	 cell = row.createCell(colNum);
    		     }
    			 
    		    CellStyle cs = workbook.createCellStyle();
    		    cs.setWrapText(true);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Fastasssssssssssssssssssssssssssssssssss2");
    			FileOutputStream file1 = new FileOutputStream(new File("C://DCIM//Data.xlsx"));
    			workbook.write(file1);
 	            file1.close();
    		}	
			catch(Exception e){
				e.printStackTrace();
			}
			
			return true;
	 } 
		
		// returns true if data is set successfully else false
		public static boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){
			try{
				FileInputStream file = new FileInputStream(new File("C://DCIM//Data.xlsx"));
		        XSSFWorkbook workbook = new XSSFWorkbook(file); 
                if(rowNum<=0)
				return false;
                
                
                XSSFSheet sheet = workbook.getSheet(sheetName);
    			if(sheet == null){
    				System.out.println("No sheet found with the name "+sheetName);
    				return false;
    			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return true;
			
		}		
		
		
		// returns true if sheet is created successfully else false
		public static boolean addSheet(String  sheetname){		
			
			try {
				FileInputStream fileIn = new FileInputStream(new File("C://DCIM//Data.xlsx"));
		        XSSFWorkbook workbook = new XSSFWorkbook(fileIn); 
				 workbook.createSheet(sheetname);	
				 FileOutputStream fileOut = new FileOutputStream(new File("C://DCIM//Data.xlsx"));
				 workbook.write(fileOut);
			     fileOut.close();		    
			} catch (Exception e) {			
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		public static boolean removeSheet(String sheetName) throws IOException{	
			FileInputStream fileIn = new FileInputStream(new File("C://DCIM//Data.xlsx"));
	        XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1){
				System.out.println("Sheet named " +sheetName+" does not exist");
				return false;
				}
			
			try {
				workbook.removeSheetAt(index);
				FileOutputStream fileOut = new FileOutputStream(new File("C://DCIM//Data.xlsx"));
				workbook.write(fileOut);
			    fileOut.close();		    
			} catch (Exception e) {			
				e.printStackTrace();
				return false;
			}
			System.out.println("Sheet named "+sheetName+ " deleted");
			return true;
		}
		
		
		public static boolean addColumn(int rowNum,String sheetName,String colName){
			//System.out.println("**************addColumn*********************");
			
			try{	
				
				FileInputStream fileIn = new FileInputStream(new File("C://DCIM//Data.xlsx"));
		        XSSFWorkbook workbook = new XSSFWorkbook(fileIn);

				XSSFSheet sheet = workbook.getSheet(sheetName);
				if(sheet == null){
					System.out.println("No sheet found with the name "+sheetName);
					return false;
				}
				
				XSSFCellStyle style = workbook.createCellStyle();
				style.setWrapText(true);
				Row row = sheet.getRow(rowNum);
				if (row == null){
					System.out.println("no row was persent ,so creating one");
					row = sheet.createRow(0); }
		       
				else {System.out.println("Row was persent ,so no need to create one");}
				
				if(row.getLastCellNum()==-1){
					System.out.println("The row had no cell so creating one");
				   Cell cell  =  row.createCell(0);}
				
				else {
					System.out.println("The row had had these many cells "+row.getLastCellNum()+ ". Adding one after that");

					Cell cell  = row.createCell(row.getLastCellNum());
			        cell.setCellValue(colName);
			        cell.setCellStyle(style);
			       }
		        FileOutputStream fileOut = new FileOutputStream(new File("C://DCIM//Data.xlsx"));
				workbook.write(fileOut);
			    fileOut.close();    

				}catch(Exception e){
					e.printStackTrace();
					return false;
				}
				
				return true;
		}
		
		
		// removes a column and all the contents
		public static boolean removeColumn(String sheetName, int colNum) {
			try{
			if(!isSheetPresent(sheetName))
				return false;
			FileInputStream fileIn = new FileInputStream(new File("C://DCIM//Data.xlsx"));
		    XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
		    XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCreationHelper createHelper = workbook.getCreationHelper();
			
			int totalRows = getRowCount(sheetName);
	    	 System.out.println("The total no of rows are "+totalRows);

			
		     for(int i =0;i<totalRows;i++){
				Row row=sheet.getRow(i);	
				if(row!=null){
					Cell cell=row.getCell(colNum);
					if(cell!=null){
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
				System.out.println("Removed from row no "+i);
			}
			FileOutputStream fileOut = new FileOutputStream(new File("C://DCIM//Data.xlsx"));
			workbook.write(fileOut);
		    fileOut.close();
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
			
		}
	  // find whether sheets exists	
		
		public static Boolean isSheetPresent(String sheetName){
			FileInputStream fileIn;
			XSSFWorkbook workbook;
			try {
				fileIn = new FileInputStream(new File("C://DCIM//Data.xlsx"));
			    workbook = new XSSFWorkbook(fileIn);
			    XSSFSheet sheet = workbook.getSheet(sheetName);
				if(sheet == null){
					System.out.println("No sheet found with the name "+sheetName);
					return false;
				}
				fileIn.close();
				workbook.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
        }
		
		// returns the row count in a sheet
		public static int getRowCount(String sheetName){
			
			FileInputStream fileIn;
		    XSSFWorkbook workbook;
			try {
				fileIn = new FileInputStream(new File("C://DCIM//Data.xlsx"));
			    workbook = new XSSFWorkbook(fileIn);
		        int index = workbook.getSheetIndex(sheetName);
		    	if(index==-1)
				return 0;
			else{
			     XSSFSheet sheet = workbook.getSheetAt(index);
			     int number=sheet.getLastRowNum()+1;
			    return number;
			    }
		    } 	
			 catch (IOException e) {
					e.printStackTrace();
					return 0;
				}
			
		}
	
		// returns number of columns in a sheet	
		public static int getColumnCount(String sheetName){
			// check if sheet exists
			if(!isSheetPresent(sheetName))
			 return -1;
			System.out.println("Trying to figure out");
			try{
			FileInputStream fileIn = new FileInputStream(new File("C://DCIM//Data.xlsx"));
		    XSSFWorkbook workbook;
			workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(0);
			if(row==null)
				return -1;
			return row.getLastCellNum();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}		
		
		
}			
			
			
