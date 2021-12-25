
public class DataManagement {

	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader("C:\\FGFG\\selenium\\Data.xlsx");
		String sheetName="Data";
		String testCaseName="TestB";
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
	}
}
