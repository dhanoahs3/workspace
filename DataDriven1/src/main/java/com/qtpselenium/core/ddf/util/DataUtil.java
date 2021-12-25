package com.qtpselenium.core.ddf.util;

import java.util.Hashtable;

public class DataUtil {

	
	public static Object[][] getTestData(Xls_Reader xls,String testCaseName){
		String sheetName="Data";
		// reads data for only testCaseName
		
		int testStartRowNum=1;
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)){
             testStartRowNum++;
		}
		System.out.println("Test starts from row - "+ testStartRowNum);
		
		int colStartRowNum = testStartRowNum +1;
		int dataStartRowNum = testStartRowNum + 2;
		
		int rows = 0;
		while(!xls.getCellData(sheetName,0,dataStartRowNum+rows).equals("")){
			System.out.println(rows);
			rows++;
		}
		System.out.println("The total rows of data is "+rows);

		int cols = 0;
		while(!xls.getCellData(sheetName,cols,colStartRowNum).equals("")){
			cols++;
		}
		System.out.println("The total rows of data is "+cols);
		
		Object[][] data = new Object[rows][1];
		int dataRow = 0;
		Hashtable<String,String> table = new Hashtable<String,String>();
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key = xls.getCellData(sheetName,cNum,colStartRowNum);
				String value = xls.getCellData(sheetName,cNum,rNum);
		        table.put(key, value);
		     }
			data[dataRow][0]=table;
			dataRow++;

			
			
		}
		return data;
	}
	
	public static boolean  isRunnable(Xls_Reader xls,String testCaseName){
		String sheet = "TestCases";
		int rows = xls.getRowCount(sheet);
		for(int r=2;r<rows;r++){
			String testcase = xls.getCellData(sheet,"TCID",r);
			if(testcase.equals(testCaseName)){
				String runmode = xls.getCellData(sheet,"Runmode",r);
				System.out.println(runmode);
				if(runmode.equals("Y")){
					return true;
				}
					else
						return false;
				}

		}
          return false;
			
		}
	
}
