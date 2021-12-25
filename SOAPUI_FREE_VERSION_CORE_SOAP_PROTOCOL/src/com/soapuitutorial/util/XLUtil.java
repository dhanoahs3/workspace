package com.soapuitutorial.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.eviware.soapui.model.support.PropertiesMap;

public class XLUtil {
	
	public static Xls_Reader reportXls;
	public static String destFilePath;


	
	public static List<PropertiesMap> getTestData(String suiteName,String testCaseName,Xls_Reader xls){
		int rows = xls.getRowCount(suiteName);
		System.out.println("Total row numbers are "+rows);
		
		int testCaseRowNum = 1;
		for(testCaseRowNum=1;testCaseRowNum<rows;testCaseRowNum++){
			if(xls.getCellData(suiteName, 0,testCaseRowNum).equals(testCaseName))
				break;
			} 

		System.out.println("The test case " +testCaseName +  " starts from row number "+testCaseRowNum);
        int colRowNum = testCaseRowNum+1;
        int dataStartRowNum = testCaseRowNum+2;
        int totalCols = 0;
        while(!xls.getCellData(suiteName, totalCols, colRowNum).equals("")){
        	totalCols++;
        }
        System.out.println("The total cols in test  "+testCaseName+ " are "+ totalCols);
        
        int totalRows = 0;
        while(!xls.getCellData(suiteName,1,dataStartRowNum+totalRows).equals("")){
        	totalRows++;
        	}

        System.out.println("The total rows in test  "+testCaseName+ " are "+ totalRows);
        

        
        String data = null;
        String key = null;
        PropertiesMap map = null;
        
        List<PropertiesMap> dataList = new ArrayList<PropertiesMap>();
        
        for(int rNum = dataStartRowNum;rNum<(dataStartRowNum+totalRows);rNum++){
        	
        	map = new PropertiesMap();
        	
        	for(int cNum=0;cNum<totalCols;cNum++){
              System.out.print(xls.getCellData(suiteName, cNum, rNum)+"------------");
              key = xls.getCellData(suiteName, cNum, colRowNum);
              data = xls.getCellData(suiteName, cNum, rNum);
              map.put(key,data);
        	}
        	
        	dataList.add(map);
        	System.out.println();
        }
		return dataList;
      }
	
	
	public static void generateReport(String testSuiteName,String testCaseName, 	List<PropertiesMap> testDataSets){
		
		
		System.out.println(testSuiteName);
		System.out.println(reportXls);
		
		int rows  = reportXls.getRowCount(testSuiteName);
		int testCaseRowNum=1;
		for(testCaseRowNum=1;testCaseRowNum<=rows;testCaseRowNum++){
			if(reportXls.getCellData(testSuiteName, 0, testCaseRowNum).equals(testCaseName))
			break;
			
  	}
		System.out.println("Test Case "+testCaseName+" starts from row -> "+ testCaseRowNum);
		int colRownum=testCaseRowNum+1;
		int dataStartRowNum=testCaseRowNum+2;
		
		// total cols in the test
		int totalCols=0;
        String result  = null;
        
        for(int rNum = dataStartRowNum;rNum<dataStartRowNum+testDataSets.size();rNum++){
        	
        result  = (String) testDataSets.get(rNum-dataStartRowNum).get("Result");
        reportXls.setCellData(testSuiteName, rNum,0,result);
        
        }


		
	}
	
	
	public static void prepareResultSheet(String srcFilePath, String destFolderPath){
		InputStream inStream = null;
	   	OutputStream outStream = null;
	 
	    	try{
	    		Date d = new Date();
	    		String fileName = "Result-"+d.toString().replace(":", "_").replace(" ", "_")+".xlsx";
	    	    destFilePath = destFolderPath+fileName;
	    	    File afile =new File(srcFilePath);
	    	    File bfile =new File(destFilePath);
	    	    
	    	   // if(!bfile.exists())
	    	   // 	bfile.createNewFile();
	 
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);
	 
	    	    byte[] buffer = new byte[1024];
	 
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	 
	    	    	outStream.write(buffer, 0, length);
	 
	    	    }

	    	    inStream.close();
	    	    
	    	    outStream.flush();
	    	    outStream.close();
	    	    inStream=null;
	    	    outStream=null;

				reportXls = new Xls_Reader(destFilePath);


	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }
	
	public static  boolean isExectuable(String testCaseName,Xls_Reader xls,String sheetName){
		int rows = xls.getRowCount(sheetName);
		
		for(int rNum=0;rNum<rows;rNum++){
			if(xls.getCellData(sheetName,"TestCaseName", rNum).equals(testCaseName)){
				if(xls.getCellData(sheetName, "Runmode", rNum).equals("Y")){
					return true;
					}
				else {

					return false;
				}
			}
			
		}
		return false;
		
	}
	

}
