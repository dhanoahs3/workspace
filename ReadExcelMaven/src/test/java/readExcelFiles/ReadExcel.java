package readExcelFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public static void main(String[] args) 
    {
	
	 try
     {
         FileInputStream file = new FileInputStream(new File("C://DCIM//Data.xlsx"));

         //Create Workbook instance holding reference to .xlsx file
         XSSFWorkbook workbook = new XSSFWorkbook(file);

         //Get first/desired sheet from the workbook
         XSSFSheet sheet = workbook.getSheetAt(0);

         //Iterate through each rows one by one
         Iterator<Row> rowIterator = sheet.iterator();
         while (rowIterator.hasNext()) 
         {
             Row row = rowIterator.next();
             //For each row, iterate through all the columns
             Iterator<Cell> cellIterator = row.cellIterator();
              
             while (cellIterator.hasNext()) 
             {
                 Cell cell = cellIterator.next();


                 //Check the cell type and format accordingly
                 switch (cell.getCellType()) 
                   {
                     case NUMERIC:
                         System.out.print(cell.getNumericCellValue() + "\t");
                         break;
                     case STRING:
                         System.out.print(cell.getStringCellValue() + "\t");
                         break;
                     case BOOLEAN:
                         System.out.print(cell.getBooleanCellValue() + "\t");
                         break;
                     case BLANK:
                         System.out.print("\t");
                         break;
                 }
               }
             System.out.println("");
         }
         
         System.out.println("------------------------------------------------------------------------");
       
         Row row=sheet.getRow(1); //returns the logical row  
         Cell cell=row.getCell(3); //getting the cell representing the given column  
       
          System.out.println("The total no of rows are "+sheet.getLastRowNum());
          
          int noOfColumns = sheet.getRow(16).getPhysicalNumberOfCells();
          
          System.out.println("The total no Of Columns are "+noOfColumns);

          int noOfColumns1 = sheet.getRow(16).getLastCellNum();
          System.out.println("The last Column is  "+noOfColumns1);

             switch (cell.getCellType()) {
                 case BOOLEAN:
                     System.out.println(cell.getBooleanCellValue());
                     break;
                 case NUMERIC:
                     System.out.println(cell.getNumericCellValue());
                     break;
                 case STRING:
                     System.out.println(cell.getRichStringCellValue());
                     break;
                 case BLANK:
                     System.out.println(cell.getRichStringCellValue());
                     break;
     } 
             
           /*  System.out.println("------------------------------------------------------------------------");
             
             int sheetIndex = workbook.getSheetIndex("Sheet2");
             if(sheetIndex==-1){
            	 System.out.println("The sheetindx is"+sheetIndex);
             }
             else{
                sheet = workbook.getSheetAt(sheetIndex);
                int totalRows  = sheet.getLastRowNum();
                System.out.println("The total rows are "+(totalRows+1));
             }
             
             System.out.println("Calling method get cell data");
             String data  = getCellData(workbook,"Data","B5",10);
             System.out.println("The data in cell is ------------>"+data);
             data = getCellData1(workbook,"Data","B5",10);
             System.out.println("The data is "+data);*/
             
             int rows  = getTotalRowCount(workbook,"Sheet2");
              System.out.println("The rows out here are "+rows);
              
            //  String data  = getCellData(workbook,"Data","Col3",2);
                String data  = getCellData(workbook,"Data",5,2);

              
              System.out.println("The data is "+data);
              
              System.out.println("<-------------------------------------------------------------->");
              System.out.println("The data is present on row no "+getCellRowNum("Data","Col1","z"));


              file.close();
     }
	 
	 catch(Exception e){
		 e.printStackTrace();
	 }
    
 
     }
	
	public static int getTotalRowCount(XSSFWorkbook workbook ,String sheetName){
		
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if(sheet == null){
			System.out.println("No sheet found with the name "+sheetName);
			return -1;
		}
		
		int rows = sheet.getLastRowNum()+1;
		System.out.println("The total rows are "+(rows));
		 return rows;
		
	}
	
	public static String getCellData(XSSFWorkbook workbook ,String sheetName,String colName,int rowNum){
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
		
		System.out.println("The column on which we are looking for data is ->  "+col_Num);
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
		  System.out.println("Finally here .No value found");

	   return " ";   
	}
	
	
	public static String getCellData(XSSFWorkbook workbook,String sheetName,int colNum,int rowNum){
		try{
			
			if(colNum==-1)
				return "--1";
			
              if(rowNum <=0)
				return "--2";
		
			XSSFSheet sheet = workbook.getSheet(sheetName);
			if(sheet == null){
				System.out.println("No sheet found with the name "+sheetName);
				return "--3";
			}
			
        Row row=sheet.getRow(rowNum-1);
		if(row==null)
			return "--4"; 
	    row = sheet.getRow(rowNum);
		 Cell cell=row.getCell(colNum-1);
		 if(cell==null)
		  return "---5";
		 
		 
		  if(cell.getCellType() == CellType.STRING){
			  System.out.println("The cell type is String");
			  return cell.getStringCellValue();
		}
		  
		  else if(cell.getCellType() == CellType.NUMERIC||cell.getCellType()==CellType.FORMULA){
			  System.out.println("The cell type is Numric");
			  return String.valueOf(cell.getNumericCellValue());
		}
		  
		  else if (cell.getCellType() == CellType.BLANK){
			  return "Empty cell";
		  }
		 
	}
	catch(Exception e){
	 e.printStackTrace();
	}
	   return "---10";  
		
  }
	
public static int getCellRowNum(String sheetName,String colName,String cellValue){
	
	FileInputStream fileIn;
    XSSFWorkbook workbook;
	try {
		fileIn = new FileInputStream(new File("C://DCIM//Data.xlsx"));
	    workbook = new XSSFWorkbook(fileIn);
	        for(int i=2;i<=getRowCount(sheetName);i++){
	        	System.out.println("Iterating over row no  "+i);
	        	String cellData = getCellData(workbook,sheetName,colName , i);
	        	System.out.println("Cell data we found is "+cellData+ " and cell value is "+cellValue);
	    	if(cellData.equalsIgnoreCase(cellValue)){
	    		return i;
	    	}
	    }
	        
	}
	catch (IOException e) {
		e.printStackTrace();
		return -1;
	}
	return -1;
}

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
		
		
}