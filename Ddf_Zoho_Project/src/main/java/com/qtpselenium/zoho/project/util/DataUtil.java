package com.qtpselenium.zoho.project.util;

import java.util.Hashtable;

public class DataUtil {
	
	public static Object[][] getTestData(Xls_Reader xls ,String testCaseName)
	{
		
		String sheetName="Data";
		int testStartRowNum=1;
	//	System.out.println(xls.getCellData(sheetName, 0, testStartRowNum));
		while(!(xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName))){
			testStartRowNum++;
			}
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}

		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}

		Object[][] data = new Object[rows][1];

int datarow = 0;
Hashtable<String ,String > table = null;

for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
table = new Hashtable<String ,String >();
for(int cNum=0;cNum<cols;cNum++){
String key = xls.getCellData(sheetName,cNum,colStartRowNum);
String value = xls.getCellData(sheetName,cNum,rNum);
table.put(key,value);
		}
data[datarow][0]  =table;	
datarow++;	
		}
return data;
}
	
	
	public static boolean IsRunnable(Xls_Reader xls ,String testCaseName){
		String sheetName="TestCases";
		for(int r = 2 ;r<=8 ; r++){
		String tname =	xls.getCellData(sheetName ,0,r);
		if(tname.trim().equals(testCaseName))
		{
	String Runmode = xls.getCellData(sheetName ,1,r);
if(Runmode.equals("Y"))
	return true;
	else
	return false;
	}	
		}
		
		return false;

	}

}
