import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestA {
	
	@Test(dataProvider="getData")
	public void testA(Hashtable<String,String> data){
		System.out.println(data.get("Runmode")+"---------"+data.get("Col1")+"----------"+data.get("Col2"));
		}
	
	@DataProvider
	public Object[][] getData(){
		
		Xls_Reader xls = new Xls_Reader("C:\\FGFG\\selenium\\Data.xlsx");
		String sheetName="Data";
		String testCaseName="TestC";
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
		
		
		
}


